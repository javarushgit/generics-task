package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class MyListTest<T>{
  @Test
  public void add() {
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    String expectOutput1 = "[1]";
    String expectOutput2 = "[1, 2]";
    String expectOutput3 = "[1, 2, 3]";
    //WHEN
    myList.add(1);
    String resultOutput1 = myList.toString();
    myList.add(2);
    String resultOutput2 = myList.toString();
    myList.add(3);
    String resultOutput3 = myList.toString();
    //THEN
    Assertions.assertEquals(expectOutput1, resultOutput1);
    Assertions.assertEquals(expectOutput2, resultOutput2);
    Assertions.assertEquals(expectOutput3, resultOutput3);
  }
  @Test
  public void addByIndex() {
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    String expectOutput1 = "[1, 111, 2, 3]";
    String expectOutput2 = "[1, 111, 2, 3, 222]";
    //WHEN
    myList.add(1, 111);
    String resultOutput1 = myList.toString();
    myList.add(4, 222);
    String resultOutput2 = myList.toString();
    Exception thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.add(6, 999);
    });
    Exception thrown2 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.add(-1, 666);
    });
    //THEN
    Assertions.assertEquals(expectOutput1, resultOutput1);
    Assertions.assertEquals(expectOutput2, resultOutput2);
    Assertions.assertEquals("Index 6 out of bounds for length 5", thrown1.getMessage());
    Assertions.assertEquals("Index -1 out of bounds for length 5", thrown2.getMessage());
  }

  @Test
  public void size() {
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    int expectSize1 = 2;
    int expectSize2 = 3;
    //WHEN
    myList.add(1);
    myList.add(3);
    int result1 = myList.size();
    myList.add(4);
    int result2 = myList.size();
    //THEN
    Assertions.assertEquals(expectSize1, result1);
    Assertions.assertEquals(expectSize2, result2);
  }
  @Test
  public void isEmpty(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    boolean expect = true;
    //WHEN
    boolean result1 = myList.isEmpty();
    myList.add(1);
    boolean result2 = myList.isEmpty();
    //THEN
    Assertions.assertEquals(expect, result1);
    Assertions.assertNotEquals(expect, result2);
  }
  @Test
  public void contains(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    boolean expect = true;
    //WHEN
    myList.add(1);
    myList.add(3);
    boolean result1 = myList.contains(3);
    boolean result2 = myList.contains(2);
    //THEN
    Assertions.assertEquals(expect, result1);
    Assertions.assertNotEquals(expect, result2);
  }
  @Test
  public void set(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    String expectOutput1 = "[1, 222, 3]";
    String expectOutput2 = "[111, 222, 3]";
    //WHEN
    myList.set(1, 222);
    String resultOutput1 = myList.toString();
    myList.set(0, 111);
    String resultOutput2 = myList.toString();
    Exception thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.set(3, 999);
    });
    Exception thrown2 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.set(-1, 666);
    });
    //THEN
    Assertions.assertEquals(expectOutput1, resultOutput1);
    Assertions.assertEquals(expectOutput2, resultOutput2);
    Assertions.assertEquals("Index 3 out of bounds for length 3", thrown1.getMessage());
    Assertions.assertEquals("Index -1 out of bounds for length 3", thrown2.getMessage());
  }
  @Test
  public void get(){
    //GIVEN
    MyList<Double> myList = new MyList<>();
    myList.add(5.0);
    myList.add(4.9);
    Double expect1 = 5.0;
    Double expect2 = 4.9;
    //WHEN
    Double result1 = myList.get(0);
    Double result2 = myList.get(1);
    Exception thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.get(2);
    });
    Exception thrown2 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.get(-1);
    });
    //THEN
    Assertions.assertEquals(expect1, result1);
    Assertions.assertEquals(expect2, result2);
    Assertions.assertEquals("Index 2 out of bounds for length 2", thrown1.getMessage());
    Assertions.assertEquals("Index -1 out of bounds for length 2", thrown2.getMessage());
  }
  @Test
  public void removeByIndex(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    myList.add(4);
    myList.add(5);
    String expectOutput1 = "[1, 3, 4, 5]";
    String expectOutput2 = "[1, 3, 5]";
    int expectedReturnNum1 = 2;
    int expectedReturnNum2 = 4;
    //WHEN
    int resultReturnNum1 = myList.remove(1);
    String resultOutput2 = myList.toString();
    int resultReturnNum2 = myList.remove(2);
    String resultOutput4 = myList.toString();
    Exception thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.remove(3);
    });
    Exception thrown2 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      myList.remove(-1);
    });
    //THEN
    Assertions.assertEquals(expectOutput1, resultOutput2);
    Assertions.assertEquals(expectOutput2, resultOutput4);
    Assertions.assertEquals(expectedReturnNum1, resultReturnNum1);
    Assertions.assertEquals(expectedReturnNum2, resultReturnNum2);
    Assertions.assertEquals("Index 3 out of bounds for length 3", thrown1.getMessage());
    Assertions.assertEquals("Index -1 out of bounds for length 3", thrown2.getMessage());
  }
  @Test
  public void removeByNumber(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    myList.add(4);
    myList.add(5);
    String expectOutput = "[1, 3, 4, 5]";
    boolean expectedReturn = true;
    //WHEN
    boolean resultReturn1 = myList.remove(Integer.valueOf(2));
    String resultOutput1 = myList.toString();
    boolean resultReturn2 = myList.remove(Integer.valueOf(2));
    String resultOutput2 = myList.toString();
    //THEN
    Assertions.assertEquals(expectOutput, resultOutput1);
    Assertions.assertEquals(expectOutput, resultOutput2);
    Assertions.assertEquals(expectedReturn, resultReturn1);
    Assertions.assertNotEquals(expectedReturn, resultReturn2);
  }
  @Test
  public void map(){
    //GIVEN
    MyList<Integer> myList1 = new MyList<>();
    myList1.add(1);
    myList1.add(3);
    myList1.add(5);
    myList1.add(7);
    //WHEN
    MyList<Double> myList2 = myList1.map(Double::valueOf);
    String result = "[1.0, 3.0, 5.0, 7.0]";
    //THEN
    Assertions.assertEquals(result, myList2.toString());
  }
  @Test
  public void hashcode(){
    //GIVEN
    MyList<Integer> list1 = new MyList<>();
    MyList<Integer> list2 = new MyList<>();
    MyList<Integer> list3 = new MyList<>();
    //WHEN
    list1.add(1);
    list1.add(2);
    list2.add(1);
    list2.add(2);
    list3.add(3);
    list3.add(4);
    //THEN
    Assertions.assertEquals(list1.hashCode(), list2.hashCode());
    Assertions.assertNotEquals(list2.hashCode(), list3.hashCode());
    Assertions.assertNotEquals(list1.hashCode(), list3.hashCode());
  }
  @Test
  public void equals(){
    //GIVEN
    MyList<Integer> myList1 = new MyList<>();
    MyList<Integer> myList2 = new MyList<>();
    ArrayList<Integer> list = new ArrayList<>();
    boolean except = true;
    //WHEN
    myList1.add(1);
    myList1.add(2);
    myList2.add(1);
    myList2.add(2);
    //THEN
    Assertions.assertEquals(except, myList1.equals(myList1));
    Assertions.assertEquals(!except, myList1.equals(null));
    Assertions.assertEquals(!except, myList1.equals(list));
    Assertions.assertEquals(except, myList1.equals(myList2));

/*
      MyList<? extends Number> myList = (MyList<? extends Number>) o;
      return Arrays.equals(numbers, myList.numbers);
    }*/
  }
  @Test
  public void iterator(){
    //GIVEN
    MyList<Integer> myList = new MyList<>();
    String expect = "1234";
    StringBuilder stringBuilder = new StringBuilder();
    //WHEN
    myList.add(1);
    myList.add(2);
    myList.add(3);
    myList.add(4);
    for (Number number : myList) {
      stringBuilder.append(number);
    }
    String result = stringBuilder.toString();
    //THEN
    Assertions.assertEquals(expect, result);
  }
}
