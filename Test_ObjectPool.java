package Assignment6;
import org.junit.Test;


import java.util.function.Supplier;

import static org.junit.Assert.*;
/*
* as requirement, we cannot use Container class to test the object pool,
* So I create a basic class named Number to test the object pool
* */
class Number implements PointerableObject<Number>{
    private int num=0;
    private Number next=null;
    private Number previous=null;

    @Override
    public Number next(){ return this.next; }
    @Override
    public void next(Number nextnumber){ this.next=nextnumber; }
    @Override
    public Number previous(){ return  this.previous; }
    @Override
    public void previous(Number previousnumber){ this.previous=previousnumber; }

    public int getNum(){
        return num;
    }

}

public class Test_ObjectPool {

    @Test
    public void test_ObjectPool(){
        //firstly initialize the supplier to create an object pool
        Supplier<Number> supplier= Number::new;
        ObjectPool<Number> objectPool=new ObjectPoolImpl<>(supplier);
        //test borrowObject method, then check the class of the borrowed object
        Number a=objectPool.borrowObject();
        assertEquals(a.getClass(), Number.class);
        //check the two pointers of a new borrowed object is null
        Number b=objectPool.borrowObject();
        assertTrue(b.previous()==null);
        assertTrue(b.next()==null);
        //borrow more(6) object than the initial numbers(5) of the objects in the pool,
        // make sure the pool can generate new object when it is empty
        Number c=objectPool.borrowObject();
        Number d=objectPool.borrowObject();
        Number e=objectPool.borrowObject();
        Number f=objectPool.borrowObject();
        //test returnObject methods, check whether the returned object is settled in a double linked list
        objectPool.returnObject(a);
        objectPool.returnObject(b);
        assertTrue(b.previous()==a);
        assertTrue(a.next()==b);
        //return all borrowed objects
        objectPool.returnObject(c);
        objectPool.returnObject(d);
        objectPool.returnObject(e);
        objectPool.returnObject(f);


    }
}
