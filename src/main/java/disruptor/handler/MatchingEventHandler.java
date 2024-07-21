package disruptor.handler;

import disruptor.event.EntrustEvent;
import disruptor.me.MatchEngine;

public class MatchingEventHandler extends EntrustEventHandler {
    private final MatchEngine matchEngine;

    public MatchingEventHandler(MatchEngine matchEngine) {
        this.matchEngine = matchEngine;
    }

    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b) {
        System.out.println(String.format("MatchEngineEventHandler receives: %f", entrustEvent.getPrice()));
        matchEngine.doMatch(entrustEvent);
        System.out.println(String.format("order(%d) filled", entrustEvent.getOrderId()));
    }
}
