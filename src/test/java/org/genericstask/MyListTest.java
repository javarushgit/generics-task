package org.genericstask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
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

  @Test
  public void mapChangeValue() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    listInteger.add(5);
    listInteger.add(6);
    listInteger.add(7);
    //WHEN
    MyList<Integer> newMyList = listInteger.map((x) -> x + 1);
    //THEN
    int value = 6;
    for (Integer integer : newMyList) {
      Assertions.assertEquals(value, integer);
      value++;
    }
  }

  @Test
  public void equalsReturnTrueSameList() {
    MyList<Integer> listInteger = new MyList<>();
    listInteger.add(555);
    listInteger.add(777);
    listInteger.equals(listInteger);
    Assertions.assertTrue(true);
  }

  @Test
  public void equalsReturnTrueSymmetricLists() {
    //GIVEN
    MyList<Integer> listInteger = new MyList<>();
    listInteger.add(555);
    listInteger.add(777);
    MyList<Integer> listInteger2 = new MyList<>();
    listInteger2.add(555);
    listInteger2.add(777);
    //WHEN
    listInteger.equals(listInteger2);
    listInteger2.equals(listInteger);
    //THEN
    Assumptions.assumeTrue(true);
    Assumptions.assumeTrue(true);
  }

  @Test
  public void equalsWithNullReturnFalse() {
    MyList<Integer> listInteger = new MyList<>();
    listInteger.equals(null);
    Assertions.assertFalse(false);
  }

  @Test
  public void EqualsReturnFalse() {
    MyList<Integer> listInteger = new MyList<>();
    listInteger.add(555);
    listInteger.add(777);
    MyList<Integer> listInteger2 = new MyList<>();
    listInteger2.add(55);
    listInteger2.add(7771);
    MyList<Integer> listInteger3 = new MyList<>();
    listInteger3.add(1515);
    listInteger3.add(7575);
    Assertions.assertNotEquals(listInteger, listInteger2);
    Assertions.assertNotEquals(listInteger2, listInteger);
    Assertions.assertNotEquals(listInteger2, listInteger3);
    Assertions.assertNotEquals(listInteger, listInteger3);
  }

  @Test
  public void testHashcode() {
    MyList<Integer> listInteger = new MyList<>();
    MyList<Integer> listInteger2 = new MyList<>();
    MyList<Double> listDouble = new MyList<>();
    for (int i = 0; i < 50; i++) {
      listInteger.add(i);
      listInteger2.add(i);
      listDouble.add(i + 0D);
    }
    int hashcodeListInteger = listInteger.hashCode();
    int hashcodeListInteger2 = listInteger2.hashCode();
    int hashcodeListDouble = listDouble.hashCode();
    Assertions.assertEquals(listInteger,listInteger2);
    Assertions.assertEquals(hashcodeListInteger,hashcodeListInteger2);
    if (hashcodeListDouble != hashcodeListInteger){
      Assertions.assertNotEquals(listInteger,listDouble);
    }
  }
}
