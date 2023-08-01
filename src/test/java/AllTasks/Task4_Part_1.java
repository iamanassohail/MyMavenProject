package AllTasks;

import org.junit.After;
import org.junit.Before;
import org.testng.annotations.Test;

public class Task4_Part_1 {
    @Before
    public void before(){
        System.out.println("This is before function");
    }

    @Test
    public void tests(){
        Task3.AllTests();
    }

    @After
    public void after(){
        System.out.println("This is after function");
    }
}
