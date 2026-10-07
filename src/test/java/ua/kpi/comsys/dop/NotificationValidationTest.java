package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NotificationValidationTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void textMsgRejectsInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg(sender, "Hello this is first lab work"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void textMsgRejectsInvalidText(String text) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("Polina", text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void imageMsgRejectsInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg(sender, "https://somewebsite.com.ua/image.jpg", 512));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void imageMsgRejectsInvalidUrl(String url) {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Viktoria", url, 512));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void imageMsgRejectsNonPositiveSize(int sizeKb) {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Viktoria", "https://somewebsite.com.ua/image.jpg", sizeKb));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void audioMsgRejectsInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg(sender, 42));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void audioMsgRejectsNonPositiveDuration(int duration) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg("Vladyslav", duration));
    }

    @Test
    void validRecordsAreCreated() {
        assertDoesNotThrow(() -> new TextMsg("Polina", "Hello this is first lab work"));
        assertDoesNotThrow(() -> new ImageMsg("Viktoria", "https://somewebsite.com.ua/image.jpg", 512));
        assertDoesNotThrow(() -> new AudioMsg("Vladyslav", 42));
    }
}
