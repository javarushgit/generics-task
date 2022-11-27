package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

public class MyList<T extends Number> implements Iterable<T> {
    private T[] myList;
    private int myListSize;

    /**
     * конструктор, позволяет инициализировать коллекцию  списком любой длинны из любых значений, наследников класса Number
     */

public MyList(T... o) {
    this.myListSize = o.length;
    Stack<Number> stack = new Stack<Number>(Number.class, myListSize);
    this.myList = (T[]) stack.array;
    for (T cell : o) {
        add(cell);
    }
}
    /**
     * конструктор, позволяет создать пустую коллекцию из 16 ячеек, наследников класса Number
     */

    public MyList() {
        this.myListSize = 16;
        Stack<Number> stack = new Stack<Number>(Number.class, myListSize);
        this.myList = (T[]) stack.array;
    }

    public void add(Object o) {
        int count = myList.length;
        if (checkForEmptyCells()) {
            for (int i = 0; i < count; i++) {
                if (myList[i] == null) {
                    myList[i] = (T) o;
                    break;
                } else {
                    continue;
                }
            }
        } else if (!checkForEmptyCells()) {
            resize();
            myList[count] = (T) o;
        }
    }

    public Number get(int index) {
        if (myList.length < index || index < 0) {
            throw new RuntimeException("Not implemented");
        }
        return myList[index];
    }


    private void resize() {
        if (myList == null) {
            throw new RuntimeException("Not implemented");
        }
        int count = myList.length;
        T[] tmp = Arrays.copyOf(myList, count + 1);
        myList = tmp;
    }

    public void resize(int index) {
        if (myList == null) {
            throw new RuntimeException("Not implemented");
        }
        int count = myList.length;
        T[] tmp;
        if ((count + index) <= 0) {
            tmp = Arrays.copyOf(myList, 0);
        } else {
            tmp = Arrays.copyOf(myList, count + index);
        }
        myList = tmp;
    }


    public Number remove(int index) {
        if (myList.length < index || index < 0) {
            throw new RuntimeException("Not implemented");
        }
        T[] tmp = Arrays.copyOf(myList, myList.length - 1);
        int i = 0;
        int j = 0;
        Number delete=0;
        for (T element : myList) {
            if (j != index) {
                tmp[i] = myList[j];
            } else {
                j = j + 1;
             delete=element;
                continue;
            }
            i++;
            j++;
        }
        myList = tmp;
        return delete;
    }

    public Number remove(Object o) {
        if (!(Arrays.stream(myList).anyMatch(x -> x == o))) {
            throw new RuntimeException("Not implemented");
        }
        int count = 0;
        for (T element : myList) {
            if (element == o) {
                count++;
            }
        }
        T[] tmp = Arrays.copyOf(myList, myList.length - count);
        int i = 0;
        int j = 0;
       Number delete=0;
        for (T element : myList) {
            if (element != o) {
                tmp[i] = myList[j];
            } else {
                j = j + 1;
                delete=element;
                         continue;
            }
            i++;
            j++;
        }
        myList = tmp;
        return delete;
    }


    public void map(Function f) {
        if (myList == null) {
            throw new RuntimeException("Not implemented");
        }
        Object[] tmp = Arrays.copyOf(myList, myList.length);
        Stream stream = Stream.of(tmp);
        tmp = stream.map(x -> f.apply(x)).toArray();
        for (int i = 0; i < tmp.length; i++) {
            myList[i] = (T) tmp[i];
        }
    }

    public int size() {
        if (myList == null) {
            throw new RuntimeException("Not implemented");
        }
        int count = 0;
        for (T element : myList) {
            count++;
        }
        return count;
    }

    public Double toDoubleType(Object o) {
        Number number = (Number) o;
        return number.doubleValue();
    }

    public float toFloatType(Object o) {
        Number number = (Number) o;
        return number.floatValue();
    }

    public short toShortType(Object o) {
        Number number = (Number) o;
        return number.shortValue();
    }

    public int hashCode(Object o) {
        int result = 31 * (o != null ? o.hashCode() : 0);
        return result;
    }


    public boolean equals(MyList obj) {
        if (this.getClass() != obj.getClass() || obj.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < obj.size(); i++) {
            if (myList[i] != (T) obj.get(i)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String result = Arrays.toString(myList);
        return result;
    }

    /**
     * статический  InnerClass от нестатического отличается
     *  может наследовать статические классы
     *  может быть родителем любого класса
     *  может содержать статические и нестатические поля и методы
     */

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < myListSize && myList[currentIndex] != null;
            }

            @Override
            public T next() {
                return myList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    private boolean checkForEmptyCells() {
        for (T cell : myList) {
            if (cell == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * вспомогательный класс , который поможет создать объект массива соответствующего типа в своем конструкторе,
     * а это означает,что типы объектов, которые хранятся в коллекции, будут проверяться в момент их добавления в коллекцию.
     * плюс помогает решить конфликт что массив ковариантный тип хранения данных а дженерики инвариантны
     */

        class Stack<T> {
        public final T[] array;

        public Stack(Class<T> clazz, int capacity) {
            array = (T[]) Array.newInstance(clazz, capacity);
        }
    }


}