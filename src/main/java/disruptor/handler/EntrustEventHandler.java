package disruptor.handler;

import com.lmax.disruptor.EventHandler;
import disruptor.event.EntrustEvent;

public abstract class EntrustEventHandler implements EventHandler<EntrustEvent> {
    @Override
    public void onEvent(EntrustEvent entrustEvent, long l, boolean b) {
        System.out.println(String.format("EntrustEventHandler receives: %f", entrustEvent.getPrice()));
    }
}
