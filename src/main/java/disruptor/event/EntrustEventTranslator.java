package disruptor.event;

import com.lmax.disruptor.EventTranslatorOneArg;
import constant.EntrustStatusEnum;

public class EntrustEventTranslator implements EventTranslatorOneArg<EntrustEvent, EntrustEvent> {

    @Override
    public void translateTo(EntrustEvent event, long sequence, EntrustEvent input) {
        event.setOrderId(input.getOrderId());
        event.setStatus(EntrustStatusEnum.PENDING);
        event.setPrice(input.getPrice());
        event.setQuantity(input.getQuantity());
        event.setBuySide(input.getBuySide());
    }
}
