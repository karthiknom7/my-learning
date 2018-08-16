package com.logical.prog;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicateWordsInString {

	public static void main(String[] args) {

		String str = "Hello Hi how are you? . Hello Hi come";
		System.out.println(str);
		str = Stream.of(str.split(" ")).filter(str1-> !str1.contains(".")).distinct().collect(Collectors.joining(",","[","]"));
		System.out.println(str);
		
	}

}
