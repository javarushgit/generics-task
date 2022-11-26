package org.example;

import java.util.Iterator;
import java.util.function.Function;

/*
 * Внутренийк класс
 * Объект внутреннего класса может быть создан только внутри внешнего класса.
 * У него есть доступ ко всем полям объекта.
 *
 * Внутренний статический класс
 * Объект класса может быть создан без объекта внешнего класса. Например MyList.MyIterator()
 * У него есть доступ только к статическим полям внешнего класса.
 */

public class MyList<T extends Number> implements Iterable<T> {

  private Number[] array;
  private int size;

  public MyList() {
    array = new Number[16];
    size = 0;
  }

  public void add(T o) {
    array[size] = o;
    size++;
    if (size == array.length) {
      resize();
    }
  }

  public T get(int index) {
    indexTest(index);
    return (T) array[index];
  }

  private void indexTest(int index) {
    if (index >= size || size == 0) {
      throw new IndexOutOfBoundsException();
    }
  }

  private void resize() {
     Number[] array2 = new Number[array.length *2];
     System.arraycopy(array, 0, array2, 0, size);
     array = array2;
  }

  public T remove(int index) {
    T value = get(index);
    if (index == 0) {
      Number[] array2 = new Number[array.length];
      System.arraycopy(array,0, array2, 1, size);
      array = array2;
    } else if (index !=size) {
      Number[] array2 = new Number[array.length];
      System.arraycopy(array, 0, array2, 0, index);
      System.arraycopy(array, index+1, array2, index, size - 1);
      array = array2;
    }
    size--;
    return value;
  }

  public MyList map(Function f) {
    throw new RuntimeException("Not implemented");
  }

  public int size() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }

   public class MyIterator implements Iterator<T> {
    private int index = 0;

     @Override
     public boolean hasNext() {
       return index < size;
     }

     @Override
     public T next() {
       return (T) array[index++];
     }
   }
}
