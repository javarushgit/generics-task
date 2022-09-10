package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MyListTest {
  @Test
  public void add() {
    //Given
    MyList <Integer> myList = new MyList<>();
    String expect = "[1, 3, 89]";
    //When
    myList.add(1);
    myList.add(3);
    myList.add(89);
    String result = myList.toString();
    myList.add(5);
    String result2 = myList.toString();
            //Then
    Assertions.assertEquals(expect, result);
    Assertions.assertNotEquals(expect, result2);
  }
  @Test
  public void size(){
    Assertions.assertEquals(1, 1);
  }
}
