package disruptor.handler;

import disruptor.event.EntrustEvent;

public class ReplicationEntrustEventHandler extends EntrustEventHandler {
    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b)  {
        System.out.println(String.format("ReplicationLongEventHandler receives: %f", entrustEvent.getPrice()));
    }
}
