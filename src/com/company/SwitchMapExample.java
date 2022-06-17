package com.company;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SwitchMapExample {
    public static void main(String[] args) {

        String strings []= {"a", "b", "c", "d", "e", "f"};
        final List<String> items = new ArrayList<>();
        items.addAll(Arrays.asList(strings));

        final TestScheduler scheduler = new TestScheduler();

        Observable.fromIterable(items)
                .switchMap( s -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler);
                })
                //.toList()
                .doOnNext(System.out::println)
                .subscribe();

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }
//SwithMap() кардинально отличается от FlatMap() и ConcatMap(). Он лучше всего подходит, если вы хотите проигнорировать
// промежуточные результаты и рассмотреть последний. SwitchMap отписывается от предыдущего источника Observable всякий раз,
// когда новый элемент начинает излучать данные, тем самым всегда эмитит данные из текущего Observable.
//Чтобы лучше понять давайте приведем пример: Предположим, вы пишете приложение мгновенного поиска, которое отправляет
// поисковый запрос на сервер каждый раз, когда пользователь что-то вводит в поисковой строке. Пользователь быстро
// набирает несколько символов. При наборе каждого символа будет отправляться на сервер запрос с дополненной строкой
// на один символ. Благодаря SwitchMap() мы можем показать результат только последнего набранного запроса.
}
