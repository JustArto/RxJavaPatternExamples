package com.company;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlatMapExample {
    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        urls.add("url1");
        urls.add("url2");
        urls.add("url3");

        Observable.just(urls)
                .flatMap(new Function<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                })
                .subscribe(url -> System.out.println(url));

//Old realisation
//        Observable.just(urls)
//                .flatMap(new Func1<List<String>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(List<String> urls) {
//                        return Observable.from(urls);
//                    }
//                })
//                .subscribe(url -> System.out.println(url));
    }
//FlatMap() принимает на вход данные, излучаемые одним Observable , и возвращает данные, излучаемые другим Observable,
// подменяя таким образом один Observable на другой.
//Обратите внимание на то что функция возвращает Observable<String>, хотя исполняется Observable.from(urls). Выходит,
// что эмитируемые по одному строки из urls мы оборачиваем  в отдельный Observable каждую из них.
//FlatMap() может чередовать элементы при эмиссии, то есть порядок исходящих элементов не поддерживается.
// Полезен если порядок выполнения не важен, но нужно единовременное выполнение.
}
