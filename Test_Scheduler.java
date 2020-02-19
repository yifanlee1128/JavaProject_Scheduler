package Assignment6;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class Test_Scheduler {
    @Test
    public void test_ScheduleAt(){
        SchedulerImpl scheduler=new SchedulerImpl(()->new SchedulerContainer(0,null));
        //mock an object
        SchedulerImpl spyScheduler1=spy(scheduler);
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                System.out.print("Test1");
            }
        };
        spyScheduler1.scheduleAt(1, runnable1);
        //check the scheduleAt method is executed one time
        verify(spyScheduler1).scheduleAt(1,runnable1);

        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                System.out.print("Test2");
            }
        };
        spyScheduler1.scheduleAt(1,runnable2);
        //check the scheduleAt method is executed one time
        verify(spyScheduler1).scheduleAt(1,runnable2);
        // check the borrowObject method is executed two times
        verify(spyScheduler1,times(2))._objectPool.borrowObject();

    }
    @Test
    public void test_setTimeAndFire(){
        SchedulerImpl scheduler=new SchedulerImpl(()->new SchedulerContainer(0,null));
        //mock a new object
        SchedulerImpl spyScheduler=spy(scheduler);
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                System.out.print("Test1");
            }
        };
        spyScheduler.scheduleAt(1, runnable1);

        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                System.out.print("Test2");
            }
        };
        Runnable runnable3=new Runnable() {
            @Override
            public void run() {
                System.out.print("Test3");
            }
        };
        spyScheduler.scheduleAt(1,runnable2);
        spyScheduler.scheduleAt(3,runnable3);
        //check the runnable1 and runnable2 are executed by comparing the printed content
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        spyScheduler.setTimeAndFire(2);
        assertEquals("Test1Test2",outContent.toString());
        //check the runnable3 is executed by comparing the printed content
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        spyScheduler.setTimeAndFire(3);
        assertEquals("Test3",outContent1.toString());
        //check the action "setTimeAndFire(2) is executed once"
        verify(spyScheduler,times(1)).setTimeAndFire(2);





    }
}
