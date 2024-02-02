package fr.moussalli.projetfilrouge.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStateConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public OrderState convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return OrderState.fromValue(dbData);
    }
}
