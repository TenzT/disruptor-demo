package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

public class QuickStart {

    private Disruptor<LongEvent> disruptor;
    private RingBuffer<LongEvent> ringBuffer;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(8);

    @Before
    public void init() {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(),
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,        // create a SingleProducerSequencer
                new BlockingWaitStrategy()
        );

        ringBuffer = disruptor.getRingBuffer();

        disruptor.handleEventsWith(new LongEventHandler())
                .then(new JournalLongEventHandler(),
                        new ReplicationLongEventHandler())
                .then(new ApplicationLongEventHandler());
        disruptor.start();
    }

    /**
     * 1. lambda way publish with EventTranslator; not a good practice for initializing new instance of
     * EventTranslator every time, which brings garbage.
     *
     * @throws InterruptedException
     */
    @Test
    public void lambdaWay() throws InterruptedException {

        LongEventTranslator translator = new LongEventTranslator();

        for (long l = 0; l < 100; l++) {
            byteBuffer.putLong(0, l);
            ringBuffer.publishEvent((event, sequence) -> event.setValue(byteBuffer.getLong(0)));

            Thread.sleep(500);
        }
    }

    /**
     * 2. using const reference is always good method to avoid frequent initialization.
     *
     * @throws InterruptedException
     */
    @Test
    public void referenceWay() throws InterruptedException {
        LongEventTranslator translator = new LongEventTranslator();

        for (long l = 0; l < 100; l++) {
            byteBuffer.putLong(0, l);

            ringBuffer.publishEvent(translator, byteBuffer);

            Thread.sleep(500);
        }
    }

    /**
     * 3. wrap up steps
     *
     * @throws InterruptedException
     */
    @Test
    public void wrapUpWay() throws InterruptedException {
        LongEventProducer eventProducer = new LongEventProducer(ringBuffer);

        for (long l = 0; l < Long.MAX_VALUE; l++) {
            byteBuffer.putLong(0, l);
            eventProducer.onData(byteBuffer);

            Thread.sleep(500);
        }
    }
}
