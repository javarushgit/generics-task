package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyListTest {
  private MyList<Integer> myList;

  @BeforeEach
  public void init(){
    myList = new MyList<>();
  }

  @Test
  public void hugeLengthTest() {
    Method hugeLength = null;
    try {
      hugeLength = MyList.class.getDeclaredMethod("hugeLength", int.class, int.class);
      hugeLength.setAccessible(true);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }

    Method finalHugeLength = hugeLength;
    Assertions.assertThrows(OutOfMemoryError.class, () -> {
      try {
        finalHugeLength.invoke(myList,-1, -1);
      } catch (InvocationTargetException e) {
        throw  e.getCause();
      }
    });
    try {
      Assertions.assertEquals(Integer.MAX_VALUE - 8, finalHugeLength.invoke(myList, 1, 1));
      Assertions.assertEquals(Integer.MAX_VALUE - 7, finalHugeLength.invoke(myList, Integer.MAX_VALUE - 6, -1));
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void newLengthTest(){
    Method newLength = null;
    try {
      newLength = MyList.class.getDeclaredMethod("newLength", int.class, int.class, int.class);
      newLength.setAccessible(true);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    try {
      Assertions.assertEquals(2 , newLength.invoke(myList, 1,1,1));
      Assertions.assertEquals(Integer.MAX_VALUE - 7 , newLength.invoke(myList, Integer.MAX_VALUE - 6,-1,-1));
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void toStringTest(){
    int a = Integer.MAX_VALUE;
    int b = Integer.MIN_VALUE;
    myList.add(a);
    myList.add(b);
    Assertions.assertTrue(myList.toString().contains(String.valueOf(a)));
    Assertions.assertTrue(myList.toString().contains(String.valueOf(b)));
    Assertions.assertTrue(myList.toString().contains(""+myList.hashCode()));
  }

  @Test
  public void sizeTest(){
    Integer a = Integer.MAX_VALUE;
    Integer b = Integer.MIN_VALUE;
    Integer c = null;
    myList.add(a);
    myList.add(b);
    myList.add(c);
    Assertions.assertEquals(3, myList.size());
  }

  @Test
  public void mapTest(){
    Integer a = Integer.MAX_VALUE;
    Integer b = Integer.MIN_VALUE;
    Integer c = null;
    myList.add(a);
    myList.add(b);
    myList.add(c);
    MyList<Integer> integers = myList.map((integer -> {return integer%2;}));
    Assertions.assertEquals(a%2,integers.get(0));
    Assertions.assertEquals(b%2,integers.get(1));
    Assertions.assertNull(integers.get(2));
  }

  @Test
  public void removeTest(){
    Integer a = Integer.MAX_VALUE;
    Integer b = Integer.MIN_VALUE;
    Integer c = null;
    myList.add(a);
    myList.add(b);
    myList.add(c);
    Assertions.assertEquals(a, myList.remove(0));
    Assertions.assertEquals(2, myList.size());
    Assertions.assertEquals(b, myList.remove(0));
    Assertions.assertEquals(1, myList.size());
    Assertions.assertNull(myList.remove(0));
    Assertions.assertEquals(0, myList.size());
    Assertions.assertThrows(IndexOutOfBoundsException.class,()->myList.remove(0));
  }

  @Test
  public void equalsTest(){
    Assertions.assertTrue(myList.equals(myList));
    Assertions.assertFalse(myList.equals(new ArrayList<Integer>()));
    MyList<Integer> integers = new MyList<>();
    Assertions.assertTrue(myList.equals(integers));
    integers.add(Integer.MAX_VALUE);
    Assertions.assertFalse(myList.equals(integers));
  }

  @Test
  public void addGetTest(){
    myList.add(Integer.MAX_VALUE);
    myList.add(Integer.MIN_VALUE);
    myList.add(null);
    Assertions.assertEquals(Integer.MAX_VALUE, myList.get(0));
    Assertions.assertEquals(Integer.MIN_VALUE, myList.get(1));
    Assertions.assertNull(myList.get(2));
    Assertions.assertThrows(IndexOutOfBoundsException.class, ()->myList.get(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, ()->myList.get(3));
  }

  @Test
  public void hashCodeTest(){
    MyList<Integer> myList1 = new MyList<>();
    myList1.add(Integer.MAX_VALUE);
    myList.add(Integer.MAX_VALUE);
    Assertions.assertEquals(myList, myList1);
  }
}
