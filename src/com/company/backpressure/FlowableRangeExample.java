package com.company.backpressure;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class FlowableRangeExample {
    public static void main(String[] args) throws InterruptedException {

        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .subscribe(v -> System.out.println(v), Throwable::printStackTrace);

        Thread.sleep(3000);

        Flowable.range(0, 10)
                .subscribe(new DisposableSubscriber<Integer>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                        request(1);
                    }

                    public void onNext(Integer v) {
                        //compute(v);
                        System.out.println("onNext "+v);
                        request(1);
                    }

                    @Override
                    public void onError(Throwable ex) {
                        System.out.println("onError "+ ex);
                        ex.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });




    }
}
