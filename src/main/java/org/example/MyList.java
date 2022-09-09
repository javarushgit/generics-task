package org.example;

import java.util.function.Function;

public class MyList {
  public void add(Object o) {
    throw new RuntimeException("Not implemented");
  }

  public Object get(int index) {
    throw new RuntimeException("Not implemented");
  }

  private void resize() {
    throw new RuntimeException("Not implemented");
  }

  public Object remove(int index) {
    throw new RuntimeException("Not implemented");
  }

  public MyList map(Function f) {
    throw new RuntimeException("Not implemented");
  }

  public int size() {
    throw new RuntimeException("Not implemented");
  }
}
