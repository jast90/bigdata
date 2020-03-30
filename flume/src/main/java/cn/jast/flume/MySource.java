package cn.jast.flume;

import java.util.HashMap;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

public class MySource extends AbstractSource implements Configurable, PollableSource {
    private Long delay;
    private String filed;

    @Override
    public void configure(Context context) {
        delay = context.getLong("delay");
        filed = context.getString("field", "Hello");
    }

    @Override
    public Status process() throws EventDeliveryException {
        HashMap<String, String> headerMap = new HashMap<>();
        SimpleEvent event = new SimpleEvent();
        for (int i = 0; i < 5; i++) {
            event.setHeaders(headerMap);
            event.setBody((filed + i).getBytes());
            getChannelProcessor().processEvent(event);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Status.BACKOFF;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

}