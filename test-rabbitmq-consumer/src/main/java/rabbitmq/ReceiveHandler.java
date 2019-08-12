package rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    @RabbitListener(queues = {Config.QUEUE_INFORM_EMAIL})
    public void receive_email(String mag){
        System.out.println(mag);
    }

    @RabbitListener(queues = {Config.QUEUE_INFORM_SMS})
    public void receive_sms(String mag){
        System.out.println(mag);
    }
}
