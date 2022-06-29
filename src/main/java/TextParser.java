import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class TextParser {
    private FileHelper fileHelper;

    List<List<String>> parse(String[] args) throws IOException {

        Preconditions.checkArgument(args.length == 1,
                "Must call program with only one argument: file name.");
        String location = args[0];
        Preconditions.checkArgument(StringUtils.isNotBlank(location),
                "File name must not be blank.");

        List<List<String>> parsedLines = new ArrayList<>();
        Stream<String> fileStream = fileHelper.getFileStream(location);
        String extension = fileHelper.getFileExtension(location);
        String delimiter = AllowedSeparators.getDelimiter(extension);

        try (Stream<String> s = fileStream) {
            s.forEach(line -> {
                List<String> lineList = Arrays.asList(StringUtils.split(line, delimiter));
                parsedLines.add(lineList);
            });
        } finally {
            fileStream.close();
        }

        // I was not sure how to represent the output, so for now I would log it to the Command line
        log.info("Output of file {}: \n{}", location, parsedLines);
        return parsedLines;
    }

    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper();
        TextParser parser = new TextParser(fileHelper);
        try {
            parser.parse(args);
        } catch (IOException e) {
            log.error("Exception when running TextParser: {}", e.getMessage());
        }
    }
}
