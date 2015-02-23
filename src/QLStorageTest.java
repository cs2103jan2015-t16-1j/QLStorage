import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class QLStorageTest {
    
    LinkedList<Task> tasks;

    
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
    }
    
    @Test
    public void test() {
        QLStorage.saveFile(tasks, "abc.txt");
        LinkedList<Task> temp = QLStorage.loadFile("abc.txt");
        
        assertEquals(tasks.toString(), temp.toString());
    }
}
