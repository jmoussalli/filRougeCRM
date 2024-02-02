# Projet Fil Rouge : Backend CRM : coder une API web

⛔ Vous ne devez pas créer de pages HTML ou de frontend, dans cette
partie !

📌 Rappel du projet complet qui sera réalisé en plusieurs étapes sur l’ensemble de la formation:

* Création d’un petit CRM simplifié
* Pas d’authentification
* Page d’accueil affiche une liste de commandes
* Une page pour afficher la liste des clients
* Une page avec un formulaire pour ajouter, modifier ou supprimer une commande
* Une page avec un formulaire pour ajouter, modifier ou supprimer un client Pas de sécurisation côté api
* Pas de sécurisation côté api

👉 Objectif pour cette partie :

Réalisation de l’API web avec Spring boot, en réutilisant la base de données créée lors d’un module précédent.

# Liste des endpoints :

orders :

* GET /orders
* POST /orders
* PUT /orders/{id}
* GET /orders/{id}
* DELETE /orders/{id}

clients :

* GET /clients
* POST /clients
* PUT /clients/{id}
* GET /clients/{id}
* DELETE /clients/{id}

💡 Notes :
* Les state sont en valeur numérique dans la base de donnée mais on utilisera les libellés dans l’API web. Vous pouvez utiliser un Enum java pour gérer cela ;o)

* Exemple de body POST pour créer une commande :
```
{
  "typePresta": "IT",
  "designation": "Formation SpringBoot",
  "nbDays": 3,
  "unitPrice": 300.0,
  "state": "CANCELED",
  "client": {
    "id": 2
  }
}
```

* Rappel des correspondances des states :
  * Order : (0,1,2) (CANCELED, OPTION, CONFIRMED)
  * Client : (0, 1) (INACTIVE, ACTIVE)
