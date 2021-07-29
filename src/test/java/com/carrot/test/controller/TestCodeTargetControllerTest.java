package com.carrot.test.controller;

import org.junit.jupiter.api.Test;

import java.util.*;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
class TestCodeTargetControllerTest {


    private void mergeSort(int[] arr){
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length-1);
    }

    private void mergeSort(int[] arr, int[] tmp, int start, int end){

        if(start<end){
            int mid = (start+end)/2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid+1, end);

            merge(arr,tmp,start,mid,end);
        }
    }

    private void merge(int[] arr, int[] tmp, int start, int mid, int end){

        for(int i=start; i<=end; i++){
            tmp[i] = arr[i];
        }

        int part1 = start;
        int part2 = mid+1;
        int index = start;

        while(part1 <= mid && part2 <= end){
            if(tmp[part1] <= tmp[part2]){
                arr[index] = tmp[part1];
                part1++;
            } else {
                arr[index] = tmp[part2];
                part2++;
            }
            index++;
        }

        for(int i=0; i<=mid-part1; i++){
            arr[index+i] = tmp[part1 +i];
        }

    }

    private void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data+",");
        }
        System.out.println();
    }


    @Test
    void test(){

        int[] arr = {9,3,7,4,5,1};

//        int[] arr = {9,3,4,7,5,0,1,6,8,2};
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }


    /**
     * 이하 퀵정렬
     */

    void quickSort2(int[] arr){
        quickSort2(arr, 0, arr.length-1);
    }

    void quickSort2(int[] arr, int start, int end){

        // right partion`s start index
        int part2 = partition(arr, start, end);

        if(start < part2-1){
            quickSort2(arr, start , part2-1);
        }
        if(part2 < end){
            quickSort2(arr, part2 , end);
        }

    }

    int partition(int[] arr, int start, int end){

        int pivot = arr[(start+end)/2];

        while(start <= end){

            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;

            if(start <= end){
                swap(arr, start ,end);
                start++;
                end--;
            }

        }

        return start;
    }

    void swap(int[] arr, int start, int end){
        int temp = arr[end];
        arr[end] = arr[start];
        arr[start] = temp;
    }

    @Test
    void test2(){
        int[] arr = {9,3,7,4,5,1};

//        int[] arr = {9,3,4,7,5,0,1,6,8,2};
        printArray(arr);
        quickSort2(arr);
        printArray(arr);
    }




}