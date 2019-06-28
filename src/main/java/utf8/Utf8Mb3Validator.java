package utf8;

import java.util.Set;
import java.util.stream.Collectors;

class Utf8Mb3Validator {

    /**
     * Finds characters which can’t be stored in a MySQL “utf8” column out of the given String.
     *
     * @param input a String which you want to check
     * @return a Set which contains strings that can't be inserted into MySQL "utf8" columns
     */
    Set<String> findProblematicStrings(String input) {
        // References:
        // https://dev.mysql.com/doc/refman/5.7/en/charset-unicode-utf8mb3.html
        // https://www.oracle.com/technetwork/java/javase/downloads/supplementary-142654.html?printOnly=1
        // https://stackoverflow.com/q/56800767/3591946
        return input
                .codePoints() // get Unicode code points
                .filter(codePoint -> Character.charCount(codePoint) > 1) // search for non-BMP characters
                .mapToObj(codePoint -> new String(Character.toChars(codePoint))) // convert code points into Strings
                .collect(Collectors.toSet());
    }
}
