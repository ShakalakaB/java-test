package com.awesome.wow;

import com.awesome.wow.gt.ArrayList;
import com.awesome.wow.gt.SinglyLinkedList;
import com.awesome.wow.gt.SinglyLinkedListNode;

import java.util.*;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car2").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car1").build();
        Car car3 = Car.builder().keyA(1).keyB(3).name("car2").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car2").build();
        Car car5 = Car.builder().keyA(2).keyB(3).name("car2").build();
        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);

        Integer[] arr = {1, 2, 3};
//        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.addToBack(arr[i]);
        }
        System.out.println(list.removeFromFront());
        System.out.println(list.removeFromBack());
        System.out.println(list.removeFromBack());
        System.out.println(list.removeFromBack());
        printList(list);

    }

    private static void printList(SinglyLinkedList<Integer> list) {
        StringBuilder output = new StringBuilder();
        SinglyLinkedListNode<Integer> current = list.getHead();
        while (current != null) {
            output.append(current.getData()).append(", ");
            current = current.getNext();
        }
        System.out.println(output);
    }

}
