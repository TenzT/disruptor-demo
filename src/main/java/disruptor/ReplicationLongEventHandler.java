package disruptor;

public class ReplicationLongEventHandler extends LongEventHandler {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(String.format("ReplicationLongEventHandler received: %d", longEvent.getValue()));
    }
}
