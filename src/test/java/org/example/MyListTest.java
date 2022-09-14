package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyListTest {

    @Test
    public void defaultSizeCreationTest() {
        //GIVEN
        MyList myList = new MyList<>();
        List<Number> list = new ArrayList<>();
        int expectedEmptyListSize = list.size();
        //WHEN
        int actualListSize = myList.size();
        //THEN
        Assertions.assertEquals(expectedEmptyListSize, actualListSize);
    }

    @Test
    public void initialSizeCreationTest() {
        //GIVEN
        MyList myList = new MyList<>(20);
        List<Number> list = new ArrayList<>(20);
        int expectedListSize = list.size();
        //WHEN
        int actualListSize = myList.size();
        //THEN
        Assertions.assertEquals(expectedListSize, actualListSize);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void elementsAdditionTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        int expectedListSize = 10;
        int expectedFirstElement = 0;
        int expectedLastElement = 9;
        //WHEN
        int actualListSize = myList.size();
        int actualFirstElement = (int) myList.get(0);
        int actualLastElement = (int) myList.get(9);
        //THEN
        Assertions.assertEquals(expectedListSize, actualListSize);
        Assertions.assertEquals(expectedFirstElement, actualFirstElement);
        Assertions.assertEquals(expectedLastElement, actualLastElement);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void growSizeTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
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
        //WHEN
        int expectedSize = 12;
        int actualSize = myList.size();
        int expectedFirstElement = 0;
        int expectedLastElement = 11;
        int actualFirstElement = (int) myList.get(0);
        int actualLastElement = (int) myList.get(11);
        //THEN
        Assertions.assertEquals(actualSize, expectedSize);
        Assertions.assertEquals(expectedFirstElement, actualFirstElement);
        Assertions.assertEquals(expectedLastElement, actualLastElement);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void removeElementTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
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
        //WHEN
        myList.remove(0);
        int expectedListSize = 11;
        int actualListSize = myList.size();
        int expectedFirstElementValue = 1;
        int actualFirstElementValue = (int) myList.get(0);
        int expectedLastElementValue = 11;
        int actualLastElementValue = (int) myList.get(10);
        //THEN
        Assertions.assertEquals(expectedListSize, actualListSize);
        Assertions.assertEquals(expectedFirstElementValue, actualFirstElementValue);
        Assertions.assertEquals(expectedLastElementValue, actualLastElementValue);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shrinkSizeTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
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
        //WHEN
        myList.remove(0);
        myList.remove(1);
        myList.remove(2);
        myList.remove(3);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            myList.get(11);
        });
        String expectedMessage = "Index 11 out of bounds for length 10";
        String actualMessage = exception.getMessage();
        //THEN
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void iteratorTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        //WHEN
        Iterator it = myList.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (it.hasNext()) {
            stringBuilder.append(it.next()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append("]");
        String expected = "[0,1,2,3]";
        String actual = String.valueOf(stringBuilder);
        int expectedValue = 0;
        int actualValue = (int) myList.iterator().next();
        //THEN
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void equalsTest() {
        //GIVEN
        MyList myList = new MyList<>();
        MyList myList1 = new MyList<>();
        MyList myList2 = new MyList<>();
        MyList myList3 = null;
        List<Integer> myList4 = Arrays.asList(0, 1, 2, 3);
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList1.add(0);
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList2.add(1);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        //WHEN
        boolean expected = true;
        boolean actualList1 = myList.equals(myList1);
        boolean actualList2 = myList.equals(myList2);
        boolean actualList3 = myList.equals(myList3);
        boolean actualList4 = myList.equals(myList4);
        //THEN
        Assertions.assertEquals(expected, actualList1);
        Assertions.assertNotEquals(expected, actualList2);
        Assertions.assertNotEquals(expected, actualList3);
        Assertions.assertNotEquals(expected, actualList4);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void hashCodeTest() {
        //GIVEN
        MyList myList = new MyList<>();
        MyList myList1 = new MyList<>();
        MyList myList2 = new MyList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList1.add(0);
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList2.add(1);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        //WHEN
        int expectedHash = myList.hashCode();
        int actualHashList1 = myList1.hashCode();
        int actualHashList2 = myList2.hashCode();
        //THEN
        Assertions.assertEquals(expectedHash, actualHashList1);
        Assertions.assertNotEquals(expectedHash, actualHashList2);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void toStringTest() {
        //GIVEN
        MyList myList = new MyList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        //WHEN
        String expected = "[0,1,2,3]";
        String actual = String.valueOf(myList);
        //THEN
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapTest() {
        //GIVEN
        MyList<Double> listOfDoubles = new MyList<>();
        MyList<Integer> listOfIntegers = new MyList<>();
        listOfDoubles.add(9.0);
        listOfDoubles.add(7.0);
        listOfDoubles.add(7.0);
        listOfDoubles.add(8.0);
        listOfIntegers.add(9);
        listOfIntegers.add(7);
        listOfIntegers.add(7);
        listOfIntegers.add(8);
        //WHEN
        MyList actualToInteger = listOfDoubles.map(Double::intValue);
        MyList actualToDouble = listOfIntegers.map(Integer::doubleValue);
        //THEN
        Assertions.assertEquals(listOfIntegers, actualToInteger);
        Assertions.assertEquals(listOfDoubles, actualToDouble);
    }
}
