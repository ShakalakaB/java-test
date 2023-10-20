package com.awesome.wow;

import com.awesome.wow.gt.list.SinglyLinkedList;
import com.awesome.wow.gt.list.SinglyLinkedListNode;
import com.awesome.wow.gt.tree.AVL;
import com.awesome.wow.gt.tree.ExternalChainingHashMap;
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

        AVL<Integer> avl = new AVL<>();
        Integer[] nums = {2, 1, 3, 0};
        for (int num : nums) {
            avl.add(num);
        }
        avl.remove(3);
        System.out.println(avl);
    }

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null
                || numbers.length < 3
                || target < (numbers[1] + numbers[2])
                || target > (numbers[1] + numbers[numbers.length - 1])) {
            return new int[]{};
        }

        int leftIndex = 1;
        int rightIndex = numbers.length - 1;
        int sum = numbers[leftIndex] + numbers[rightIndex];
        while (rightIndex > leftIndex) {
            sum = numbers[leftIndex] + numbers[rightIndex];
            if (sum == target) {
                break;
            }
            if (sum > target) {
                rightIndex = rightIndex - 1;
            } else {
                leftIndex = leftIndex + 1;
            }
        }
        return new int[]{leftIndex, rightIndex};
    }

}
