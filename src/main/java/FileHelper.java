import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
/**
 * This class holds helpers to be used with files
 */
public class FileHelper {

    /**
     *
     * @param location location of the file
     * @return fileStream a String Stream of the contents of the file
     */
    public Stream<String> getFileStream(String location) throws IOException {
        Stream<String> fileStream;
        try {
            Path path = Paths.get(location);
            fileStream = Files.lines(path);
        } catch (NoSuchFileException e) {
            throw new NoSuchFileException(String.format("File '%s' not found", location));
        }
        return fileStream;
    }

    public String getFileExtension(String location) {
        return com.google.common.io.Files.getFileExtension(location);
    }
}
