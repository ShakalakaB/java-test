package com.awesome.wow;

import com.awesome.wow.gt.list.SinglyLinkedList;
import com.awesome.wow.gt.list.SinglyLinkedListNode;

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
        
//        Integer[] arr = {};
//        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] arr = {null, null};
//        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
//        for (int i = 0; i < arr.length; i++) {
//            list.addToBack(arr[i]);
//        }
//        list.addAtIndex(2, 8);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
        System.out.println(arr.length);
//        queue.enqueue(10);
//        queue.enqueue(11);
//        queue.enqueue(12);
//        queue.enqueue(13);
//        printList(list);

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
