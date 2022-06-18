package org.pwroblew.javapurefun.funutils;

import io.vavr.Tuple2;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunUtils {

    public static <T, V> Function<T, Optional<V>> funSwitch(List<Tuple2<Predicate<T>, Function<T, V>>> list1) {
        return t -> list1.stream().dropWhile(tuple1 -> tuple1._1().negate().test(t)).findFirst().map(tuple -> tuple._2.apply(t));
    }

    public static <T, V> Function<T, V> funSwitch2(List<Tuple2<Predicate<T>, Function<T, V>>> list1, Function<T, V> v2) {
        return t -> funSwitch(list1).andThen(opV -> opV.orElseGet(() -> v2.apply(t))).apply(t);
    }

}
