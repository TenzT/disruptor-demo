package disruptor.me;

import disruptor.event.EntrustEvent;

public interface MatchEngine {
    /**
     * matching new entrust
     * @param entrust
     * @return
     */
    EntrustEvent doMatch(EntrustEvent entrust);
}
