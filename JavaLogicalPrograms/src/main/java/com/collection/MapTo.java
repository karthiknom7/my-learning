package com.collection;

public interface MapTo<R, T> {
    R apply(T t);
}
