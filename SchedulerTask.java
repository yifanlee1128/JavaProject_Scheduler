package Assignment6;

/**
 * A representation of a scheduled {@code java.lang.Runnable}.
 */
public interface SchedulerTask {

    /**
     * Cancel the scheduled task from being executed by a scheduler.
     */
    void cancel();
}
