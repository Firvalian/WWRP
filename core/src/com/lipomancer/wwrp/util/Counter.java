package com.lipomancer.wwrp.util;

/**
 * A synchronized counter.
 */
public class Counter {

    private int value;

    public Counter(int value) {
        this.value = value;
    }

    public Counter() {
        this(0);
    }

    public synchronized int get() {
        value += 1;

        if (value < 0) {
            throw new IllegalStateException("Counter overflew integer limit.");
        }

        return value;
    }
}
