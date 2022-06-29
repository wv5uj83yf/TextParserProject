import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
class AllowedSeparatorsTest {

    @Test
    void getDelimiterReturnsCorrectly() {
        String extension = "tab";
        String delimiter = AllowedSeparators.getDelimiter(extension);
        assertThat(delimiter).isEqualTo("\t");
        log.info("Delimiter: {}", delimiter);
    }

    @Test
    void delimiterDoesNotExist() {
        String extension = "tub";

        assertThatExceptionOfType(DelimiterNotFoundException.class)
                .isThrownBy(() -> AllowedSeparators.getDelimiter(extension))
                .withMessage("Delimiter for extension tub does not exist");
    }
}