package com.carrot.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

//c
class TestApplicationTests {

	static class WordCnt{
		List<Integer> line;
		int cnt;
		public WordCnt(int curLine){
			this.line = new LinkedList<>(Collections.singletonList(curLine));
			this.cnt = 1;
		}
		public void addLine(int curLine){
			line.add(curLine);
		}
	}

	// line, occurrence, words(key)
	@Test
	void test(){

		Map<String, WordCnt> hm = new HashMap<>();

		List<String> inputs = List.of("This is the best solution", "These are the best solutions");

		// line
		for(int i=0; i<inputs.size(); i++){

			String[] sentence = inputs.get(i).split(" ");

			for(int y=0; y<sentence.length; y++){
				if(hm.containsKey(sentence[y])){
					WordCnt wordCnt = hm.get(sentence[y]);
					wordCnt.cnt++;
					wordCnt.addLine(i+1);
				} else {
					hm.put(sentence[y], new WordCnt(i+1));
				}
			}

		}

		for(Map.Entry<String,WordCnt> entry: hm.entrySet()){
			System.out.println(" word : " + entry.getKey() + " 	cnt : " + entry.getValue().cnt + " 	line : " + entry.getValue().line);
		}

	}

	public class ListNode{
		int val;
		ListNode next;
		ListNode(){}
		ListNode(int val){this.val = val;}
		ListNode(int val, ListNode next){this.val=val; this.next=next;}
	}

	@Test
	void reverseLinkedList(ListNode head) {

		ListNode ret = null;

		while(head.next != null){
			ListNode tmp = head.next;
			//
			head.next = ret;
			ret = head;

			head = tmp;
		}

	}

	public int lengthOfLIS(int[] nums) {


		int[] dp = new int[nums.length];
		int len = 0;

		for(int x : nums) {
			int i = Arrays.binarySearch(dp, 0, len, x); // logN
			System.out.println("i = " + i);
			if(i < 0) i = -(i + 1);
			dp[i] = x;
			if(i == len) len++;
//			for(int d:dp){
//				System.out.println(d);
//			}
//			System.out.println();

		}



		return len;


	}

	@Test
	void tests(){
		int[][] arr = { { 1, 2 }, { 3, 4 }, { 5, 6, 7 } };
		System.out.println(arr.length);
		System.out.println(arr[0].length);
	}

	@Test
	void test02(){
		int a = 4_5;
		System.out.println("a = " + a);
	}

	/**
	 *
	 * p1, p2 => 내용물이 같다
	 *
	 * equals (x) & hashcode == 1
	 *
	 *
	 * Hash 와 관련된 collection :::  1. hashcode() -> 2. equals()
	 *
	 */
	@Test
	void test03(){

		Person person1 = new Person();
		person1.setAge(1);
		person1.setName("a");

		Person person2 = new Person();
		person2.setAge(1);
		person2.setName("a");

		boolean equals = person1.equals(person2);
		System.out.println("equals = " + equals);

//		Map<Person, Integer> hm = new HashMap<>();
//		hm.put(person1, 1);
//		hm.put(person2, 2);
//
//		System.out.println(person1.hashCode());
//		System.out.println(person2.hashCode());
//		System.out.println(hm.size());

	}



}
