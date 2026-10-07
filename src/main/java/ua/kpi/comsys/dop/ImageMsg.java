package ua.kpi.comsys.dop;

public record ImageMsg(String sender, String url, int sizeKb) implements Notification {

    public ImageMsg {
        Validation.requireNotBlank(sender, "sender");
        Validation.requireNotBlank(url, "url");
        Validation.requirePositive(sizeKb, "sizeKb");
    }
}
