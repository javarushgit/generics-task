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
}
