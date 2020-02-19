package Assignment6;

/**
 * A representation of an object that can point to another object creating a doubly linked list.
 */
public interface PointerableObject<T extends PointerableObject<T>> {

    /**
     * Returns the object this object points to.
     * @return the object this object points to or null
     */
    T next();

    /**
     * Sets the object this object points to.
     *
     * @param t the object this object points to.
     */
    void next(T t);

    /**
     * Returns the objects pointing to this object.
     *
     * @return the objects pointing to this objects or null
     */
    T previous();

    /**
     * Sets the object pointing to this object.
     *
     * @param t the object pointing to this object
     */
    void previous(T t);


}
