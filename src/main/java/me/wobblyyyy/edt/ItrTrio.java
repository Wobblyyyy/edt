package me.wobblyyyy.edt;

/**
 * An iteration trio for sets that have three consumed arguments.
 *
 * @param <A> the first of three arguments.
 * @param <B> the second of three arguments.
 * @param <C> the third of three arguments.
 */
public interface ItrTrio<A, B, C> {
    /**
     * Get the previous trio element.
     *
     * @return the previous trio element. If this element isn't contained in
     * the iterated set, a null pointer exception will be thrown.
     * @throws NullPointerException if there is no previous element to
     *                              reference.
     */
    Trio<A, B, C> previousElement();

    /**
     * Get the previous A element.
     *
     * @return the previous A element. If this element isn't contained in
     * the iterated set, a null pointer exception will be thrown.
     * @throws NullPointerException if there is no previous element to
     *                              reference.
     */
    A previousA();

    /**
     * Get the previous B element.
     *
     * @return the previous B element. If this element isn't contained in
     * the iterated set, a null pointer exception will be thrown.
     * @throws NullPointerException if there is no previous element to
     *                              reference.
     */
    B previousB();

    /**
     * Get the previous C element.
     *
     * @return the previous C element. If this element isn't contained in
     * the iterated set, a null pointer exception will be thrown.
     * @throws NullPointerException if there is no previous element to
     *                              reference.
     */
    C previousC();

    /**
     * Get the current trio element.
     *
     * @return the current trio element. This method shouldn't ever throw
     * any exceptions.
     */
    Trio<A, B, C> element();

    /**
     * Get the current A element.
     *
     * @return the current A element. This method shouldn't ever throw
     * any exceptions.
     */
    A a();

    /**
     * Get the current B element.
     *
     * @return the current B element. This method shouldn't ever throw
     * any exceptions.
     */
    B b();

    /**
     * Get the current C element.
     *
     * @return the current C element. This method shouldn't ever throw
     * any exceptions.
     */
    C c();

    /**
     * Get the next element in the iterated trio set.
     *
     * @return the next element. If there is no next element, a null pointer
     * exception should be thrown.
     * @throws NullPointerException if there is no next element.
     */
    Trio<A, B, C> nextElement();

    /**
     * Get the next A element in the iterated trio set.
     *
     * @return the next A element. If there is no next element, a null pointer
     * exception should be thrown.
     * @throws NullPointerException if there is no next element.
     */
    A nextA();

    /**
     * Get the next B element in the iterated trio set.
     *
     * @return the next B element. If there is no next element, a null pointer
     * exception should be thrown.
     * @throws NullPointerException if there is no next element.
     */
    B nextB();

    /**
     * Get the next C element in the iterated trio set.
     *
     * @return the next C element. If there is no next element, a null pointer
     * exception should be thrown.
     * @throws NullPointerException if there is no next element.
     */
    C nextC();

    /**
     * Get the previous index.
     *
     * @return the previous index. This index can be -1.
     */
    int previousIndex();

    /**
     * Get the current index.
     *
     * @return the current index.
     */
    int index();

    /**
     * Get the next index.
     *
     * @return the next index. This index can be out of bounds!
     */
    int nextIndex();

    /**
     * Run the given executable code for each element of the iterated set
     * that's contained in the requested range.
     *
     * @param runnable the code to be executed for each of the elements.
     * @param min the minimum element in the iterated set.
     * @param max the maximum element in the iterated set.
     */
    void forEach(Runnable runnable, int min, int max);

    /**
     * Run the given executable code for each element of the iterated set
     * that's contained in the requested range.
     *
     * @param runnable the code to be executed for each of the elements.
     */
    void forEach(Runnable runnable);

    /**
     * Run the given executable code for each element of the iterated set
     * that's contained in the requested range.
     *
     * @param consumer the code to be executed for each of the elements.
     * @param min the minimum element in the iterated set.
     * @param max the maximum element in the iterated set.
     */
    void forEach(TriConsumer<A, B, C> consumer, int min, int max);

    /**
     * Run the given executable code for each element of the iterated set
     * that's contained in the requested range.
     *
     * @param consumer the code to be executed for each of the elements.
     */
    void forEach(TriConsumer<A, B, C> consumer);
}
