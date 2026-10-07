package ua.kpi.comsys.dop;

public record AudioMsg(String sender, int durationSeconds) implements Notification {

    public AudioMsg {
        Validation.requireNotBlank(sender, "sender");
        Validation.requirePositive(durationSeconds, "durationSeconds");
    }
}
