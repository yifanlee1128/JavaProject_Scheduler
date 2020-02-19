package Assignment6;

/**
 * A Scheduler that can be used to schedule and execute events.
 *
 * <p>A scheduler execute the events by time priority. If two {@code Runnable} are schedule at
 * the same time, they will be executed by order of schedule. When
 * <code>
 *     scheduleAt(100,runnable1);
 *     scheduleAt(100,runnable2);
 * </code>
 * It is guaranteed that runnble1 is executed before runnable2.
 *
 */
public interface Scheduler {

    /**
     * Schedule a {@code Runnable} to be executed at some future time.
     *
     * @param time the time to run the {@code Runnable} as millis from epoch.
     * @param runnable the {@code Runnable} to execute.
     * @return a {@code SchedulerTask} representing the scheduled task
     * @throws NullPointerException if {@code runnable} is null
     * @throws IllegalArgumentException if time is negative
     */
    SchedulerTask scheduleAt(long time, Runnable runnable) throws NullPointerException,IllegalArgumentException;

}
