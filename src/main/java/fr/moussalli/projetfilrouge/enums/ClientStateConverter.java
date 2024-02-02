package fr.moussalli.projetfilrouge.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

@Converter(autoApply = true)
public class ClientStateConverter implements AttributeConverter<ClientState, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(ClientState attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.equals(ClientState.ACTIVE);
    }

    @Override
    public ClientState convertToEntityAttribute(Boolean dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData ? ClientState.ACTIVE : ClientState.INACTIVE;
    }
}
