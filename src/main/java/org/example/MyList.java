package org.example;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyList<T extends Number> implements Iterable<T>{
  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
  private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
  private transient Object[] elementData;
  private transient int modCount = 0;
  private int size = 0;

  public MyList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }

  public void add(T o) {
    modCount++;
    add(o, elementData, size);
  }

  public T get(int index) {
    Objects.checkIndex(index, size);
    return elementData(index);
  }

  private void resize() {
    modCount++;
    if (size < elementData.length) {
      elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrays.copyOf(elementData, size);
    }
  }

  public Object remove(int index) {
    Objects.checkIndex(index, size);
    final Object[] es = elementData;
    @SuppressWarnings("unchecked") T oldValue = (T) es[index];
    fastRemove(es, index);
    return oldValue;
  }

  public MyList<T> map(Function<T,T> f) {
    MyList<T> myList = new MyList<>();
    for (int i = 0; i < this.size(); i++) {
      if (elementData[i] == null){
        myList.add(null);
      } else {
        myList.add(f.apply((T) elementData[i]));
      }
    }
    return myList;
  }

  public int size() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {return true;}
    if (!(o instanceof MyList)) {return false;}
    final int expectedModCount = modCount;
    // ArrayList can be subclassed and given arbitrary behavior, but we can
    // still deal with the common case where o is ArrayList precisely
    boolean equal = (o.getClass() == MyList.class)
            ? equalsMyList((MyList<T>) o)
            : equalsRange((MyList<T>) o, 0, size);

    checkForComodification(expectedModCount);
    return equal;
  }

  private boolean equalsRange(MyList<T> other, int from, int to) {
    final Object[] es = elementData;
    if (to > es.length) {
      throw new ConcurrentModificationException();
    }
    Iterator<T> oit = other.iterator();
    for (; from < to; from++) {
      if (!oit.hasNext() || !Objects.equals(es[from], oit.next())) {
        return false;
      }
    }
    return !oit.hasNext();
  }

  private boolean equalsMyList(MyList<?> other) {
    final int otherModCount = other.modCount;
    final int s = size;
    boolean equal;
    if (equal = (s == other.size)) {
      final Object[] otherEs = other.elementData;
      final Object[] es = elementData;
      if (s > es.length || s > otherEs.length) {
        throw new ConcurrentModificationException();
      }
      for (int i = 0; i < s; i++) {
        if (!Objects.equals(es[i], otherEs[i])) {
          equal = false;
          break;
        }
      }
    }
    other.checkForComodification(otherModCount);
    return equal;
  }

  @Override
  public int hashCode() {
    int expectedModCount = modCount;
    int hash = hashCodeRange(0, size);
    checkForComodification(expectedModCount);
    return hash;
  }

  private int hashCodeRange(int from, int to) {
    final Object[] es = elementData;
    if (to > es.length) {
      throw new ConcurrentModificationException();
    }
    int hashCode = 1;
    for (int i = from; i < to; i++) {
      Object e = es[i];
      hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
    }
    return hashCode;
  }

  private void checkForComodification(final int expectedModCount) {
    if (modCount != expectedModCount) {
      throw new ConcurrentModificationException();
    }
  }

  @Override
  public String toString() {
    resize();
    return "MyList@" + hashCode() + Arrays.toString(elementData);
  }

  private void add(T e, Object[] elementData, int s) {
    if (s == elementData.length)
      elementData = grow();
    elementData[s] = e;
    size = s + 1;
  }

  private Object[] grow() {
    return grow(size + 1);
  }

  private Object[] grow(int minCapacity) {
    int oldCapacity = elementData.length;
    if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      int newCapacity = newLength(oldCapacity,
              minCapacity - oldCapacity, /* minimum growth */
              oldCapacity >> 1           /* preferred growth */);
      return elementData = Arrays.copyOf(elementData, newCapacity);
    } else {
      return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
  }

  private T elementData(int index) {
    return (T) elementData[index];
  }

  private T elementAt(Object[] es, int index) {
    return (T) es[index];
  }

  private void fastRemove(Object[] es, int i) {
    modCount++;
    final int newSize;
    if ((newSize = size - 1) > i)
      System.arraycopy(es, i + 1, es, i, newSize - i);
    es[size = newSize] = null;
  }


  public static void main(String[] args) {
    MyList<Integer> integers = new MyList<>();
    integers.add(10);
    integers.add(12345);
    integers.add(null);
    integers.add(2342);
    integers.add(100);
    System.out.println(integers.map(integer -> {return integer * 100;}));
  }

  private int newLength(int oldLength, int minGrowth, int prefGrowth) {
    int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
    if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
      return prefLength;
    } else {
      return hugeLength(oldLength, minGrowth);
    }
  }

  private int hugeLength(int oldLength, int minGrowth) {
    int minLength = oldLength + minGrowth;
    if (minLength < 0) { // overflow
      throw new OutOfMemoryError(
              "Required array length " + oldLength + " + " + minGrowth + " is too large");
    } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
      return SOFT_MAX_ARRAY_LENGTH;
    } else {
      return minLength;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new MyList.Itr();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<T> spliterator() {
    return Iterable.super.spliterator();
  }

  /**
   *  static(nested) inner class has not reference(this) on the outer class instance,
   *  but easy inner class has this reference
   */
  private class Itr implements Iterator<T> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = modCount;

    // prevent creating a synthetic constructor
    Itr() {}

    public boolean hasNext() {
      return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public T next() {
      checkForComodification();
      int i = cursor;
      if (i >= size)
        throw new NoSuchElementException();
      Object[] elementData = MyList.this.elementData;
      if (i >= elementData.length)
        throw new ConcurrentModificationException();
      cursor = i + 1;
      return (T) elementData[lastRet = i];
    }

    public void remove() {
      if (lastRet < 0)
        throw new IllegalStateException();
      checkForComodification();

      try {
        MyList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
      Objects.requireNonNull(action);
      final int size = MyList.this.size;
      int i = cursor;
      if (i < size) {
        final Object[] es = elementData;
        if (i >= es.length)
          throw new ConcurrentModificationException();
        for (; i < size && modCount == expectedModCount; i++)
          action.accept(elementAt(es, i));
        // update once at end to reduce heap write traffic
        cursor = i;
        lastRet = i - 1;
        checkForComodification();
      }
    }

    private void checkForComodification() {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
    }
  }
}
