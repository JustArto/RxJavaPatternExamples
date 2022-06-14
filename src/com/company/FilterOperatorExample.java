package com.company;

import io.reactivex.Observable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

public class FilterOperatorExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        Observable.fromIterable(integers)
                .filter(number -> {
                    return (number  % 2 == 0);
                    // odd numbers will return false, that will cause them to be filtered
                })
                .map(i -> {
                    return Math.pow(i, 2); // take each number and multiply by power of 2
                })
                .subscribe(onNext -> {
                    System.out.println(onNext); // print out the remaining numbers
                });
    }
//You can use the filter operator to filter out items from the values stream based on a result of a predicate method.
//In other words, the items passing from the Observer to the Subscriber will be discarded based on the Function you pass
// filter, if the function returns false for a certain value, that value will be filtered out.
}
