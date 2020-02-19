package Assignment6;



public class SchedulerContainer implements PointerableObject<SchedulerContainer>, SchedulerTask {

    private SchedulerContainer next;
    private SchedulerContainer previous;
    /*
    * Runnable and time are used to store the information of the container,
    * _cancel is used to judge whether the scheduled task is canceled, if _cancel equals to "true", then the runnable
    * will not be executed.
    * */
    private Runnable runnable;
    private long time;
    protected boolean _cancel=false;

    //the constructor
    public SchedulerContainer(long time, Runnable runnable){
        this.runnable=runnable;
        this.time=time;
        next=null;
        previous=null;
    }
    //override the function defined in the interface
    @Override
    public SchedulerContainer next() {
        return next;
    }
    @Override
    public void next(SchedulerContainer schedulerContainer) {
        this.next = schedulerContainer;
    }

    @Override
    public SchedulerContainer previous() { return previous; }
    @Override
    public void previous(SchedulerContainer schedulerContainer){this.previous=schedulerContainer;}

    @Override
    public void cancel(){
        _cancel=true;
    }

    //some basic methods which are used to get the information of this container and execute the runnable.
    public void set_time(long time){this.time=time;}
    public void set_runnable(Runnable runnable){this.runnable=runnable;}
    public long get_time(){
        return time;
    }
    public Runnable get_runnable(){return runnable;}
    public boolean get_cancelInfo(){return _cancel;}
    public void run(){
        runnable.run();
    }
}
