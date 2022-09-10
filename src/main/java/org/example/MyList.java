package org.example;

import java.util.Arrays;
import java.util.function.Function;

public class MyList<T extends Number> {
    private int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Number[] numbers;

    private class Iterator {
        /*boolean hasNext() {

        }
        T next() {

        }*/
    }

    public MyList() {
        numbers = new Number[INITIAL_CAPACITY];
    }

    public MyList(int size) {
        INITIAL_CAPACITY = size;
        numbers = new Number[INITIAL_CAPACITY];
    }
    boolean isEmpty(){
        return size == 0;
    }
    boolean contains(T element){
        for (Number number : numbers) {
            if (number != null && number.equals(element)){
                return true;
            }
        }
        return false;
    }
    public void set(int index, T element){
        numbers[index] = element;
    }

    public void add(T element) {
        resize();
        numbers[size++] = element;
    }

    public void add(int index, T element) {
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

    public Number get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return numbers[index];
    }

    private void resize() {
        if (size >= numbers.length) {
            INITIAL_CAPACITY = (int) (INITIAL_CAPACITY * 1.5);
            this.numbers = Arrays.copyOf(this.numbers, INITIAL_CAPACITY);
        }
    }

    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        size--;
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
}
