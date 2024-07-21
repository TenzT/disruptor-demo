package disruptor.handler;

import disruptor.event.EntrustEvent;

public class DepthHandler extends EntrustEventHandler {

    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b) {
        System.out.println(String.format("DepthHandler receives: %f", entrustEvent.getPrice()));
    }
}
