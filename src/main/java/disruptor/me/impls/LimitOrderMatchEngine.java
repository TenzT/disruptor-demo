package disruptor.me.impls;

import constant.EntrustStatusEnum;
import disruptor.event.EntrustEvent;
import disruptor.me.MatchEngine;

import java.util.Random;

/**
 * Matching engine for order with limit price
 */
public class LimitOrderMatchEngine implements MatchEngine {
    private final Random random = new Random();

    @Override
    public EntrustEvent doMatch(EntrustEvent entrust) {
        entrust.setStatus(EntrustStatusEnum.FILLED);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return entrust;
    }
}
