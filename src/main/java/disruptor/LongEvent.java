package disruptor;

public class LongEvent {
    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }

    public void setValue(long value) {
        this.value = value;
    }

    private long value;
}
