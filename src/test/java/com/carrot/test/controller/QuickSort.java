package com.carrot.test.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuickSort {

    @Test
    @DisplayName("퀵정렬")
    void test01(){
//        int[] arr = {9,3,7,4,5,1};
//        int[] arr = {2,3,4,1};
        int[] arr = {0,1};

//        0,1,2,3
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }

    public void quickSort(int[] arr){
        quickSort(arr,0, arr.length-1);
    }

    public void quickSort(int[] arr, int start ,int end){
        int part2 = partition(arr,start,end);
        if(start < part2-1){
            quickSort(arr,start,part2-1);
        }
        if(part2 < end){
            quickSort(arr,part2,end);
        }
    }

    /**
     *        pivot
     *     2(s),3(v),4,1(e) ->  s->1, e,3 -> swap 2,1,4,3(v) && s->2 e->2 -> s->2, e->1 -> 2,1,3(v),4 && s->2
     *
     *     2,3(v),4,1
     */



    public int partition(int[] arr, int start, int end){
        int pivot = arr[(start+end)/2];
        while (start <= end){
            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;
            if(start<=end){
                swap(arr,start,end);
                start++;
                end--;
            }
        }

        return start;
    }


    public void swap(int[] arr, int start, int end){
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    public void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data+",");
        }
        System.out.println();
    }







}
