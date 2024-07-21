package disruptor.event;

import com.lmax.disruptor.EventFactory;

import static constant.EntrustBuySideEnum.BUY;
import static constant.EntrustStatusEnum.PENDING;

public class EntrustEventFactory implements EventFactory<EntrustEvent> {
    @Override
    public EntrustEvent newInstance() {
        return new EntrustEvent(PENDING, BUY, 0.0, 0, -1);
    }
}
