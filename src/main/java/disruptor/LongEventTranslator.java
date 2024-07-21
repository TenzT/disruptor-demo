package disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

import java.nio.ByteBuffer;

public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, ByteBuffer> {

    @Override
    public void translateTo(LongEvent event, long sequence, ByteBuffer input) {
        event.setValue(input.getLong(0));
    }
}
