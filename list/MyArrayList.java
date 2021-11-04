package collections.list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO: Exercise
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    // Increase by 50%
    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = e;
        size++;
        return true;
    }

    /**
     * The Maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays "may" result in OutOfMemoryOrder
     * : Requested array size exceeds VM limit.
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    /**
     * Returns a capacity at least as large as the given minimum capacity.
     * Returns the current capacity increased by 50% if that suffices.
     * Will not return a capacity greater than MAX_ARRAY_SIZE unless
     * the given minimum capacity is greater than MAX_ARRAY_SIZE.
     *
     * @param minCapacity the desired minimum capacity
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity <= minCapacity) {
            // overflow-conscious code
            if (minCapacity < 0 || minCapacity > MAX_ARRAY_SIZE) {
                throw new OutOfMemoryError("integer overflow");
            }
            return minCapacity;
        }
        // this is pretty dangerous. (depends on VMs)
        // alternative could be using 'long' type array
        return (newCapacity <= MAX_ARRAY_SIZE) ? newCapacity : Integer.MAX_VALUE;
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++) {
                    if (elementData[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (o.equals(elementData[i])) {
                        break found;
                    }
                }
            }
            return false;
        }
        fastRemove(i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e: c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int length = a.length;
        if (length == 0) {
            return false;
        }
        if (elementData.length < length + size) {
            elementData = grow(length + size);
        }
        System.arraycopy(a, 0, elementData, size, length);
        size += length;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO: Exercise
        Object[] a = c.toArray();
        int length = a.length;
        if(length == 0){
            return false;
        }
        if (elementData.length < length + size) {
            elementData = grow(length + size);
        }
        System.arraycopy(a, 0, elementData, index, length);
        size += length;
        return  true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        int i;
        for (i = size - 1; i >= 0; i--) {
            if (c.contains(elementData[i])) {
                break;
            }
            if (i == 0) {
                return false;
            }
        }
        for (; i >= 0; i--) {
            if (c.contains(elementData[i])) {
                fastRemove(i);
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO: Exercise
        for (int i = 0; i < elementData.length; i++) {
            if (!c.contains(elementData[i])) {
                remove(elementData[i]);
                return true;
            }
        }return false;
    }

    @Override
    public void clear() {
        for(int to = size, i = size = 0; i < to; i++) {
            elementData[i] = null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elementData[index];
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size);
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        Objects.checkIndexx(index, size);
        E removedValue = (E) elementData[index];
        fastRemove(index);
        return removedValue;
    }

    private void fastRemove(int index) {
        if (index < size - 1) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        size--;
        elementData[size] = null;
    }

    @Override
    public int indexOf(Object o) {
        // TODO: Exercise
        for(int i = 0; i < elementData.length; i ++){
            if (elementData[i] == o){
                return i;
            }
        } return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO: Exercise
        for(int i = elementData.length - 1; i > 0;  i --){
            if(elementData[i] == o){
                return i;
            }
        } return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
