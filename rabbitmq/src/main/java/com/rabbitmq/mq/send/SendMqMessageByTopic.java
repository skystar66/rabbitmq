package com.rabbitmq.mq.send;

import com.rabbitmq.mq.topic.message.MessageTemplate;
import com.rabbitmq.utils.FanoutExancheConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产发送mq to server
 *
 * @author xuliang 2018/08/12
 */
@Service
public class SendMqMessageByTopic implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    private static Logger logger = LoggerFactory.getLogger(SendMqMessageByTopic.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MessageTemplate mqPushLog) {
        logger.info("发送内容：{},到队列：{}", mqPushLog, mqPushLog.getMessageQueueName());
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.convertAndSend(FanoutExancheConstant.FANOUT_EXANCHE_CONSTANT_A, mqPushLog.getMessageQueueName(), mqPushLog, mqPushLog);
    }


    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        //正常来说  交换机不正确 消息就没有进入队列当中 所以应该去插入 轮训mq表
        logger.info("消息发送失败:{}，请检查队列订阅名称是否正确", message);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        MessageTemplate messageTemplate = (MessageTemplate) correlationData;
        if (!ack) {
            logger.info("队列：{}，消息发送失败:{}", messageTemplate.getMessageQueueName(), cause);
        } else {
            logger.info("队列：{}，消息发送成功:{} ", messageTemplate.getMessageQueueName(), messageTemplate.getMessageInfo());

        }
    }


}
