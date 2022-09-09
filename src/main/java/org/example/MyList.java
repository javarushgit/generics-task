package org.example;

import java.util.Arrays;
import java.util.function.Function;

public class MyList<T extends Number> {
  public final int INITIAL_CAPACITY = 10;
  public int size = 0;
  Number[] numbers;
  public MyList(){
    numbers = new Number[INITIAL_CAPACITY];
  }

  public void add(T t) {
    resize();
    numbers[size++] = t;
  }

  public Number get(int index) {
    return numbers[index];
  }

  private void resize() {
    if (size >= numbers.length){
      this.numbers = Arrays.copyOf(this.numbers,(int) (INITIAL_CAPACITY * 1.5));
    }
  }

/*  public void remove(int index) {
    T = numbers[index] = null;
    for (int i = index; i < numbers.length; i++) {
    }
  }*/

  public MyList map(Function f) {
    throw new RuntimeException("Not implemented");
  }

  public int size() {
    throw new RuntimeException("Not implemented");
  }

  public static void main(String[] args) {
    MyList<Integer> myList = new MyList<>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    myList.add(4);
    myList.add(5);
    myList.add(6);
    myList.add(7);
    myList.add(8);
    myList.add(9);
    myList.add(10);
    myList.add(11);
    myList.add(12);
    myList.add(13);
    System.out.println(myList.get(7));
    System.out.println(myList);
  }
  public String toString(){
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < numbers.length; i++) {
      if(i == 0){
        result.append("[");
      }
      if (numbers[i] != null){
        result.append(numbers[i] + ", ");
      }
      if (i == numbers.length - 1){
        result.append("\b\b]");
      }
    }
    return result.toString();
  }
}
