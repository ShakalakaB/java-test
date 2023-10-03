package com.awesome.wow;

import com.awesome.wow.gt.list.SinglyLinkedList;
import com.awesome.wow.gt.list.SinglyLinkedListNode;
import com.awesome.wow.gt.tree.MinHeap;

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

        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(4);
//        heap.add(5);
//        heap.add(6);
//        heap.add(7);
//        heap.add(8);
//        heap.add(9);
        System.out.println(heap.getBackingArray());
        heap.remove();
//        heap.remove();
        System.out.println(heap.getBackingArray());

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
