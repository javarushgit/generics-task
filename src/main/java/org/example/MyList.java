package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyList<T extends Number> implements Iterable<T> {

    private int INITIAL_CAPACITY = 10;
    private int size = 0;
    private T [] numbers;

    public MyList() {
        numbers = (T[]) new Number [INITIAL_CAPACITY];
    }

    public MyList(int size) {
        INITIAL_CAPACITY = size;
        numbers = (T[]) new Number[INITIAL_CAPACITY];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean contains(T element){
        for (T number : numbers) {
            if (number != null && number.equals(element)){
                return true;
            }
        }
        return false;
    }
    public void set(int index, T element){
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        numbers[index] = element;
    }

    public void add(T element) {
        resize();
        numbers[size++] = element;
    }

    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        resize();
        size++;
        for (int i = size - 1; i >= index; i--) {
            if (i == index) {
                numbers[i] = element;
                return;
            }
            numbers[i] = numbers[i - 1];
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        return numbers[index];
    }

    private void resize() {
        if (size >= numbers.length) {
            INITIAL_CAPACITY = (int) (INITIAL_CAPACITY * 1.5);
            this.numbers = Arrays.copyOf(this.numbers, INITIAL_CAPACITY);
        }
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        T result = null;
        for (int i = index; i < size - 1; i++) {
            if (i == index){
                result = (T) numbers[i];
            }
            numbers[i] = numbers[i + 1];
        }
        size--;
        return result;
    }

    public boolean remove(T t) {
        for (int i = 0; i < size - 1; i++) {
            if (numbers[i].equals(t)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public MyList map(Function f) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                result.append("[");
            }
            if (i < size - 1) {
                result.append(numbers[i]).append(", ");
            }
            else {
                result.append(numbers[i]).append("]");
            }
        }
        return result.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        MyList<? extends Number> myList = (MyList<? extends Number>) o;
        return INITIAL_CAPACITY == myList.INITIAL_CAPACITY && size == myList.size && Arrays.equals(numbers, myList.numbers);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(INITIAL_CAPACITY, size);
        result = 31 * result + Arrays.hashCode(numbers);
        for (int i = 0; i < size - 1; i++) {
            result += numbers[i].hashCode();
            System.out.println(numbers[i].hashCode());
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
