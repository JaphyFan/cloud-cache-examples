package io.pivotal.pcc.helloworld;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    // Spring Caching requires a cache name to function
    @Cacheable("Hello")
    public String getHelloValue(String ignoredArgument) {
        simulateSlowDataStore();
        return Long.toString(System.nanoTime());

    }

    // Don't do this at home
    private void simulateSlowDataStore() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
