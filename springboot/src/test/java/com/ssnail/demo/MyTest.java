package com.ssnail.demo;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author pengdengwang
 * @since 2020/12/13
 */
public class MyTest {
    public static void main(String[] args) throws Exception{
//        sendMq();
        consumerMq();
    }

    public static void sendMq() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my_test_group_id");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0;i<3;i++)   {
            Message message = new Message("my_test_topic", "my_test_tag", ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message);
            System.out.printf("%s%n", result);
        }

        producer.shutdown();
    }

    public static void consumerMq() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_test_consumer_group_id");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("my_test_topic", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println(JSON.toJSONString(msgs));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }

    public static void ss(String ss) {
        switch (ss) {
            case "afjd":
                break;
            case "jkfd":
                break;
            default:
                System.out.println("a");
        }
    }
}

 enum Person {
    ZHANGSAN,
     LISI;
}
