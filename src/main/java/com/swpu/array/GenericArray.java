package com.swpu.array;

/**
 * @author: Lemon-Fruit
 * @date: 2021/6/5 22:06
 */
public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray() {
        this(10);
    }

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public int count() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public boolean contains(T e) {
        for (int i = 0; i < size; ++i) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(T e) {
        for (int i = 0; i < size; ++i) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void add(int index, T e) {
        checkIndexForAdd(index);
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; --i) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++size;
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public void addLast(T e) {
        add(size, e);
    }

    public T remove(int index) {
        checkIndex(index);

        T ret = data[index];
        for (int i = index + 1; i < size; ++i) {
            data[i - 1] = data[i];
        }
        data[--size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }
    }

    private void checkIndexForAdd(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("remove failed! Require index >=0 and index <= size.");
        }
    }
}
