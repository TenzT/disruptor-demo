package disruptor.handler;

import disruptor.event.EntrustEvent;

public class JournalEntrustEventHandler extends EntrustEventHandler {
    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b) {
        System.out.println(String.format("JournalEntrustEventHandler receives: %f", entrustEvent.getPrice()));
    }
}
