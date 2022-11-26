package org.genericstask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {
  @Test
  public void addAndGet() {
    //GEVEN
    int count = 5;
    MyList<Integer> listInteger = new MyList<>();
    //WHEN
    for (int i = 0; i < count; i++) {
      listInteger.add(i);
    }
    //THEN
    Assertions.assertEquals(count, listInteger.size());
    for (int i = 0; i < count; i++) {
      Assertions.assertEquals(i, listInteger.get(i));
    }
  }

  @Test
  public void getThrowIndexOutOfBoundException() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    //THEN
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> listInteger.get(0));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> listInteger.get(-1));
  }

  @Test
  public void removeFirstElement() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    listInteger.add(1);
    listInteger.add(2);
    listInteger.add(3);
    listInteger.add(4);
    listInteger.add(5);
    //WHEN
    listInteger.remove(0);
    //THEN
    Assertions.assertEquals(2,listInteger.get(0));
    Assertions.assertEquals(4, listInteger.size());
  }

  @Test
  public void removeMiddleElement() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    int count = 50;
    for (int i = 0; i < count; i++) {
      listInteger.add(i);
    }
    int previousNumber = listInteger.get(24);
    int nextNumber = listInteger.get(26);
    //WHEN
    listInteger.remove(25);
    //THEN
    Assertions.assertEquals(previousNumber, listInteger.get(24));
    Assertions.assertEquals(nextNumber, listInteger.get(25));
    Assertions.assertEquals(count - 1, listInteger.size());
  }

  @Test
  public void removeLastElement() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    int count = 50;
    for (int i = 0; i < count; i++) {
      listInteger.add(i);
    }
    int previousNumber = listInteger.get(48);
    //WHEN
    listInteger.remove(49);
    //THEN
    Assertions.assertEquals(previousNumber, listInteger.get(48));
    Assertions.assertEquals(count - 1, listInteger.size());
  }
}
