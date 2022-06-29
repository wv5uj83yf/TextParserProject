import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
class FileHelperTest {
    private final FileHelper fileHelper = new FileHelper();

    @Test
    void testLoadFileAndGetStream() throws IOException {
        var resultStream = fileHelper.getFileStream("src/main/resources/files/test.tab");
        assertThat(resultStream).isNotNull();
    }

    @Test
    void testGetFileExtension() {
        String location = "src/main/resources/files/test.tab";
        String extension =Files.getFileExtension(location);
        assertThat(extension).isEqualTo("tab");
    }

    @Test
    void testLoadingFileFails() {
        String location = "fake-file.space";
        assertThatExceptionOfType(NoSuchFileException.class)
                .isThrownBy(() -> fileHelper.getFileStream(location))
                .withMessage("File 'fake-file.space' not found");
    }
}