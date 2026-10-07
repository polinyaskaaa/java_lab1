package ua.kpi.comsys.dop;

public record TextMsg(String sender, String text) implements Notification {

    public TextMsg {
        Validation.requireNotBlank(sender, "sender");
        Validation.requireNotBlank(text, "text");
    }
}
