package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {


  @Test
  public void shouldCreateAnInstanceOfMyListWithTheData() {
    MyList<Integer> test = new MyList<>(1, 2, 3);
    int expected = 3;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.getClass()==MyList.class);
  }

  @Test
  public void ShouldCreateAnInstanceOfMyListWithDifferentTypesOfData() {
    MyList<Number> test = new MyList<>(-1, 2D, 300000000000000000L, 5F);
    int expected = 4;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.getClass()==MyList.class);
  }


  @Test
  public void shouldCreateAnEmptyMyListInstance() {
    MyList<Integer> test = new MyList<>();
    int expected = 16;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.getClass()==MyList.class);
  }

  @Test
  public void shouldAddTheValueToAnEmptyMyList() {
    MyList<Integer> test = new MyList<>();
    test.add(78);
    int expected = 78;
    int actual = (int) test.get(0);
    Assertions.assertEquals(expected, actual);
   Assertions.assertTrue(test.size()==16);
  }

  @Test
  public void shouldAddAValueToTheMyListWithTheValues() {
    MyList<Integer> test = new MyList<Integer>(1, 2, 3);
    test.add(456);
    int expected = 456;
    int actual = (int) test.get(3);
    Assertions.assertEquals(expected, actual);
    Assertions.assertFalse(test.size()!=3);
  }

  @Test
  public void shouldReturnTheValueFromMyListByIndex() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    int expected = 2;
    int actual = (int) test.get(7);
    Assertions.assertEquals(expected, actual);
    Assertions.assertFalse(test.size()!=10);
  }

  @Test
  public void shouldChangeTheSize() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.resize(-2);
    int expected = 8;
    int actual = test.size();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue((int)test.get(0)==9);

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
    Assertions.assertTrue(test.size()==9);
  }

  @Test
  public void shouldDeleteTheValueByValue() {
    MyList<Number> test = new MyList<>(9L, 8, 7f, 6, 9L, 4, 3L, 2, 1, 0);
    test.remove(9L);
    Number expected = 2;
    Number actual = test.get(5);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.size() == 8);
  }

  @Test
  public void testMethodTOMapTypeCastingDouble() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toDoubleType);
    double expected = 5;
    var actual = test.get(4);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.get(4).getClass()==Double.class);
  }


  @Test
  public void testMethodTOMapTypeCastingShort() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toShortType);
    short expected = 1;
    var actual = test.get(8);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.get(4).getClass()==Short.class);
  }

  @Test
  public void testMethodTOMapTypeCastingFloat() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    test.map(test::toFloatType);
    float expected = 9;
    var actual = test.get(0);
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.get(4).getClass()==Float.class);
  }

  @Test
  public void testMethodTOString() {
    MyList<Integer> test = new MyList<>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    String expected = "[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]";
    String actual = test.toString();
    Assertions.assertEquals(expected, actual);
   Assertions.assertTrue(actual.length()==30);
  }

  @Test
  public void testMethodTOEquals() {
    MyList<Number> test = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    MyList<Number> test1 = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    String expected = test.toString();
    String actual = test1.toString();
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(test.equals(test1));
  }

  @Test
  public void testMethodTOEqualsNegative() {
    MyList<Number> test = new MyList<Number>(9, -8, 6, 4, 3, 2, 1, 0);
    MyList<Number> test1 = new MyList<>(9, -8, 7.0, 6, 588888888888L);
    Assertions.assertFalse(test.equals(test1));
  }

  @Test
  public void testMethodTOEqualsNegative2() {
    MyList<Number> test = new MyList<>(9, -8, 6, 4, 3, 2, 1, 0);
    MyList<Integer> test1 = new MyList<>(9, -8, 0);
    Assertions.assertFalse(test.equals(test1));
  }

  @Test
  public void testMethodTOHashCode() {
    MyList<Number> test = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    int expected = Math.abs(test.get(1).hashCode() * 5);
    int actual = test.hashCode(test.get(1));
    Assertions.assertEquals(expected, actual);
    Assertions.assertFalse((test.hashCode(test.get(2)))==actual);

  }

  @Test
  public void testMethodTOHashCodNegative() {
    MyList<Number> test = new MyList<>(9, -8, 7.0, 6, 588888888888L, 4, 3, 2, 1, 0);
    int expected = test.get(9).hashCode() * 5;
    int actual = test.hashCode(test.get(9));
    Assertions.assertEquals(expected, actual);
    Assertions.assertTrue(actual == 0);
  }
}
