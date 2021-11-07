package com.bsuir.counter.counter;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ServiceCallCounter {
    private final AtomicLong counter = new AtomicLong();

    public void increment() {
        counter.incrementAndGet();
    }

    public long getCount() {
        return counter == null ? 0 : counter.get();
    }
}
