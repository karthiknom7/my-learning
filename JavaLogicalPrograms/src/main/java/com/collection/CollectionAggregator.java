package com.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionAggregator {


    public <R, T> Map<R, List<T>> group(Collection<T> inputCollection, GroupBy<R, T> groupBy){
        return null;
    }

    public <R, S, T > Map<R, Map<S, List<T>>> groupTwice(Collection<T> collection,
                                                         GroupBy<R,S> firstGroupBy, GroupBy<S, T> secondGroupBy){
        return null;
    }

    public <R, T> List<R> mapAndCollect(Collection<T> inputCollection, MapTo<R, T> mapTo){
        return null;
    }

    public <R, S, T> List<R> mapAndCollect(Collection<T> inputCollection, MapTo<S, T> firstMapTo, MapTo<R, S> secondMapTo){
        return null;
    }

    public <R, T> List<R> filterAndMapAndCollect(Collection<T> inputCollection, FilterBy<T> filterBy, MapTo<R, T> mapTo){
        return null;
    }

    public <T , K, V> Map<K, V> toMap(Collection<T> inputCollection, MapTo<K, T> keyMapper, MapTo<V, T> valueMapper){
        return null;
    }




}
