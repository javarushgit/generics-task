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
