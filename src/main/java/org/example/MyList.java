package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

public class MyList<T extends Number> implements Iterable<T> {
    private T[] myList;
    private int myListSize;

    public MyList(T... o) {
        this.myListSize = o.length;
         this.myList = new T[myListSize]; 
            for (int i = 0; i < myListSize; i++) {
            add(o);
        }

    }

    public MyList() {
        this.myListSize = 16;
        this.myList = new T[myListSize];
    }

    public void add(Object o) {
        int count = myList.length;
        if (count < 16) {
            myList[count + 1] = (T) o;
        } else {
            resize();
            myList[count + 1] = (T) o;
        }
        throw new RuntimeException("Not implemented");
    }

    public Object get(int index) {
        if (myList.length >= index) {
            return myList[index];
        }

        throw new RuntimeException("Not implemented");
    }

    private void resize() {
        int count = myList.length % 16;
        T[] tmp = Arrays.copyOf(myList, count + 16);
        myList = tmp;
        throw new RuntimeException("Not implemented");
    }

    public void remove(int index) {
        if (myList.length < index || index < 0) {
            throw new RuntimeException("Not implemented");
        }
        T[] tmp = new T[myList.length - 1];
        for (T znak : myList) {
            int i = 0;
            int j = 0;
            if (znak != myList[index]) {
                tmp[i] = myList[j];
            } else {
                j = j + 1;
                continue;
            }
            i++;
            j++;
        }
    }

    public Object[] map(Function f) {
        if (myList == null)  {throw new RuntimeException("Not implemented");}
        Stream<T> stream=Stream.of(myList);
        stream.map(x->f.apply(x));
        return stream.toArray();
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


}
