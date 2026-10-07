package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationProcessorTest {

    private final NotificationProcessor processor = new NotificationProcessor();

    @Test
    @DisplayName("TextMsg is formatted as TEXT notification")
    void processTextMsg() {
        String expected = """
                Notification: TEXT
                From: Polina
                Message: Hello this is first lab work""";

        assertEquals(expected, processor.process(new TextMsg("Polina", "Hello this is first lab work")));
    }

    @Test
    @DisplayName("ImageMsg is formatted as IMAGE notification")
    void processImageMsg() {
        String expected = """
                Notification: IMAGE
                From: Viktoria
                URL: https://somewebsite.com.ua/image.jpg (512 KB)""";

        assertEquals(expected,
                processor.process(new ImageMsg("Viktoria", "https://somewebsite.com.ua/image.jpg", 512)));
    }

    @Test
    @DisplayName("AudioMsg is formatted as AUDIO notification")
    void processAudioMsg() {
        String expected = """
                Notification: AUDIO
                From: Vladyslav
                Duration: 42 seconds""";

        assertEquals(expected, processor.process(new AudioMsg("Vladyslav", 42)));
    }

    @Test
    @DisplayName("null notification is rejected with exact message")
    void processNullThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> processor.process(null));

        assertEquals("Notification cannot be null", ex.getMessage());
    }
}
