package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MyListTest {

    @Test
    public void testAddAndGet() {
        int count = 10;
        MyList<Integer> listInteger = new MyList<>();
        Assertions.assertEquals(0, listInteger.size());
        for (int i = 0; i < count; i++) {
            listInteger.add(i);
        }
        Assertions.assertEquals(count, listInteger.size());
        for (int i = 0; i < count; i++) {
            Assertions.assertEquals(i, listInteger.get(i));
        }
    }

    @Test
    public void testGetThrowIndexOutOfBoundException() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testRemoveFirstElement() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertEquals(5, list.size());
        list.remove(0);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void testRemoveMiddleElement() {
        MyList<Integer> list = new MyList<>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        int previousValue = list.get(49);
        int nextValue = list.get(51);
        Assertions.assertEquals(count, list.size());
        list.remove(50);
        Assertions.assertEquals(previousValue, list.get(49));
        Assertions.assertEquals(nextValue, list.get(50));
        Assertions.assertEquals(count - 1, list.size());
    }

    @Test
    public void testRemoveLastElement() {
        MyList<Integer> list = new MyList<>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        int previousValue = list.get(98);
        Assertions.assertEquals(count, list.size());
        list.remove(99);
        Assertions.assertEquals(previousValue, list.get(98));
        Assertions.assertEquals(count - 1, list.size());
    }

    @Test
    public void testForeach() {
        MyList<Integer> list = new MyList<>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        int value = 0;
        for (Integer integer : list) {
            Assertions.assertEquals(value, integer);
            value++;
        }
    }

    @Test
    public void testMapReturnDoubleList() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        MyList<Double> doubleMyList = list.map(Double::valueOf);
        Assertions.assertInstanceOf(Double.class, doubleMyList.get(0));
    }

    @Test
    public void testMapChangeValue() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        MyList<Integer> newMyList = list.map((x) -> x + 1);
        int value = 2;
        for (Integer integer : newMyList) {
            Assertions.assertEquals(value, integer);
            value++;
        }
    }

    @Test
    public void testEqualsReturnTrue() {
        MyList<Integer> listA = new MyList<>();
        listA.add(101);
        listA.add(1010);
        MyList<Integer> listB = new MyList<>();
        listB.add(101);
        listB.add(1010);
        MyList<Integer> listC = new MyList<>();
        listC.add(101);
        listC.add(1010);
        //Reflexive
        boolean reflexive = listA.equals(listA);
        Assertions.assertTrue(reflexive);
        //Symmetric
        boolean symmetricA = listA.equals(listB);
        boolean symmetricB = listB.equals(listA);
        Assertions.assertTrue(symmetricA);
        Assertions.assertTrue(symmetricB);
        //Transitive
        boolean transitiveAB = listA.equals(listB);
        boolean transitiveBC = listB.equals(listC);
        boolean transitiveAC = listA.equals(listC);
        Assertions.assertTrue(transitiveAB);
        Assertions.assertTrue(transitiveBC);
        Assertions.assertTrue(transitiveAC);
        //Consistent - надо ли?
    }

    @Test
    public void testEqualsNullReturnFalse() {
        MyList<Integer> listA = new MyList<>();
        boolean isFalse = listA.equals(null);
        Assertions.assertFalse(isFalse);
    }

    @Test
    public void testEqualsReturnFalse() {
        MyList<Integer> listA = new MyList<>();
        listA.add(101);
        listA.add(1010);
        MyList<Integer> listB = new MyList<>();
        listB.add(101);
        listB.add(1011);
        MyList<Double> listC = new MyList<>();
        listC.add(101D);
        listC.add(1010D);
        Assertions.assertNotEquals(listA, listB);
        Assertions.assertNotEquals(listB, listA);
        Assertions.assertNotEquals(listB, listC);
        Assertions.assertNotEquals(listA, listC);
    }

    @Test
    public void testHashcode() {
        MyList<Integer> listA = new MyList<>();
        MyList<Integer> listB = new MyList<>();
        MyList<Double> listD = new MyList<>();
        for (int i = 0; i < 100; i++) {
            listA.add(i);
            listB.add(i);
            listD.add(i + 0D);
        }
        int hashcodeA = listA.hashCode();
        int hashcodeB = listB.hashCode();
        int hashcodeD = listD.hashCode();
        Assertions.assertEquals(listA,listB);
        Assertions.assertEquals(hashcodeA,hashcodeB);
        if (hashcodeD != hashcodeA){
            Assertions.assertNotEquals(listA,listD);
        }
    }


}
