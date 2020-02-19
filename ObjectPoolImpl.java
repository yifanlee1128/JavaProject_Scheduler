package Assignment6;

import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * An implementation of an {@code ObjectPool} for objects that implements the {@code
 * PointerableObject} interface.
 *
 * <p>This object pool implements a growing object pool. If the pool do not have any more objects
 * to serve it will create a new object.
 *
 * <p>This pool implementation takes advantage of the objects in the pool are {@code
 * PointerableObject} and do not use any additional backing list or an array in the pool
 * implementation.
 *
 * @param <T>
 */
public class ObjectPoolImpl<T extends PointerableObject<T>> implements ObjectPool<T> {

    /*
    * There are three fields in the object pool.
    * firstObject and currentObject are used to realize the double linked list structure and avoid to use the existing
    * data structure in java (as required by teh professor)
    * _supplier is used to initiate the object pool and supply new object when all the objects in the pool are used up
    * */
    private T firstObject;
    private T currentObject;
    private Supplier<T> _supplier;

    //constructor, we firstly create five objects in the pool
    public ObjectPoolImpl(Supplier<T> supplier){
        firstObject=supplier.get();
        currentObject=firstObject;
        for(int i=0;i<5;i++){
            T tempObject=supplier.get();
            tempObject.previous(currentObject);
            currentObject.next(tempObject);
            currentObject=tempObject;
        }
        _supplier=supplier;
    }

    /*
    * to borrow object from the pool, we firstly use two pointers to judge whether the pool is empty,
    * if it is true, then create a new object
    * before we borrow an object, we reset its two pointers as NULL to avoid troubles when we use this object
    * */
    @Override
    public T borrowObject(){
        if(firstObject==currentObject){
            T tempObject=_supplier.get();
            tempObject.previous(currentObject);
            currentObject.next(tempObject);
            currentObject=tempObject;
        }
        T returnedObject=firstObject;
        firstObject=firstObject.next();
        returnedObject.previous(null);
        returnedObject.next(null);
        return returnedObject;
    }
    //put the returned object in the end of the double linked list.
    @Override
    public void returnObject(T t){

        t.previous(currentObject);
        currentObject.next(t);
        currentObject=t;
    }
}
