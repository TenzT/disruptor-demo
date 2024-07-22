package disruptor.handler;

import disruptor.event.EntrustEvent;

public class QuotesHandler extends EntrustEventHandler {

    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b) {
        System.out.println(String.format("QuotesHandler receives: %f", entrustEvent.getPrice()));
    }
}
