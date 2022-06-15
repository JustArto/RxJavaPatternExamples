package com.company.backpressure;

import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

public class PublishProcessorExample {
    public static void main(String[] args) throws InterruptedException {
        PublishProcessor<Integer> source = PublishProcessor.create();

        source.observeOn(Schedulers.computation())
                .subscribe(System.out::println, Throwable::printStackTrace);

        for (int i = 0; i < 10; i++) {
            source.onNext(i);
        }
        Thread.sleep(10000);

    }
}
