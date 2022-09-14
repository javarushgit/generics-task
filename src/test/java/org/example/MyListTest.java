package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class MyListTest {
  @Test
  public void createListTest() {
    MyList<Integer> list = new MyList<>();
    MyList<Double> list1 = new MyList<>();
    MyList<Short> list2 = new MyList<>();
    MyList<Long> list3 = new MyList<>();
    MyList<Byte> list4 = new MyList<>();
    MyList<Float> list5 = new MyList<>();
  }
  @Test
  public void addTest() {
    MyList<Integer> list = new MyList<>();
    list.add(10);
    list.add(200);
    list.add(3300);
    int expectedSize = 3;
    int actualSize = list.size();
    int expectedFirst = 10;
    int expectedLast = 3300;
    Assertions.assertEquals(expectedSize, actualSize);
    Assertions.assertEquals(expectedFirst, list.get(0));
    Assertions.assertEquals(expectedLast, list.get(2));
  }
  @Test
  public void getTest() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    int expectedNum = 2;
    int actualNum = list.get(1);
    Assertions.assertEquals(expectedNum, actualNum);
  }
  @Test
  public void removeTest() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    list.remove(0);
    int expectedSize = 2;
    int actualSize = list.size();
    int expectedFirst = 20;
    int actualFirst = list.get(0);
    int expectedLast = 300;
    int actualLast = list.get(1);
    Assertions.assertEquals(expectedSize, actualSize);
    Assertions.assertEquals(expectedFirst, actualFirst);
    Assertions.assertEquals(expectedLast, actualLast);
  }
  @Test
  public void mapTest() {
    MyList<Integer> integerMyList = new MyList<>();
    integerMyList.add(1);
    integerMyList.add(20);
    integerMyList.add(300);
    MyList<Double> doubleMyList = integerMyList.map(Integer::doubleValue);
    MyList<Float> floatMyList = integerMyList.map(Integer::floatValue);
    MyList<Long> longMyList = integerMyList.map(Integer::longValue);
    MyList<Double> doubleMyList1 = longMyList.map(Long::doubleValue);
    Assertions.assertInstanceOf(Double.class, doubleMyList.get(0));
    Assertions.assertInstanceOf(Float.class, floatMyList.get(0));
    Assertions.assertInstanceOf(Long.class, longMyList.get(0));
    Assertions.assertInstanceOf(Double.class, doubleMyList1.get(0));
  }
  @Test
  public void myEqualsTestOne() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    boolean actual = list.equals(list);
    Assertions.assertEquals(true, actual);
  }
  @Test
  public void myEqualsTestTwo() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    boolean actual = list.equals(list1);
    Assertions.assertEquals(true, actual);
  }
  @Test
  public void myEqualsTestThree() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    MyList<Integer> list2 = list;
    boolean actualOne = list.equals(list1);
    boolean actualTwo = list.equals(list2);
    boolean actualFinal = list1.equals(list2);
    Assertions.assertEquals(actualOne, actualTwo);
    Assertions.assertEquals(actualOne, actualFinal);
    Assertions.assertEquals(actualTwo, actualFinal);
  }
  @Test
  public void myEqualsTestFour() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    MyList<Double> list2 = new MyList<>();
    boolean mustTrue = list.equals(list1);
    boolean mustTrue2 = list.equals(list1);
    boolean mustNotTrue = list.equals(list2);
    boolean mustNotTrue2 = list.equals(list2);
    Assertions.assertEquals(mustTrue, mustTrue2);
    Assertions.assertEquals(mustNotTrue, mustNotTrue2);
  }
  @Test
  public void myHashCodeTestOne() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    int hash = list.hashCode();
    int hash2 = list.hashCode();
    Assertions.assertEquals(hash, hash2);
  }
  @Test
  public void myHashCodeTestTwo() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    int hash = list.hashCode();
    int hash2 = list1.hashCode();
    Assertions.assertEquals(hash, hash2);
  }

  @Test
  public void iteratorTest() {
    MyList<Double> listD = new MyList<>();
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(5);
    int i = 0;
    for (Integer count : list) {
      Assertions.assertEquals(count, list.get(i++));
    }
    Iterator iterator = list.iterator();
    Iterator iteratorD = listD.iterator();
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertFalse(iteratorD.hasNext());
    Assertions.assertNotNull(iterator.next());
    Assertions.assertNull(iteratorD.next());
  }
}
