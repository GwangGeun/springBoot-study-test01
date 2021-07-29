package com.carrot.test.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MergeSort {



    /**
     *  1) s:0,e:3,m:1 -> left mergeSort(arr,tmp,0,1)
     *                         -> s:0,e:1,m:0 ->  left mergeSort(arr,tmp,0,0)
     *                                              -> s:0,e:0,m:0 -> die
     *
     *                                        -> right mergeSort(arr,tmp,1,1)
     *                                              -> s:1,e:1,m:1 -> die
     *
     *                                        -> merge(arr,tmp,0,0,1)
     *
     *                                        -> process die
     *
     *                  -> right mergeSort(arr,tmp,2,3)
     *                           -> s:2,e:3,m:2 -> left mergeSort(arr,tmp,2,2) -> die
     *                                          -> right mergeSort(arr,tmp,3,3) -> die
     *
     *                                          -> merge(arr,tmp,2,2,3);
     *
     *                                          -> process die
     *
     *                  -> merge(arr,tmp,0,1,3)
     *
     */
    @Test
    @DisplayName("병합정렬")
    void test01(){

//        int[] arr = {9,3,7,4,5,1};
//        int[] arr = {9,3,7,4,1};
        int[] arr = {1,2,3,5};
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }

    private void mergeSort(int[] arr){
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length-1);
    }

    /**
     *                                [left]
     *  1) mergeSort(arr,tmp,0,3) ->  mergeSort(arr,tmp,0,1)
     *                                                          [left]
     *                                                       -> mergeSort(arr,tmp,0,0)
     *                                                                                  -> die
     *                                                          [right]
     *                                                       -> mergeSort(arr,tmp,1,1)
     *                                                                                  -> die
     *
     *                                                       -> merge(arr,tmp,0,0,1)
     *
     *
     *                                [right]
     *                            ->  mergeSort(arr,tmp,2,3)
     *
     *                                                          [left]
     *                                                       -> mergeSort(arr,tmp,2,2)
     *                                                                                  -> die
     *                                                          [right]
     *                                                       -> mergeSort(arr,tmp,3,3)
     *                                                                                  -> die
     *
     *                                                       -> merge(arr,tmp,2,2,3)
     *
     *
     *
     *
     *                                [left]
     *  2) mergeSort(arr,tmp,0,3) ->  mergeSort(arr,tmp,0,1)
     *                                                          [left]
     *                                                       -> mergeSort(arr,tmp,0,0)
     *                                                                                  -> die
     *                                                          [right]
     *                                                       -> mergeSort(arr,tmp,1,1)
     *                                                                                  -> die
     *
     *                                                       -> merge(arr,tmp,0,1,1)
     *
     *
     *                                [right]
     *                            ->  mergeSort(arr,tmp,2,3)
     *
     *                                                          [left]
     *                                                       -> mergeSort(arr,tmp,2,2)
     *                                                                                  -> die
     *                                                          [right]
     *                                                       -> mergeSort(arr,tmp,3,3)
     *                                                                                  -> die
     *
     *                                                       -> merge(arr,tmp,2,2,3)
     */


    private void mergeSort(int[] arr, int[] tmp, int start, int end){

        if(start < end){
            int mid = (start+end)/2;
            // left - divide
            mergeSort(arr, tmp, start, mid);
            // right - divide
            mergeSort(arr, tmp, mid+1, end);
            // merge
            merge(arr, tmp, start, mid, end);
        }

    }

    // merge(arr,tmp,0,0,1)
    private void merge(int[] arr, int[] tmp, int start, int mid, int end){

        // temp array declare
        for(int i=start; i<=end; i++){
            tmp[i] = arr[i];
        }

        // two part scan
        int part1 = start;
        int part2 = mid+1;
        // array current location
        int index = start;

        while(part1 <= mid && part2 <=end){
            if(tmp[part1] <= tmp[part2]){
                arr[index] = tmp[part1];
                part1++;
            } else {
                arr[index] = tmp[part2];
                part2++;
            }
            index++;
        }

        // if there is a value at left space, we have to assign value to that.
        for(int i=0; i<=mid-part1; i++){
            arr[index+i] = tmp[part1+i];
        }

    }

    private void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data+",");
        }
        System.out.println();
    }

}
