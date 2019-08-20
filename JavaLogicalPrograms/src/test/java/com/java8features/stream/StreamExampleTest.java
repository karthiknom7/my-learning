package com.java8features.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamExampleTest {

    private List<String> someString = Arrays.asList("abc", "xzy", "mno");

    @Test
    public void checkPeekMethodOfStream() {

        List<String> strLength = someString.stream().peek(str -> str.length()).collect(Collectors.toList());
        System.out.println(strLength);
    }
}