package command;

public class CommandUtils {
    public static String getCommandNameFromPath(String requestPath) {
        int lastSlashIndex = requestPath.lastIndexOf('/');
        return requestPath.substring(lastSlashIndex + 1);
    }
}
