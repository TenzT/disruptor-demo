package disruptor;

public class JournalLongEventHandler extends LongEventHandler {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(String.format("JournalLongEventHandler received: %d", longEvent.getValue()));
//        Thread.sleep(1000);
    }
}
