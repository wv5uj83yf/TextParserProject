import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
class TextParserTest {
    private final FileHelper fileHelper = new FileHelper();
    TextParser textParser;

    @BeforeEach
    void setUp() {
        textParser = new TextParser(fileHelper);
    }

    @Test
    void parseTabFile() throws IOException {
        var expected = List.of(
                List.of("This", "is", "a", "test"),
                List.of("red", "green", "blue")
        );
        String[] location = {"src/main/resources/files/test.tab"};
        var result = textParser.parse(location);
        log.info("Result: {}", result);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void parseSpacesFile() throws IOException {
        var expected = List.of(
                List.of("test", "file", "with", "varying", "spaces"),
                List.of("a", "b", "c", "d", "e", "fg", "h"),
                List.of("i", "j", "k", "l", "m", "n")
        );
        String[] location = {"src/main/resources/files/test.space"};
        var result = textParser.parse(location);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void parsePipeFile() throws IOException {
        var expected = List.of(
                List.of("test", "file", "with", "pipes"),
                List.of("a", "b", "c"),
                List.of("d", "e")
        );
        String[] location = {"src/main/resources/files/test.pipe"};
        var result = textParser.parse(location);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void parseFailsWithMultipleArgs() {
        String[] location = {"src/main/resources/files/test.pipe", "location2"};

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> textParser.parse(location))
                .withMessage("Must call program with only one argument: file location");
    }

    @Test
    void parseFailsWithNoArgs() {
        String[] location = {};

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> textParser.parse(location))
                .withMessage("Must call program with only one argument: file location");
    }
}