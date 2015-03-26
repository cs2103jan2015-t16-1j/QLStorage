import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QLStorageTest {
    
    private LinkedList<Task> tasks;
    
    private String directory;
    
    @Before
    public void initialize() {
        tasks = new LinkedList<Task>();
        
        Task taskA = new Task("task A");
        taskA.setDescription("task A is to code logic");
        taskA.setDueDate("23022015");
        taskA.setStartDate("22022015");
        taskA.setPriority('H');
        Task taskB = new Task("task B");
        taskB.setDescription("task B is to code ui");
        taskB.setDueDate("25022015");
        taskB.setStartDate("21022015");
        taskB.setPriority('M');
        Task taskC = new Task("task C");
        taskC.setDescription("task C is to code storage");
        taskC.setDueDate("26022015");
        taskC.setStartDate("22012015");
        taskC.setPriority('L');
        Task taskD = new Task("Task D");
        taskD.setDescription("task D is to integrate");
        taskD.setDueDate("23032015");
        taskD.setStartDate("22022015");
        taskD.setPriority('H');
        tasks.add(taskA);
        tasks.add(taskB);
        tasks.add(taskC);
        tasks.add(taskD);
        QLStorage.saveFile(tasks, "abc.txt");
        directory = "tempDirectory";
        (new File(directory)).mkdir();
    }
    
    @Test
    public void test() {
        LinkedList<Task> temp = new LinkedList<Task>();
        Error caught;
        // This is to test valid input file
        caught = null;
        try {
            temp = QLStorage.loadFile(new LinkedList<Task>(), "abc.txt");
        } catch (Error e) {
            caught = e;
        }
        assertEquals(null, caught);
        assertEquals(tasks.toString(), temp.toString());
        
        // This is to test another valid but non existence input file
        caught = null;
        try {
            temp = QLStorage.loadFile(new LinkedList<Task>(), "hi");
        } catch (Error e) {
            caught = e;
        }
        assertEquals(null, caught);
        assertEquals("[]", temp.toString());
        
        // This is to test invalid input file
        caught = null;
        try {
            temp = QLStorage.loadFile(new LinkedList<Task>(), directory);
        } catch (Error e) {
            caught = e;
        }
        assertNotEquals(null, caught);
        assertEquals(directory + " is a directory", caught.getMessage());
    }
}
