import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class QLStorageTest {
    
    LinkedList<Task> tasks;

    
    @Before
    public void initialize() {
        tasks = new LinkedList<Task>();
        tasks.add(new Task("something"));
        tasks.add(new Task("important"));
        tasks.add(new Task("like"));
        tasks.add(new Task("this"));
    }
    
    @Test
    public void test() {
        QLStorage.saveFile(tasks, "abc.txt");
        LinkedList<Task> temp = QLStorage.loadFile("abc.txt");
        
        assertEquals(tasks.toString(), temp.toString());
    }
}
