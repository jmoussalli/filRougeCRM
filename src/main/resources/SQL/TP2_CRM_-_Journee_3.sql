DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    company_name VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    zip_code VARCHAR(10),
    city VARCHAR(255),
    country VARCHAR(255),
    state BOOLEAN -- 0: INACTIVE, 1: ACTIVE
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        type_presta VARCHAR(255),
                        designation VARCHAR(255),
                        client_id INTEGER,
                        nb_days INTEGER,
                        unit_price NUMERIC,
                        total_exclude_taxe NUMERIC GENERATED ALWAYS AS (unit_price*nb_days) STORED,
    --TTC = HT + (HT x TVA / 100)
                        total_with_taxe NUMERIC GENERATED ALWAYS AS ((unit_price*nb_days)+((unit_price*nb_days)*20/100)) STORED,
                        state INTEGER CHECK (state IN (0, 1, 2)), -- 0: CANCELED, 1: OPTION, 2: CONFIRMED
                        FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Table de jointure pour les clients et orders (Many-to-Many relationship)
DROP TABLE IF EXISTS clients_orders;
CREATE TABLE clients_orders (
                                id_client INT NOT NULL,
                                id_order INT NOT NULL,
                                PRIMARY KEY (id_client, id_order),
                                FOREIGN KEY (id_client) REFERENCES clients(id) ON DELETE CASCADE,
                                FOREIGN KEY (id_order) REFERENCES orders(id) ON DELETE CASCADE
);
select * from clients_orders;

-- Ajouter 2 autres ESN : ATOS(id:3) et SOPRA STERIA(id:4)
INSERT INTO clients(company_name, first_name, last_name, email, phone, address, zip_code, city, country, state) VALUES
    ('Sopra', 'Fabrice', 'Martin', 'martin@mail.com', '06 56 85 84 33', 'abc', 'xyz', 'Nantes', 'France', true),
    ('M2i Formation', 'Julien', 'Lamard', 'lamard@mail.com', '06 11 22 33 44', 'abc', 'Paris', 'Nantes', 'France', false),
    ('ATOS', 'Marcel', 'Jambon', 'marcel@mail.com', '06 56 85 84 33', 'abc', 'xyz', 'Nice', 'France', true),
    ('SOPRA STERIA', 'Glandine', 'Tartiflette', 'glandine@mail.com', '06 56 85 84 33', 'abc', 'xyz', 'Chambéry', 'France', false)
returning *;

INSERT INTO orders (type_presta, designation, client_id, nb_days,unit_price, state) VALUES
    ('Formation','Angular init',2,3,1200,0),
    ('Formation','React avancé',2,3,1000,2),
    ('Coaching','React Techlead',1,20,900,2),
    ('Coaching','Nest.js Techlead',1,50,800,1),
    ('Coaching','React Techlead',3,20,900,1),
    ('Coaching','Jakarta EE',3,100,1500,1),
    ('Coaching','Angular TechLead',4,20,900,1),
    ('Formation','Java For Dummies',2,3,800,2),
    ('Formation','SQL For Dummies',4,3,800,2),
    ('Formation','Angular For Dummies',4,30,1800,2)
returning *;

-- Essai d'insertion d'un order avec un client qui n'existe pas
-- INSERT INTO orders (type_presta, designation, client_id, nb_days,unit_price, state) VALUES
--     ('Formation','Angular init',5,3,1200,0)
-- returning *;

-- tout afficher
SELECT c.*, o.* FROM clients c JOIN orders o ON o.client_id = c.id;

-- *=> Ecrire une requête pour créer ces 2 tables en prenant en compte la jointure
--     OK FAIT PLUS HAUT (Utilisation de "FOREIGN KEY (client_id) REFERENCES clients(id)" dans le CREATE de la table ORDERS

-- *=> Remplissez la base de données au travers des insertions
--     OK FAIT PLUS HAUT

-- *=> Afficher toutes les formations sollicités par le client M2i formation M2i formation
SELECT c.id, c.company_name, o.id, o.designation FROM clients c
JOIN orders o ON o.client_id = c.id
WHERE c.company_name='M2i Formation';

-- *=> Afficher les noms et contacts de tous les contacts des clients qui ont sollicité un coaching
SELECT c.first_name, c.last_name, c.company_name, o.type_presta, o.designation FROM clients c
JOIN orders o ON o.client_id = c.id
WHERE o.type_presta = 'Coaching';

-- *=> Afficher les noms et contacts de tous les contacts des clients qui ont sollicité un
-- coaching pour les accompagnements React.js
SELECT c.first_name, c.last_name, c.company_name, o.type_presta, o.designation FROM clients c
JOIN orders o ON o.client_id = c.id
WHERE o.type_presta = 'Coaching' AND o.designation ILIKE '%react%';

-- *=> Pour chacune des demandes de formation, afficher le prix UHT et prix TTC en se
-- basant sur le unité Price(TJM) et le nombre de jours de prestation tout en sachant
-- que la TVA est de 20%.
SELECT c.first_name, c.last_name, c.company_name,
       o.type_presta, o.designation,
       o.unit_price, o.nb_days,
       o.unit_price*o.nb_days AS HT,
       (o.unit_price*o.nb_days)+((o.unit_price*o.nb_days)*20/100) AS TTC
       FROM clients c
JOIN orders o ON o.client_id = c.id;

-- *=> Lister toutes les prestations qui sont confirmés et qui vont rapporter plus
-- 30.000€

SELECT c.first_name, c.last_name, c.company_name,
        o.type_presta, o.designation, o.state,
        o.unit_price, o.nb_days,
        o.total_exclude_taxe, o.total_with_taxe
FROM clients c
JOIN orders o ON o.client_id = c.id
WHERE o.state=2
  AND o.total_with_taxe > 30000;
