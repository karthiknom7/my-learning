package com.logical.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayCopyExample {

	public static void main(String[] args) {
		String[] strArr = new String[2];
		strArr[0] = "hello";
		strArr[1] = "Karthik";
		System.out.println("before " + Arrays.toString(strArr));
		strArr = add(strArr, "super" , "great", "done");
		
		System.out.println("after " + Arrays.toString(strArr));
		// convert to fixed list. we can not modify it
		List<String> testList = Arrays.asList(strArr);
		System.out.println(testList);
		List<String> testList2 = new ArrayList<String>(testList);
		testList2.add("adding");
		System.out.println(testList2);
		// convert list to array
		
		strArr = testList2.toArray(new String[testList.size()]);
		System.out.println(Arrays.toString(strArr));
		testList2.add("adding 2");
		System.out.println(testList2);
		System.out.println(Arrays.toString(strArr));
		// sort using comparator 
		testList2.sort((el1,el2)->{return el1.compareToIgnoreCase(el2);});
		System.out.println("Sort : " + testList2);
		// stream from array
		testList2 = Arrays.stream(strArr).collect(Collectors.toList());
		testList2.add("hello1");
		System.out.println("Check modfi " + testList2);
		testList2 = Stream.of(strArr).collect(Collectors.toList());
		Stream.of(1,2,3,4).collect(Collectors.toList());
		
		
		// To Map 
		
		Map<String, Integer> myMap = testList2.stream().collect(Collectors.toMap(element -> element, element -> element.length()) );
		System.out.println("Map" + myMap);

	}
	
	public static String[] add(String[] source, String ...elements){
		String[] temp = new String[source.length + elements.length];
		System.arraycopy(source, 0, temp, 0, source.length);
		System.out.println("Array copy " + Arrays.toString(temp));
		for(int i = 0; i<elements.length; i++){
			temp[source.length +i] = elements[i];
		}
		return temp;
	}

}
