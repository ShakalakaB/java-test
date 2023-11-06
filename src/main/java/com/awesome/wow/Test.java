package com.awesome.wow;

import com.awesome.wow.gt.CharacterComparator;
import com.awesome.wow.gt.PatternMatching;
import com.awesome.wow.gt.list.SinglyLinkedList;
import com.awesome.wow.gt.list.SinglyLinkedListNode;
import com.awesome.wow.gt.sort.Sorting;
import com.awesome.wow.gt.tree.AVL;
import com.awesome.wow.gt.tree.ExternalChainingHashMap;
import com.awesome.wow.gt.tree.MinHeap;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car2").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car1").build();
        Car car3 = Car.builder().keyA(1).keyB(3).name("car2").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car2").build();
        Car car5 = Car.builder().keyA(2).keyB(3).name("car2").build();
        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);


        PriorityQueue<Car> queue = new PriorityQueue<>(Comparator.comparing(Car::getKeyA));
        queue.offer(car1);
        queue.offer(car2);

        System.out.println(queue.poll().keyA);
    }

}
