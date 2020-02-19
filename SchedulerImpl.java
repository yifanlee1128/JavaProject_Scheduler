package Assignment6;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * An implementation of {@code Scheduler}.
 *
 * <p>The implementation uses an {@code ObjectPool} to ensure that there is no continues
 * generation of garbage.
 */
public class SchedulerImpl implements Scheduler {
    protected ObjectPool<SchedulerContainer> _objectPool;
    private HashMap<Long,SchedulerContainer> containerMap;

    //constructor creates an object pool and two pointers to realize the linked container list.
    public SchedulerImpl(Supplier<SchedulerContainer> supplier){
        _objectPool=new ObjectPoolImpl<>(supplier);
        containerMap=new HashMap<>();
    }

    //to create a container, we firstly borrow an object from pool and then initialize the parameters of the container
    @Override
    public SchedulerTask scheduleAt(long time, Runnable runnable) {
        if(time<0)
            throw new IllegalArgumentException("Argument time cannot be negative!");
        if(runnable==null)
            throw  new NullPointerException("Argument runnable is NULL!");
        SchedulerContainer newSchedulerContainer=_objectPool.borrowObject();
        newSchedulerContainer.set_time(time);
        newSchedulerContainer.set_runnable(runnable);
        if(!containerMap.containsKey(time)) containerMap.put(time,newSchedulerContainer);
        else{
            SchedulerContainer temp=containerMap.get(time);
            while(temp.next()!=null) temp=temp.next();
            temp.next(newSchedulerContainer);
        }
        return newSchedulerContainer;
    }

    /**
     * Executes all the scheduled task before or at the specified time.
     *
     * @param time the time.
     */
    public void setTimeAndFire(long time) {
        // execute all the tasks with time lower than or equal to the current time,
        // if a container is canceled, then skip it
        LinkedList<SchedulerContainer> returnList=new LinkedList<>();
        for(long t:containerMap.keySet()){
            if(t>time) continue;
            else{
                SchedulerContainer temp=containerMap.get(t);
                while(temp!=null){
                    if(!temp.get_cancelInfo()) {
                        temp.run();
                        temp.cancel();
                    }
                    returnList.add(temp);
                    temp=temp.next();
                }
            }
        }
        for(SchedulerContainer sc:returnList) _objectPool.returnObject(sc);
    }
}
