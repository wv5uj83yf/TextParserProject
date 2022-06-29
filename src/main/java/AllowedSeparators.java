import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

/**
 * This class holds the map that is used by TextParser to map extensions to delimiter
 * Any new delimiters and/or file types that are added need to be added to this class
 */
public class AllowedSeparators {
    private static final Map<String, String> extensionsToDelimitersMap =
            Map.ofEntries(
                entry("tab", "\t+"),
                entry("space", "\s+"),
                entry("pipe", "|"),
                entry("csv", ","),
                entry("tsv", "\t+")
            );

    public static String getDelimiter(String extension) {
        String delimiter;
        try {
            delimiter = Objects.requireNonNull(extensionsToDelimitersMap.get(extension));
        } catch (NullPointerException e) {
            throw new DelimiterNotFoundException(String.format("Delimiter for extension %s does not exist", extension), e);
        }
        return delimiter;
    }
}
