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
 * @param <T> extends Number
 * @author vnsemkin@gmail.com
 * @version 1
 */
public class MyList<T extends Number> {
  private T[] myArray;
  private int myArraySize = 0;

  @SuppressWarnings("unchecked")
  public MyList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.myArray = (T[]) new Number[initialCapacity];
    } else if (initialCapacity == 0) {
      this.myArray = (T[]) new Number[]{};
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
              initialCapacity);
    }
  }

  @SuppressWarnings("unchecked")
  public MyList() {
    int DEFAULT_CAPACITY = 10;
    this.myArray = (T[]) new Number[DEFAULT_CAPACITY];
  }

  public void add(T t) {
    if (myArraySize >= myArray.length) {
      myArray = resize(myArray, true);
    }
    myArray[myArraySize] = t;
    myArraySize++;
  }

  public T get(int index) {
    return this.myArray[index];
  }

  @SuppressWarnings("unchecked")
  private T[] resize(T[] oldMyArray, boolean isGrow) {
    T[] newArray;
    if(isGrow) {
      newArray = (T[]) new Number[(int) (oldMyArray.length * 1.5)];
      System.arraycopy(oldMyArray, 0, newArray, 0, oldMyArray.length);
    }else{
      newArray = (T[]) new Number[(int) (oldMyArray.length / 1.5)];
      System.arraycopy(oldMyArray, 0, newArray, 0, (int)(oldMyArray.length/1.5));
    }
    return newArray;
  }

  @SuppressWarnings("unchecked")
  public T remove(int index) {
    T removeElement = null;
    myArray = myArraySize < myArray.length/1.5 ? resize(myArray,false):myArray;
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

  public MyList map(Function f) {
    throw new RuntimeException("Not implemented");
  }

  public int size() {
    return myArraySize;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Iterator it = this.iterator();
    stringBuilder.append("[");
      while (it.hasNext()) {
        stringBuilder.append(it.next()).append(",");
      }
    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append("]");
    return String.valueOf(stringBuilder);
  }

  public Iterator<T> iterator() {
    return new MyArrayIterator();
  }

  public class MyArrayIterator implements Iterator<T> {

    public int index;

    public MyArrayIterator() {
      index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < myArray.length && MyList.this.get(index) != null;
    }

    @Override
    public T next() {
      return (T) MyList.this.get(index++);
    }

    @Override
    public void remove() {
      if (index > 0) {
        MyList.this.remove(index - 1);
        index--;
      }
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
    return Arrays.equals(myArray,myList.myArray);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(myArray);
  }
}