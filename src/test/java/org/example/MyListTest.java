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
  public void listAddTest() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    int expectedSize = 3;
    int actualSize = list.size();
    Assertions.assertEquals(expectedSize, actualSize);
  }
  @Test
  public void listGetTest() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    int expectedNum = 2;
    int actualNum = list.get(1);
    Assertions.assertEquals(expectedNum, actualNum);
  }
  @Test
  public void listRemoveTest() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.remove(0);
    int expectedSize = 2;
    int actualSize = list.size();
    int expectedNum = 2;
    int actualNum = list.get(0);
    Assertions.assertEquals(expectedSize, actualSize);
    Assertions.assertEquals(expectedNum, actualNum);
  }
  @Test
  public void changeListTypeTest() {
    MyList<Integer> integerMyList = new MyList<>();
    integerMyList.add(1);
    integerMyList.add(20);
    integerMyList.add(300);
    MyList<Double> doubleMyList = integerMyList.map(s -> (double) s);
    MyList<Float> floatMyList = integerMyList.map(s -> (float) s);
    MyList<Long> longMyList = integerMyList.map(s -> (long) s);
    MyList<Double> doubleMyList1 = longMyList.map(s -> (double) s);
    Double d = new Double(2.0);
    Float f = new Float(3f);
    Long l = new Long(250000000);
    Assertions.assertInstanceOf(d.getClass(), doubleMyList.get(0));
    Assertions.assertInstanceOf(f.getClass(), floatMyList.get(0));
    Assertions.assertInstanceOf(l.getClass(), longMyList.get(0));
    Assertions.assertInstanceOf(d.getClass(), doubleMyList1.get(0));
  }
  @Test
  public void myEqualsTestOne() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    boolean actual = list.myEquals(list);
    Assertions.assertEquals(true, actual);
  }
  @Test
  public void myEqualsTestTwo() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    boolean actual = list.myEquals(list1);
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
    boolean actualOne = list.myEquals(list1);
    boolean actualTwo = list.myEquals(list2);
    boolean actualFinal = list1.myEquals(list2);
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
    boolean mustTrue = list.myEquals(list1);
    boolean mustTrue2 = list.myEquals(list1);
    boolean mustNotTrue = list.myEquals(list2);
    boolean mustNotTrue2 = list.myEquals(list2);
    Assertions.assertEquals(mustTrue, mustTrue2);
    Assertions.assertEquals(mustNotTrue, mustNotTrue2);
  }
  @Test
  public void myHashCodeTestOne() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    int hash = list.myHashCode();
    int hash2 = list.myHashCode();
    Assertions.assertEquals(hash, hash2);
  }
  @Test
  public void myHashCodeTestTwo() {
    MyList<Integer> list = new MyList<>();
    list.add(1);
    list.add(20);
    list.add(300);
    MyList<Integer> list1 = list;
    int hash = list.myHashCode();
    int hash2 = list1.myHashCode();
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
    Iterator iterator = list.iterator();
    Iterator iteratorD = listD.iterator();
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertFalse(iteratorD.hasNext());
    Assertions.assertNotNull(iterator.next());
    Assertions.assertNull(iteratorD.next());
  }
}
