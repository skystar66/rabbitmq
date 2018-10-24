package com.rabbitmq.mq.topic.queue;

import com.rabbitmq.utils.QueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 队列订阅
 *
 * @author xuliang 2018/08/12
 */

@Component
public class QueueMessage {

    /**
     * 测试queue
     *
     * @return
     * @throws Exception
     */
    @Bean
    public Queue sendMessageQueue() {
        return new Queue(QueueConstant.QUEUE_MESSAGE_TEST);
    }




}
