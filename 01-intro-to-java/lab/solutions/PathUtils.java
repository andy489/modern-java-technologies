public class PathUtils {

    private static final String PATH_SEPARATOR = "/";
    private static final String CURRENT_DIRECTORY_SYMBOL = ".";
    private static final String PARENT_DIRECTORY_SYMBOL = "..";

    public static String getCanonicalPath(String path) {
        String[] pathElements = path.split(PATH_SEPARATOR);

        StringBuilder sb = new StringBuilder();

        int skip = 0;

        for (int i = pathElements.length - 1; i >= 0; i--) {
            String currElement = pathElements[i];
            
            if (currElement.isBlank() || currElement.equals(CURRENT_DIRECTORY_SYMBOL)) {
                continue;
            }

            if (currElement.equals(PARENT_DIRECTORY_SYMBOL)) {
                skip++;
            } else if (skip > 0) {
                skip--;
            } else {
                sb.insert(0, currElement);
                sb.insert(0, PATH_SEPARATOR);
            }
        }

        return sb.length() == 0 ? PATH_SEPARATOR : sb.toString();
    }

}
