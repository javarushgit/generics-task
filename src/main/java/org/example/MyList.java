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

  public Object get(int index) {
    throw new RuntimeException("Not implemented");
  }

  private void resize() {
     Number[] array2 = new Number[array.length *2];
     System.arraycopy(array, 0, array2, 0, size);
     array = array2;
  }

  public Object remove(int index) {
    throw new RuntimeException("Not implemented");
  }

  public MyList map(Function f) {
    throw new RuntimeException("Not implemented");
  }

  public int size() {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }

   public class MyIterator implements Iterator<T> {
     @Override
     public boolean hasNext() {
       return false;
     }

     @Override
     public E next() {
       return null;
     }
   }
}
