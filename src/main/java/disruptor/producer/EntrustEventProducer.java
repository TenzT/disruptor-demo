package disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import disruptor.event.EntrustEvent;

public class EntrustEventProducer {
    private final RingBuffer<EntrustEvent> ringBuffer;
    private final EventTranslatorOneArg eventTranslator;

    public EntrustEventProducer(RingBuffer<EntrustEvent> ringBuffer, EventTranslatorOneArg<EntrustEvent, EntrustEvent> eventTranslator) {
        this.ringBuffer = ringBuffer;
        this.eventTranslator = eventTranslator;
    }

    public void onData(EntrustEvent input) {
        // 2-Phase commit
        long sequence = ringBuffer.next();
        try {
            EntrustEvent event = ringBuffer.get(sequence);
            eventTranslator.translateTo(event, sequence, input);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
