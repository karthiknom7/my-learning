package com.logical.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectorsExample {

	public static void main(String[] args) {

		String str = "Hello Hi how are you? . Hello Hi come";

		Map<String, List<Integer>> mapingWithStream = Arrays.stream(str.split(" "))
				.collect(Collectors.toMap(e -> e.toString(), e -> {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(e.length());
					return tmp;
				}, (l1, l2) -> {
					l1.addAll(l2);
					return l1;
				}));
		System.out.println(mapingWithStream);

		String[] strArr = { "Hello Hi how are you?", "Hello Hi come", "come come dear" };
		Map<String, Integer> wordCountMap = Stream.of(strArr).flatMap(line -> Arrays.asList(line.split(" ")).stream())
				.reduce(new HashMap<String, Integer>(), (map, e) -> {
					int val = map.containsKey(e) ? map.get(e) + 1 : 1;
					map.put(e, val);
					return map;
				}, (map1, map2) -> {
					map1.putAll(map2);
					return map1;
				});

		System.out.println("Wprd count" + wordCountMap);

		Map<Integer, List<String>> grpByTest = Stream.of(str.split(" "))
				.collect(Collectors.groupingBy(element -> element.length()));
		System.out.println(grpByTest);
	}

}
