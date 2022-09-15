package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {

  @Test
  void sizeTest() {
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    myList.add(25);
    Assertions.assertEquals(2, myList.size());
  }


  @Test
  void isEmptyTest() {
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    Assertions.assertFalse(myList.isEmpty());
  }

  @Test
  void containsTest() {
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    myList.add(25);
    Assertions.assertTrue(myList.contains(25));
    Assertions.assertFalse(myList.contains(42));
  }

  @Test
  void toArrayTest() {
    Integer[] array = new Integer[10];
    array[0] = 10;
    array[1] = 25;
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    myList.add(25);
    Assertions.assertArrayEquals(array, myList.toArray());
  }

  @Test
  void addTest() {
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    myList.add(25);
    Assertions.assertEquals(10, myList.get(0));
    Assertions.assertEquals(25, myList.get(1));
    Assertions.assertNull(myList.get(2));
  }

  @Test
  void removeTest() {
    MyList<Integer> myList = new MyList<>();
    myList.add(10);
    myList.add(10);
    myList.add(10);
    myList.add(10);
    myList.add(105);
    myList.add(10);
    myList.add(10);
    myList.add(10);
    myList.add(10);
    myList.remove(105);
    Assertions.assertEquals(10, myList.get(4));
    Assertions.assertNull(myList.get(8));
    Assertions.assertFalse(myList.contains(105));
  }

  @Test
  void equalsTest1() {
    MyList<Integer> myList1 = new MyList<>();
    MyList<Integer> myList2 = myList1;
    Assertions.assertTrue(myList1.equals(myList2));
  }

  @Test
  void equalsTest2() {
    MyList<Integer> myList1 = new MyList<>();
    myList1.add(10);
    MyList<Integer> myList2 = new MyList<>();
    Assertions.assertFalse(myList1.equals(myList2));
  }

  @Test
  void equalsTest3() {
    MyList<Integer> myList1 = new MyList<>();
    myList1.add(10);
    MyList<Double> myList2 = new MyList<>();
    myList2.add(10.0);
    Assertions.assertFalse(myList1.equals(myList2));
  }

  @Test
  void equalsTest4() {
    MyList<Integer> myList1 = new MyList<>();
    MyList<Integer> myList2 = new MyList<>();
    myList1.add(10);
    myList1.add(25);
    myList2.add(10);
    myList2.add(25);
    Assertions.assertTrue(myList1.equals(myList2));
  }

  @Test
  void hashCodeTest1() {
    MyList<Integer> myList = new MyList<>();
    Assertions.assertEquals(0, myList.hashCode());
  }

  @Test
  void hashCodeTest2() {
    MyList<Integer> myList1 = new MyList<>();
    MyList<Integer> myList2 = new MyList<>();
    MyList<Integer> myList3 = new MyList<>();
    myList1.add(10);
    myList1.add(25);
    myList2.add(10);
    myList2.add(25);
    myList3.add(10);
    myList3.add(35);
    Assertions.assertEquals(1296, myList1.hashCode());
    Assertions.assertEquals(1296, myList2.hashCode());
    Assertions.assertEquals(1306, myList3.hashCode());
  }
}
