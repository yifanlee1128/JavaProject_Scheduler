package Assignment6;



import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class Test_SchedulerContainer{

    @Test
    public void test_SchedulerContainer(){

        //create two runnable to test the container
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                System.out.print("runnable1");
            }
        };
        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                System.out.print("runnable2");
            }
        };
        //create two containers
        SchedulerContainer schedulerContainer1=new SchedulerContainer(1,runnable1);
        SchedulerContainer schedulerContainer2=new SchedulerContainer(2,runnable2);
        //when they are created , check their pointers are NULL
        assertNull(schedulerContainer1.next());
        assertNull(schedulerContainer1.previous());
        assertNull(schedulerContainer2.next());
        assertNull(schedulerContainer2.previous());
        //link two containers
        schedulerContainer1.next(schedulerContainer2);
        schedulerContainer2.previous(schedulerContainer1);
        //check their pointers after linking them
        assertEquals(schedulerContainer1.next(),schedulerContainer2);
        assertEquals(schedulerContainer2.previous(),schedulerContainer1);
        //cancel first container, check their _cancel parameters
        schedulerContainer1.cancel();
        assertTrue(schedulerContainer1._cancel);
        assertFalse(schedulerContainer2._cancel);
        // check their scheduled time
        assertEquals(schedulerContainer1.get_time(),1);
        assertEquals(schedulerContainer2.get_time(),2);
        //check the run method, compared the printed String
        //the first container should print "runnable1", the second should print "runnable2"
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        schedulerContainer1.run();
        assertEquals("runnable1",outContent.toString());
        outContent=new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        schedulerContainer2.run();
        assertEquals("runnable2",outContent.toString());

        //change the scheduled time of container1
        schedulerContainer1.set_time(10);
        assertEquals(10,schedulerContainer1.get_time());
        //create a new runnable to check the runnable parameter for each container
        //before assigning it the container 1,the assertion should be false, after that, it should be true.
        Runnable newRunnable=new Runnable() {
            @Override
            public void run() {
                System.out.print(12);
            }
        };

        assertFalse(schedulerContainer1.get_runnable()==newRunnable);
        schedulerContainer1.set_runnable(newRunnable);
        assertTrue(schedulerContainer1.get_runnable()==newRunnable);



    }


}
