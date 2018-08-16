package com.logical.prog;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {

	public static void main(String[] args) {

		Map<Integer, String> map = new TreeMap<>();
		map.put(20, "hello");
		map.put(12, "test");
		map.put(3, "tree");
		map.put(7, "map");
		
		System.out.println(map);
		
		map = new TreeMap<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 > o2 ? -1: (o1==o2) ? 0 : 1;
			}
		});
		
		map.put(20, "hello");
		map.put(12, "test");
		map.put(3, "tree");
		map.put(7, "map");
		System.out.println(map);
		
		map = new TreeMap<>((o1,o2)-> o1>o2?-1:(o1==o2)? 0 : 1);
		
		map.put(2, "hello");
		map.put(12, "test");
		map.put(31, "tree");
		map.put(7, "map");
		System.out.println(map);
	}

}
