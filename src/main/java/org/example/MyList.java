package org.example;

import java.util.*;
import java.util.function.Function;

/**
 * Реализация списка MyList для численных типов
 * @param <E>
 * @author maslovskij.98
 * @version 0.0.1
 */
public class MyList<E extends Number> implements Iterable<E> {
    private static final int arraySize = 10;
    private E[] array;

    private int size = 0;


    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println(list);
        list.remove(1);
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println(list);
        MyList<Integer> list1 = new MyList<>();
        list1.add(1);
        System.out.println(list.equals(list1));
        System.out.println(list.equals(list));
        MyList<Integer> list2 = list;
        System.out.println(list.equals(list2));
        System.out.println(list.hashCode());
        System.out.println(list1.hashCode());
        System.out.println(list2.hashCode());
    }

    public MyList() {
        this.array = (E[]) (new Number[arraySize]);
    }

    public MyIterator iterator() {
        return new MyIterator();
    }

    public void add(E o) {
        if (size == arraySize - 1)
            resize(arraySize * 2);
        array[size++] = o;
    }

    public E get(int index) {
        return (E) array[index];
    }

    private void resize(int i) {
        E[] newArray = Arrays.copyOf(array, i);
        array = newArray;
    }

    public E remove(int index) {
        Object removed = array[index];
        for (int i = index; i <= size; i++)
            array[i] = array[i + 1];
        array[size--] = null;
        if (size <= arraySize / 2 && size > 10)
            resize(arraySize / 2);
        return (E) removed;
    }

    public <R extends Number> MyList<R> map(Function<? super E, ? extends R> function) {
        MyList<R> list = new MyList<>();
        for (int i = 0; i < this.size; i++) {
            list.add(function.apply(this.array[i]));
        }
        return list;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyList<?> list = (MyList<?>) o;
        return arraySize == list.arraySize && size == list.size && arraysEquals(array, list.array);
    }
    private boolean arraysEquals(Number[] array1, Number[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (!array1.equals(array2))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(arraySize, size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
    private int arrayHashCode(Number[] array) {
        if (array == null)
            return 0;

        int result = 1;

        for (Object element : array)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i]).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    /**
     * Разница между статическим вложенным классом и не статическим заключается в :
     * 1. Объект не статического вложенного класса невозможно создать без объекта внешнего класса
     * в отличие от статического.
     * 2. Статический вложенный класс имеет доступ только к статическим полям внешнего класса.
     * 3. Для создания объекта статического вложенного класса без объекта внешнего, нужно также
     * указать имя внешнего класса, а затем вложенного через "." (напрмер :
     * MyList.MyIterator iterator = new MyList.MyIterator();
     */
    public class MyIterator implements Iterator<E> {
        public int index = 0;

        @Override
        public boolean hasNext() {
            if (array[index] != null && index<array.length)
                return true;
            return false;
        }

        @Override
        public E next() {
            return array[index++];
        }
    }
}
