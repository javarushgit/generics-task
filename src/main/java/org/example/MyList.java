package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

/**
 * MyList is the custom ArrayList class implementation standing for working
 * with elements extends Number.
 * This implementation has add() method for adding elements to the list,
 * get() method for getting certain elements from a list.
 * Remove method standing for removing elements from the list.
 *
 * @param <T> extends Number
 * @author vnsemkin@gmail.com
 * @version 1
 */
public class MyList<T extends Number> {
    int DEFAULT_CAPACITY = 10;
    private T[] myArray;
    private int myArraySize = 0;

    @SuppressWarnings("unchecked")
    public MyList(int initialCapacity) {
        if (initialCapacity > 0) {
            myArray = (T[]) new Number[initialCapacity];
        } else if (initialCapacity == 0) {
            myArray = (T[]) new Number[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    public MyList() {
        myArray = (T[]) new Number[DEFAULT_CAPACITY];
    }

    public void add(T t) {
        if (myArraySize >= myArray.length) {
            resize(myArray, true);
        }
        myArray[myArraySize] = t;
        myArraySize++;
    }

    public T get(int index) {
        return myArray[index];
    }

    @SuppressWarnings("unchecked")
    private void resize(T[] oldMyArray, boolean isGrow) {
        if (isGrow) {
            myArray = (T[]) new Number[(int) (oldMyArray.length * 1.5)];
            System.arraycopy(oldMyArray, 0, myArray, 0, oldMyArray.length);
        } else {
            myArray = (T[]) new Number[(int) (oldMyArray.length / 1.5)];
            System.arraycopy(oldMyArray, 0, myArray, 0, (int) (oldMyArray.length / 1.5));
        }
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T removeElement = null;
        if (myArraySize < myArray.length / 1.5)
            resize(myArray, false);
        int size = myArray.length - 1;
        if (index >= 0 && size > index) {
            removeElement = myArray[index];
            myArray[index] = null;
            System.arraycopy(myArray, index + 1, myArray, index, size - (index + 1));
            myArray[size - 1] = null;
            myArraySize--;
        }
        return removeElement;
    }

    public <R extends Number> MyList<R> map(Function<T, R> mapper) {
        MyList<R> newTypeList = new MyList<>();
        for (int i = 0; i < myArray.length && myArray[i] != null; i++) {
            newTypeList.add(mapper.apply(myArray[i]));
        }
        return newTypeList;
    }

    public int size() {
        return myArraySize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < myArray.length && myArray[i] != null; i++) {
            stringBuilder.append(myArray[i]).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append("]");
        return String.valueOf(stringBuilder);
    }

    public Iterator<T> iterator() {
        return new MyArrayIterator();
    }

    /**
     * Причины использования вложенных классов такие. Если класс полезен только для одного другого класса,
     * то вполне логично встроить его в этот класс и хранить их вместе.
     * Не статический вложенный класс:
     * Из него видны:
     * — все (даже private) свойства и методы OuterClass обычные и статические.
     * — public и protected свойства и методы родителя OuterClass обычные и статические.
     * То есть те, которые видны в OuterClass.
     * Его видно:
     * — согласно модификатору доступа.
     * Может наследовать:
     * — обычные классы.
     * — такие же внутренние классы в OuterClass и его предках.
     * Может быть наследован:
     * — таким же внутренним классом в OuterClass и его наследниках.
     * Может имплементировать интерфейс
     * Может содержать:
     * — только обычные свойства и методы (не статические).
     * Статический вложенный класс:
     * Из него видны:
     * — все (даже private) свойства и методы OuterClass обычные и статические.
     * — public и protected свойства и методы родителя OuterClass обычные и статические.
     * То есть те, которые видны в OuterClass.
     * Его видно:
     * — согласно модификатору доступа.
     * Может наследовать:
     * — обычные классы.
     * — такие же внутренние классы в OuterClass и его предках.
     * Может быть наследован:
     * — таким же внутренним классом в OuterClass и его наследниках.
     * Может имплементировать интерфейс
     * Может содержать:
     * — только обычные свойства и методы (не статические).
     * Экзэмпляр этого класса создаётся так:
     * OuterClass outerClass = new OuterClass();
     * OuterClass.InnerClass innerClass = outerClass.new InnerClass();
     * Статический вложенный класс (StaticInnerClass)
     * public class OuterClass {
     * public static class StaticInnerClass{
     * }
     * }
     * Из него (самого класса) видны:
     * — статические свойства и методы OuterClass (даже private).
     * — статические свойства и методы родителя OuterClass public и protected. То есть те, которые видны в OuterClass.
     * Из его экземпляра видны:
     * — все (даже private) свойства и методы OuterClass обычные и статические.
     * — public и protected свойства и методы родителя OuterClass обычные и статические. То есть те, которые видны в OuterClass.
     * Его видно:
     * — согласно модификатору доступа.
     * Может наследовать:
     * — обычные классы.
     * — такие же статические внутренние классы в OuterClassе и его предках.
     * Может быть наследован:
     * — любым классом:
     * — вложенным
     * — не вложенным
     * — статическим
     * — не статическим!
     * Может имплементировать интерфейс
     * Может содержать:
     * — статические свойства и методы.
     * — не статические свойства и методы.
     */
    public class MyArrayIterator implements Iterator<T> {
        public int index;

        public MyArrayIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < myArray.length && myArray[index] != null;
        }

        @Override
        public T next() {
            return (T) myArray[index++];
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        MyList<T> myList = (MyList<T>) o;
        return Arrays.equals(myArray, myList.myArray);
    }

    @Override
    public int hashCode() {
        if (myArray == null)
            return 0;
        int result = 1;
        for (int i = 0; i < myArray.length && myArray[i] != null; i++) {
            result = 31 * result + ((int) myArray[i] * 154497374);
        }
        return result;
    }
}
