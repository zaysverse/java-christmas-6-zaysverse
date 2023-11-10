package christmas.domain;

public enum EventBadge {
    NONE("없음"),
    STAR("별"),
    TREE("나무"),
    SANTA("산타");

    private final String message;

    EventBadge(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
