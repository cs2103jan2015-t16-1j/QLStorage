import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QLStorage {
    
    private static final String ERROR_WRITE_FILE = "Error writing file %s";
    private static final String ERROR_READ_FILE = "Error reading file %s";
    private static final String ERROR_CHECK_FILE = "Error checking file %s";
    private static final String ERROR_UNABLE_READ_FILE = "Unable to read file %s";
    private static final String ERROR_DIRECTORY = "%s is a directory";
    
    private static class TasksWrapper {
        protected LinkedList<Task> tasks;
    }
    
    private static boolean checkFile(String filepath) {
        File file = null;
        try {
            file = new File(filepath);
            if (!file.exists()) {
                return false;
            }
            if (file.isDirectory()) {
                throw new Error(String.format(ERROR_DIRECTORY, filepath));
            } else if (!file.canRead()) {
                throw new Error(String.format(ERROR_UNABLE_READ_FILE, filepath));
            }
            return true;
        } catch (Exception e) {
            throw new Error(String.format(ERROR_CHECK_FILE, filepath));
        }
    }
    
    public static LinkedList<Task> loadFile(String filepath) {
        boolean doesFileExist = checkFile(filepath);
        
        if (!doesFileExist) {
            return new LinkedList<Task>();
        }
        
        try (FileReader f = new FileReader(filepath))
        {
            Gson gson = new Gson();
            TasksWrapper wrapper = gson.fromJson(f, TasksWrapper.class);
            return wrapper.tasks;
        } catch (Exception e) {
            throw new Error(String.format(ERROR_READ_FILE, filepath));
        }
    }
    
    public static void saveFile(LinkedList<Task> tasks, String filepath) {
        try (FileWriter f = new FileWriter(filepath))
        {
            TasksWrapper wrapper = new TasksWrapper();
            wrapper.tasks = tasks;
            Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();
            gson.toJson(wrapper, f);
        } catch (Exception e) {
            throw new Error(String.format(ERROR_WRITE_FILE, filepath));
        }
    }
}
