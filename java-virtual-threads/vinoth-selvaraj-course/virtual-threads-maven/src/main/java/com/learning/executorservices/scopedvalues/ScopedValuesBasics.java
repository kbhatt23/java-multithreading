package com.learning.executorservices.scopedvalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScopedValuesBasics {

    private static final ScopedValue<String> SCOPED_VALUE = ScopedValue.newInstance();

    private static final Logger logger = LoggerFactory.getLogger(ScopedValuesBasics.class);
    public static void main(String[] args) {

        ScopedValuesBasics scopedValuesBasics = new ScopedValuesBasics();

        scopedValuesBasics.singleThreadedMain();
    }

    //main thread will be the single thread
    private void singleThreadedMain() {
        ScopedValue.runWhere(SCOPED_VALUE, "singleThreadedMain", this :: runnableTask);
        //runnableTask();
        String result = ScopedValue.getWhere(SCOPED_VALUE, "singleThreadedMain", this::getTask);
        logger.info("result: {}",result);
    }

    private String getTask() {
        String initData = SCOPED_VALUE.get();
        logger.info("getTask started with value: {}", initData);
        return initData.toUpperCase();
    }

    private void runnableTask() {
        //better to check ifBound earlier
        logger.info("runnableTask started with value: {}",SCOPED_VALUE.get());
    }
}
