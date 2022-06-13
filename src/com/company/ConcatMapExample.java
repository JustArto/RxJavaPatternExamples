package com.company;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.TestScheduler;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ConcatMapExample {

    public static void main(String[] args) {

        String strings []= {"a", "b", "c", "d", "e", "f"};
        final List<String> items = new ArrayList<>();
        items.addAll(Arrays.asList(strings));

        final TestScheduler scheduler = new TestScheduler();

        Observable.fromIterable(items)
                .concatMap( s -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler);
                })
                //.toList().
                .doOnNext(System.out::println)
                .doOnNext(value -> System.out.println("before transform: " + value))
                .map(value -> value + "| adding some new data")
                .doOnNext(value -> System.out.println("after transform: " + value))
                .subscribe();

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }
//doOnNext()позволяет нам добавить некоторое дополнительное действие, происходящее всякий раз, как мы получаем новый элемент данных.

//Технически данный оператор выдает похожий с FlatMap() результат, меняется только последовательность эмиттируемых данных.
// ConcatMap() поддерживает порядок эмиссии данных и ожидает исполнения текущего Observable. Поэтому лучше использовать
// его когда необходимо обеспечить порядок выполнения задач. При этом нужно помнить, что выполнение ConcatMap()
// занимает больше времени чем FlatMap().
}
