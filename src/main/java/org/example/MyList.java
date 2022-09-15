package org.example;

import java.util.Arrays;
import java.util.Iterator;

public class MyList <E extends Number> implements Iterable<E> {

  private E[] array = (E[])(new Number[10]);


  E get(int index) {
    return array[index];
  }


  int size() {
    int counter = 0;
    for (E element : this) {
      if (element == null) {
        return counter;
      }
      counter++;
    }
    return counter;
  }

  boolean isEmpty() {
    return array[0] == null;
  }

  boolean contains(E number) {
    for (E element : this) {
      if (number.equals(element)) {
        return true;
      }
    }
    return false;
  }

  E[] toArray() {
    return Arrays.copyOf(array, array.length);
  }

  boolean add(E e) {
    if (array[array.length - 1] != null) {
      int position = array.length;
      array = Arrays.copyOf(array, (int)(array.length * 1.5 + 1));
      array[position] = e;
    } else {
      for (int i = 0; i < this.array.length; i++) {
        if (this.array[i] == null) {
          this.array[i] = e;
          break;
        }
      }
    }
    return true;
  }


  boolean remove(E e) {
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i] == null) {
        return false;
      }
      if (e.equals(this.array[i])) {
        E[] first = Arrays.copyOf(array, i);
        E[] second = Arrays.copyOfRange(array, i + 1, array.length - 1);
        array = Arrays.copyOf(first, array.length);
        System.arraycopy(second, 0, array, first.length, second.length);
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[ ");
    for (E element : this) {
      stringBuilder.append(element + ", ");
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    MyList<E> list = (MyList<E>) o;
    return Arrays.equals(list.array, this.array);
  }

  @Override
  public int hashCode() {

    if(this.array[0] == null) {
      return 0;
    }

    int result = 1;
    for (E element : this) {
      if (element == null) {
        break;
      }
      result = 31 * result + element.intValue();
    }
    return result;
  }

  @Override
  public Iterator<E> iterator() {
    return new MyListIterator();
  }

  /**
   * Статический вложенный класс может напрямую обращаться к статическим полям и методам внешнего класса
   * Для доступа к нестатическому вложенному классу необходимо создать экземпляр внешнего класса
   * Статический вложенный класс не имеет доступ к нестатическим полям и методам внешнего класса
   */

  private class MyListIterator implements Iterator<E> {

    int index;

    @Override
    public boolean hasNext() {
      return index < array.length && array[index] != null;
    }

    @Override
    public E next() {
      return array[index++];
    }

  }


}
