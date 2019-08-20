package com.java8features;


import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
public class OptionalClassTest {



    @Test
    public void givenEmptyValue_whenCompare_thenOk() {


        String username = null;

        System.out.println("using orElse");
        Assert.assertEquals("kk",Optional.ofNullable(username).orElse(getUserName()));
        System.out.println("using orElseGet");
        Assert.assertEquals("kk",Optional.ofNullable(username).orElseGet(()-> getUserName()));

    }

    @Test
    public void givenNonEmptyValue_whenCompare_thenOk() {


        String username = "abc";

        System.out.println("using orElse");
        Assert.assertEquals("abc",Optional.ofNullable(username).orElse(getUserName()));
        System.out.println("using orElseGet");
        Assert.assertEquals("abc",Optional.ofNullable(username).orElseGet(()-> getUserName()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        String username = null;
        String result = Optional.ofNullable(username)
                .orElseThrow( () -> new IllegalArgumentException());
    }


    private String getUserName(){
        System.out.println("Called getUserName");
        return "kk";
    }

}