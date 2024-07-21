package disruptor;

public class ApplicationLongEventHandler extends LongEventHandler {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(String.format("ApplicationLongEventHandler received: %d", longEvent.getValue()));
    }
}
