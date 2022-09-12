package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {


  @Test
  public void shouldCreateAnInstanceOfMyListWithTheData() {
    MyList<Integer> test = new MyList<Integer>(1, 2, 3);
    int expected = 3;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void ShouldCreateAnInstanceOfMyListWithDifferentTypesOfData() {
    MyList<Number> test = new MyList<Number>(-1, 2D, 300000000000000000L, 5F);
    int expected = 4;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
  }


  @Test
  public void shouldCreateAnEmptyMyListInstance() {
    MyList<Integer> test = new MyList<Integer>();
    int expected = 16;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);

  }

  @Test
  public void shouldAddTheValueToAnEmptyMyList() {
    MyList<Integer> test = new MyList<Integer>();
    test.add(78);
    int expected = 78;
    int actual = (int) test.get(0);
    Assertions.assertEquals(expected, actual);
//    Assertions.assertTrue(test.get(0) == 78);
  }

  @Test
  public void shouldAddAValueToTheMyListWithTheValues() {
    MyList<Integer> test = new MyList<Integer>(1, 2, 3);
    test.add(456);
    int expected = 456;
    int actual = (int) test.get(3);
    Assertions.assertEquals(expected, actual);
//    Assertions.assertTrue(test.get(3) == 456);
  }

  @Test
  public void shouldReturnTheValueFromMyListByIndex() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    int expected = 2;
    int actual = (int) test.get(7);
    Assertions.assertEquals(expected, actual);
//    Assertions.assertTrue(test.get(7) == 2);
  }

  @Test
  public void shouldChangeTheSize() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.resize(-2);
    int expected = 8;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
//    Assertions.assertTrue(test.get(7) = 2);

  }

  @Test
  public void shouldChangeTheSize2() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.resize(-10);
    int expected = 0;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test != null);

  }

  @Test
  public void shouldDeleteTheValueByIndex() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.remove(3);
    int expected = 5;
    int actual = (int) test.get(3);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test != null);
  }


  @Test
  public void shouldApplyTheFunctionToEachMyListElement() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toDoubleType);
    double expected = 5;
    var actual = test.get(4);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test != null);
  }


  @Test
  public void shouldApplyTheFunctionToEachMyListElement2() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toShortType);
    short expected = 1;
    var actual = test.get(8);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test != null);
  }

  @Test
  public void shouldApplyTheFunctionToEachMyListElement3() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toFloatType);
    float expected = 9;
    var actual = test.get(0);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test != null);
  }

  @Test
  public void tOString() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    String expected = "[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]";
    String actual = test.toString();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void tOEquals() {
    MyList<Number> test = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    MyList<Number> test1 = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    String expected = test.toString();
    String actual = test1.toString();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.equals(test1));
  }

  @Test
  public void tOhashCode() {
    MyList<Number> test = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    int expected = test.hashCode() * 5;
    int actual = test.hashCode(test);
    Assertions.assertEquals(expected, actual);
//    Assertions.assertTrue(test.equals(test1));
  }


}
