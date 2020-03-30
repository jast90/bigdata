package cn.jast.flume;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySink extends AbstractSink implements Configurable {
    
    private static final Logger log = LoggerFactory.getLogger(MySink.class);
    private String prefix;
    private String suffix;
    
    @Override
    public void configure(Context context) {
        prefix = context.getString("prefix","hello");
        suffix = context.getString("suffix");
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status;
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        Event event;
        txn.begin();
        while(true){
            event = ch.take();
            if(event!=null){
                break;
            }
        }
        try {
            log.info(prefix+new String(event.getBody())+suffix);
            txn.commit();
            status = Status.READY;
        } catch (Exception e) {
           e.printStackTrace();
           txn.rollback();
           status = Status.BACKOFF;
        }finally{
            txn.close();
        }
        return status;
    }

    

}