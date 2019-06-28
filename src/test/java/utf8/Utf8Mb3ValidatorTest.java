package utf8;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}
