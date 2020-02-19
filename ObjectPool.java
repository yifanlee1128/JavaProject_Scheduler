package Assignment6;

/**
 * A base interface for object pool.
 */
public interface ObjectPool<T> {

    /**
     * Returns an object of type T
     *
     * @return an object of type T
     */
    T borrowObject();

    /**
     * Returns on object of type T to the pool.
     *
     * @param t the object to return to the pool
     */
    void returnObject(T t);

}
