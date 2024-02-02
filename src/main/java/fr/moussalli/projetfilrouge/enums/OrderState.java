package fr.moussalli.projetfilrouge.enums;

public enum OrderState {
    CANCELED(0),
    OPTION(1),
    CONFIRMED(2);

    private final int value;

    OrderState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderState fromValue(int value) {
        for (OrderState state : OrderState.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static OrderState fromString(String str) {
        for (OrderState state : OrderState.values()) {
            if (state.name().equalsIgnoreCase(str)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unexpected string '" + str + "'");
    }
}
