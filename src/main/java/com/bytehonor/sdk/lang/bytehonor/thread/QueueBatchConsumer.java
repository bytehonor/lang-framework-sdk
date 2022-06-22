package com.bytehonor.sdk.lang.bytehonor.thread;

import java.util.List;

public interface QueueBatchConsumer<T> {

    public int size();
    
    public void consume(List<T> payloads);
}
