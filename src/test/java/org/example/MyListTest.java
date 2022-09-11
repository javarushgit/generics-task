package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {
  @Test
  public void test() {
    MyList<Integer> test=new MyList<Integer>(1,2,3);
    int expected=3;
    int actual=test.size();
    Assertions.assertEquals(expected,actual);

  }
}
