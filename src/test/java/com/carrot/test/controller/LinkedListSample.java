package com.carrot.test.controller;

import org.junit.jupiter.api.Test;

public class LinkedListSample {

    Node header;

    LinkedListSample(){
        header = new Node();
    }

    static class Node{
        int data;
        Node next = null;
    }

    // append
    void append(int d){
        Node end = new Node();
        end.data = d;
        Node n = header;

        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }

    //delete
    void delete(int d){
        Node n = header;

        while(n.next != null){
            if(n.next.data ==d){
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    //retrieve
    void retrieve(){
        Node n = header.next;

        while(n.next != null){
            System.out.println("n.data = " + n.data);
            n = n.next;
        }
        System.out.println("n.data = " + n.data);

    }


    @Test
    void test(){
        LinkedListSample ll = new LinkedListSample();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
//        ll.retrieve();
        ll.delete(1);
        ll.retrieve();

    }

}



