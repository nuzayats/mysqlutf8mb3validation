package utf8;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Utf8Mb3ValidatorTest {

    Utf8Mb3Validator sut = new Utf8Mb3Validator();

    @Test
    void noEmoji() {
        Set<String> actual = sut.findProblematicStrings("no emojis");

        assertThat(actual).isEmpty();
    }

    @Test
    void emoji() {
        Set<String> actual = sut.findProblematicStrings("Musical notes 🎵🎶");

        assertThat(actual).containsOnly("🎵", "🎶");
    }

    @Test
    void border() {
        //noinspection StringBufferReplaceableByString
        String input = new StringBuilder()
                .append(Character.toChars(0xFFFE))
                .append(Character.toChars(0xFFFF))
                .append(Character.toChars(0x10000))
                .append(Character.toChars(0x10001))
                .toString();

        Set<String> actual = sut.findProblematicStrings(input);

        assertThat(actual).containsOnly(
                new String(Character.toChars(0x10000)),
                new String(Character.toChars(0x10001)));
    }
}
