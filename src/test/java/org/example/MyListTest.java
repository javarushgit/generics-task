package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.util.ArrayList;
import java.util.Collections;

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
  public void hashcodeAndEquals(){
    //GIVEN
    MyList<Integer> myList1 = new MyList<>();
    MyList<Integer> myList2 = new MyList<>();
    MyList<Integer> myList3 = new MyList<>();
    //WHEN
    myList1.add(5);
    int hash1 = myList1.hashCode();
    myList2.add(5);
    int hash2 = myList1.hashCode();
    myList3.add(6);
    int hash3 = myList1.hashCode();
    //THEN
    Assertions.assertEquals(hash1, hash2);
    Assertions.assertNotEquals(hash1, hash3);
    Assertions.assertNotEquals(hash2, hash3);
/*    1. Всякий раз, когда он вызывается на одном и том же объекте более одного раза во время выполнения приложения Java,
метод hashCode должен последовательно возвращать одно и то же целое число, при условии, что информация, используемая в равных сопоставлениях объекта,
не изменяется. Это целое число не должно оставаться последовательным от одного выполнения приложения к другому выполнению того же приложения.

      2. Если два объекта равны по методу равенства, то вызов метода hashCode на каждом из двух объектов должен дать один и тот же результат.

      3. Не требуется, чтобы если два объекта являются неравными в соответствии с методом равных, то вызов метода hashCode на каждом из двух объектов
должен дать различные целые результаты. Тем не менее, программист должен знать, что получение различных целочисленных результатов для неравных
объектов может улучшить производительность хеш-таблиц.*/
  }
}
