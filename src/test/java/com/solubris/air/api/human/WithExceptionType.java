package com.solubris.air.api.human;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class WithExceptionType<T> implements Consumer<Throwable> {
    private final Class<T> tClass;

    private WithExceptionType(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void accept(Throwable throwable) {
        assertThat(throwable).isInstanceOf(tClass);
    }

    public static <T> Consumer<Throwable> of(Class<T> tClass) {
        return new WithExceptionType<>(tClass);
    }
}
