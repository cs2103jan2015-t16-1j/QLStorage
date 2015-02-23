import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QLStorage {
    
    private static class TasksWrapper {
        protected LinkedList<Task> tasks;
    }
    
    public static LinkedList<Task> loadFile(String filepath) {
        try (FileReader f = new FileReader(filepath))
        {
            Gson gson = new Gson();
            TasksWrapper wrapper = gson.fromJson(f, TasksWrapper.class);
            return wrapper.tasks;
        } catch (Exception e) {
            
        }
        return null;
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
            
        }
    }
}
