package com.carrot.test.controller;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TestPage {


    @Test
    void test02(){

        String str = "ABC";

        List<String> mains = mains(str);
        for (String main : mains) {
            System.out.println("main = " + main);
        }

    }

    List<String> mains(String input){
        List<String> ret = new LinkedList<>();
        char[] arr = input.toCharArray();
        backtracking(ret, arr, "");
        return ret;
    }

    void backtracking(List<String> ret, char[] arr, String str){
         //termination check
        if(str.length() == 3){
            ret.add(str);
            return;
        }
        // backtracking
        for(char d : arr){
            if(str.contains(Character.toString(d))) continue;
            backtracking(ret,arr,str+d);
        }

    }



    @Test
    void duplicatedValue(){

        HashSet<Integer> hs = new HashSet<>();
        List<Integer> ret = new LinkedList<>();

        int[] arr = new int[]{2,2,2,4,5,1,2};

        for(int value: arr){
            if(!hs.add(value)){
                if(!ret.contains(value)){
                    ret.add(value);
                }
            } else {
                hs.add(value);
            }
        }

        for (Integer integer : ret) {
            System.out.println("integer = " + integer);
        }

    }






    @Test
    void mergeSortTest1(){

        int[] arr = new int[]{1,5,2,4,7};

        mergeSort(arr);
        print(arr);
    }

    void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length-1);

    }

    void mergeSort(int[] arr, int[] temp, int start, int end){

        if(start < end){
            int mid = (start+end)/2;
            // left divide
            mergeSort(arr,temp, start,mid);
            // right divide
            mergeSort(arr,temp, mid+1, end);
            // merge
            merge(arr,temp, start, mid, end);
        }

    }

    void merge(int[] arr, int[] temp, int start, int mid, int end){

        for(int i=start; i<=end; i++){
            temp[i] = arr[i];
        }

        int part1 = start;
        int part2 = mid+1;

        int index = start;

        while(part1 <=mid && part2 <= end){
            // choose left
            if(temp[part1] <= temp[part2]){
                arr[index] = temp[part1];
                part1++;
            }
            // choose right
            else {
                arr[index] = temp[part2];
                part2++;
            }
            index++;
        }

        // if there are some values at left side
        for(int i=0; i<=mid-part1; i++){
            arr[index+i] = temp[part1+i];
        }

    }

    void print(int[] arr){
        for(int a : arr){
            System.out.println(a);
        }
    }


}
