package fr.moussalli.projetfilrouge.enums;

public enum ClientState {
    ACTIVE(0),
    INACTIVE(1);

    private final int code;

    ClientState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ClientState fromCode(int code) {
        for (ClientState state : ClientState.values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid code for ClientState: " + code);
    }

    public static ClientState fromString(String text) {
        for (ClientState state : ClientState.values()) {
            if (state.name().equalsIgnoreCase(text)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid text for ClientState: " + text);
    }
}
