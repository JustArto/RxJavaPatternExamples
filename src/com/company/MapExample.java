package com.company;

import io.reactivex.Observable;

public class MapExample {

    public static void main(String[] args) {
        Observable.just("Some data!")
                .map(s -> s + " + new data")
                .subscribe(s -> System.out.println(s));

        Observable.just("Some data...")
                .doOnNext(System.out::println)
                .doOnNext(value -> System.out.println("before transform: " + value+" You can add something here but it dont affect in data"))
                .map(value -> value + "| adding some new data")
                .doOnNext(value -> System.out.println("after transform: " + value))
                .subscribe(onNext -> {
            System.out.println("onNext: "+onNext); // print out the remaining numbers
        });
        //Result: Some data! + new data
    }

//Оператор Map() преобразует каждый элемент, излучаемый Observable-ом, и эмиттит измененный элемент.
// Мы можем создать цепочку из такого количества Map(), какое посчитаем нужным для того, чтобы придать
// данным наиболее удобную и простую форму для Observer-a.
//Рассмотрите возможность использования оператора Map(), где необходимо выполнить оффлайновые операции с эмитируемыми данными.
// Например, мы получили что-то от сервера, но это не соответствует нашему требованию.
// В этом случае Map() может использоваться для изменения эмитируемых данных.

}
