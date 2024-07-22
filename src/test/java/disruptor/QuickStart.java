package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import constant.EntrustBuySideEnum;
import constant.EntrustStatusEnum;
import disruptor.event.EntrustEvent;
import disruptor.event.EntrustEventFactory;
import disruptor.event.EntrustEventTranslator;
import disruptor.handler.*;
import disruptor.me.impls.LimitOrderMatchEngine;
import disruptor.producer.EntrustEventProducer;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

public class QuickStart {

    private Disruptor<EntrustEvent> disruptor;
    private RingBuffer<EntrustEvent> ringBuffer;

    @Before
    public void init() {

        int bufferSize = 1024;

        Disruptor<EntrustEvent> disruptor = new Disruptor<>(new EntrustEventFactory(),
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,        // create a SingleProducerSequencer
                new BlockingWaitStrategy()
        );

        ringBuffer = disruptor.getRingBuffer();

        disruptor.handleEventsWith(new JournalEntrustEventHandler(),new ReplicationEntrustEventHandler())
                .then(new MatchingEventHandler(new LimitOrderMatchEngine()))
                .then(new DepthHandler())
                .then(new QuotesHandler())
        ;
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

        for (long l = 0; l < 100; l++) {
            long orderId = l;
            long quantity = l;
            double price = l;
            ringBuffer.publishEvent((entrust, sequence) -> {
                entrust.setOrderId(orderId);
                entrust.setPrice(price);
                entrust.setQuantity(quantity);
            });

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
        EntrustEventTranslator translator = new EntrustEventTranslator();
        EntrustEvent newEntrust = new EntrustEvent(EntrustStatusEnum.PENDING, EntrustBuySideEnum.BUY, 0.0, 0, -1);

        for (long l = 0; l < 100; l++) {
            newEntrust.setOrderId(l);
            newEntrust.setPrice(l);
            newEntrust.setQuantity(l);
            ringBuffer.publishEvent(translator, newEntrust);

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
        EntrustEventProducer eventProducer = new EntrustEventProducer(ringBuffer, new EntrustEventTranslator());
        EntrustEvent newEntrust = new EntrustEvent(EntrustStatusEnum.PENDING, EntrustBuySideEnum.BUY, 0.0, 0, -1);

        for (long l = 0; l < Long.MAX_VALUE; l++) {
            newEntrust.setOrderId(l);
            newEntrust.setPrice(l);
            newEntrust.setQuantity(l);
            eventProducer.onData(newEntrust);

            Thread.sleep(500);
        }
    }
}
