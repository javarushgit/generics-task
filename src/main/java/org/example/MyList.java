package org.example;

import java.util.*;
import java.util.function.Function;

public class MyList<T extends Number> implements Iterable<T> {
    private final static int INITIAL_CAPACITY = 10;
    private int size = 0;
    private T[] numbers;

    @SuppressWarnings("unchecked")
    public MyList() {
        numbers = (T[]) new Number[INITIAL_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyList(int size) {
        numbers = (T[]) new Number[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        for (T number : numbers) {
            if (number != null && number.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        numbers[index] = element;
    }

    public void add(T element) {
        if (size >= numbers.length) {
            resize();
        }
        numbers[size++] = element;
    }

    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        if (size >= numbers.length) {
            resize();
        }
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
        this.numbers = Arrays.copyOf(this.numbers, (int) (numbers.length * 1.5));
    }

        public T remove ( int index){
            if (index >= size || index < 0) {
                throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
            }
            T result = null;
            for (int i = index; i < size - 1; i++) {
                if (i == index) {
                    result = (T) numbers[i];
                }
                numbers[i] = numbers[i + 1];
            }
            size--;
            return result;
        }

        public boolean remove (T t){
            for (int i = 0; i < size - 1; i++) {
                if (numbers[i].equals(t)) {
                    remove(i);
                    return true;
                }
            }
            return false;
        }

        public int size () {
            return size;
        }

        public <R extends Number > MyList < R > map(Function < ? super T, ? extends R > function){
            MyList<R> newList = new MyList<>();
            for (T element :
                    numbers) {
                if (element != null)
                    newList.add(function.apply(element));
            }
            return newList;
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    result.append("[");
                }
                if (i < size - 1) {
                    result.append(numbers[i]).append(", ");
                } else {
                    result.append(numbers[i]).append("]");
                }
            }
            return result.toString();
        }

        @Override
        public boolean equals (Object o){
            if (this == o)
                return true;
            if (o == null || this.getClass() != o.getClass())
                return false;
            MyList<? extends Number> myList = (MyList<? extends Number>) o;
            return Arrays.equals(numbers, myList.numbers);
        }

        @Override
        public int hashCode () {
            int result = 1;
            for (T element : numbers) {
                if (element != null){
                    result = 31 * result + element.hashCode();
                }
            }
            return result;
        }

        @Override
        public Iterator<T> iterator () {
            return new MyIterator();
        }
        /**
         * @autor Куприянов Дмитрий
         * Разница между статическим InnerClass и нестатическими:
         * 1. Из статического InnerClass'а можно обращаться только к статическим полям внешнего класса.
         * 2. Экземпляр нестатического класса нельзя создать без объекта внешнего класса.
         * 3. Нестатический класс не может содержать статические переменные и методы.
         */
        public class MyIterator implements Iterator<T> {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                return numbers[pos++];
            }
        }
    }
