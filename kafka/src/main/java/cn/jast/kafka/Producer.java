package cn.jast.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties properties = new Properties();
        // kafka集群broker list
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        //
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);

        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //RecoredAccumulator缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        //
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String,String> producer =new KafkaProducer<>(properties);

        // asyncWithNoCallback(producer);
        // asyncWithallback(producer);
        sync(producer);
        
        producer.close();

    }

    /**
     * 异步发送 不带回调函数
     */
    private static void asyncWithNoCallback(KafkaProducer<String,String> producer) {
        
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String,String>("first", Integer.toString(i),Integer.toString(i)));
        }
    }

    /**
     * 异步发送 带回调函数
     */
    private static void asyncWithallback(KafkaProducer<String,String> producer) {
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String,String>("first", Integer.toString(i),Integer.toString(i)),new Callback(){
            
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception==null){
                        System.out.println("success->"+metadata.offset());
                    }else{
                        exception.printStackTrace();
                    }
                }
            });
            
        }
    }

    /**
     * 同步发送
     * @param producer
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void sync(KafkaProducer<String,String> producer) throws InterruptedException, ExecutionException {
        RecordMetadata recordMetadata;
        for (int i = 0; i < 10; i++) {
            recordMetadata = producer.send(new ProducerRecord<String,String>("first", Integer.toString(i),Integer.toString(i))).get();
            System.out.println("success->"+recordMetadata.offset());
        }
    }

}