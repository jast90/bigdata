package cn.jast.kafka;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer {
    public static void main(String[] args) {
        autoCommitOffset();
    }

    public static void autoCommitOffset() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 自动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first"));
        
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                System.out.println(String.format("offset = %s ,key = %s , value = %s"
                ,consumerRecord.offset(),consumerRecord.key(),consumerRecord.value()));
            }
        }
    }
    public static void manualCommitOffSet() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 手动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first"));
        
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                System.out.println(String.format("offset = %s ,key = %s , value = %s"
                ,consumerRecord.offset(),consumerRecord.key(),consumerRecord.value()));
            }
            // 同步提交
            // consumer.commitSync();

            // 异步提交
            consumer.commitAsync(new OffsetCommitCallback(){
            
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception != null){
                        System.err.println("Commit failed for" + offsets);
                    }
                }
            });
        }
    }
}