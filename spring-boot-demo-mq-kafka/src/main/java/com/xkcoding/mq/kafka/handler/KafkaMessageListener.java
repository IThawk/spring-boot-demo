package com.xkcoding.mq.kafka.handler;

import com.xkcoding.mq.kafka.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author ithawk
 */
@Component
public class KafkaMessageListener {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

//     @KafkaListener(topics = "test", groupId = "test-consumer")
////     @KafkaListener(groupId = "test-consumer",
////             topicPartitions = @TopicPartition(topic = "test",
////                     partitionOffsets = {
////                             @PartitionOffset(partition = "0", initialOffset = "0")
////                     }))
//     public void listen(@Payload String message,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//         logger.info("接收消息: {}，partition：{}", message, partition);
//     }

    @KafkaListener(topics = "test", groupId = "test-consumer")
    public void listen(Message message) {
        logger.info("接收消息: {}", message);
    }

}
