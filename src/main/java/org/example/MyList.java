package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Реализация списка MyList для численных типов
 * @param <E>
 * @author maslovskij.98
 * @version 0.0.1
 */
public class MyList<E extends Number> {
  private final int arraySize = 10;
  private E[] array;

  private int size = 0;

  public MyList() {
    this.array = (E[])(new Number[arraySize]);
  }

  public MyIterator iterator() {
    return new MyIterator(this);
  }

  public static void main(String[] args) {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    MyList<Double> list1 = list.map(s -> (double) s);
    System.out.println(list1.get(0));
    System.out.println(list1.get(1));
    Object s = null;
    System.out.println(list.toString());
  }
  public void add(E o) {
    if (size == arraySize - 1)
      resize(arraySize * 2);
    array[size++] = o;
  }

  public E get(int index) {
    return (E)array[index];
  }

  private void resize(int i) {
    E[] newArray = Arrays.copyOf(array, i);
    array = newArray;
  }

  public E remove(int index) {
    Object removed = array[index];
    for(int i = index; i <= size; i++)
      array[i] = array[i + 1];
      array[size--] = null;
      if (size <= arraySize/2 && size > 10)
        resize(arraySize/2);
    return (E)removed;
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
  public int MyHashCode() {

  }

  /**
   * Разница между статическим вложенным классом и не статическим заключается в :
   * 1. Объект не статического вложенного класса невозможно создать без объекта внешнего класса
   * в отличие от статического.
   * 2. Статический вложенный класс имеет доступ только к статическим полям внешнего класса.
   * 3. Для создания объекта статического вложенного класса без объекта внешнего, нужно также
   * указать имя внешнего класса, а затем вложенного через "." (напрмер :
   *                                      MyList.MyIterator iterator = new MyList.MyIterator();
   */
  public class MyIterator implements Iterator {

    private MyList list;
    private int listSize;
    public MyIterator(MyList list) {
      this.list = list;
      this.listSize = list.size;
    }
    public boolean hasNext() {
      if (listSize == 0)
        return false;
      else return true;
    }
    public E next() {
      if (listSize <= 0) {
        return null;
      } else {
        return (E) list.get(list.size() - listSize--);
      }
    }
  }
}
