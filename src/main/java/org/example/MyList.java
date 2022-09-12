package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

public class MyList<T extends Number> implements Iterable<T> {
    private T[] myList;
    private int myListSize;

    public MyList(T... o) {
        //         if (!(o instanceof MyList<T>)) {throw new RuntimeException("Not implemented");  }   
        this.myListSize = o.length;
        Stack<Number> stack = new Stack<Number>(Number.class, myListSize);
        this.myList = (T[]) stack.array;
        for (T cell : o) {
            add(cell);
        }

    }

    public MyList() {
        this.myListSize = 16;
        Stack<Number> stack = new Stack<Number>(Number.class, myListSize);
        this.myList = (T[]) stack.array;
    }

    public void add(Object o) {
//         if (!(o instanceof MyList<T>)) {throw new RuntimeException("Not implemented");  }
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
        if ((count + index) <= 0) {
            T[] tmp = Arrays.copyOf(myList, 0);
            myList = tmp;
        } else {
            T[] tmp = Arrays.copyOf(myList, count + index);
            myList = tmp;
        }
    }


    public void remove(int index) {
        if (myList.length < index || index < 0) {
            throw new RuntimeException("Not implemented");
        }
        T[] tmp = Arrays.copyOf(myList, myList.length - 1);
        int i = 0;
        int j = 0;
        for (T element : myList) {
            if (j != index) {
                tmp[i] = myList[j];
            } else {
                j = j + 1;
                continue;
            }
            i++;
            j++;
        }
        myList = tmp;
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
        for (T znak : myList) {
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

    public int hashCode(MyList o) {
        int result = 5 * (o != null ? o.hashCode() : 0);
        return result;
    }


    public boolean equals(MyList obj) {
        if (this.getClass() != obj.getClass() || obj.size() != this.size()) {
            return false;
        }
        if (!(this.toString().equals(obj.toString()))) {
            return false;
        }
        return true;
    }

    public String toString() {
        String result = Arrays.toString(myList);
        return result;
    }

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


    class Stack<T> {
        public final T[] array;

        public Stack(Class<T> clazz, int capacity) {
            array = (T[]) Array.newInstance(clazz, capacity);
        }
    }


}