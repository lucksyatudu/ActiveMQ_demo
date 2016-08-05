package pubSub2;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Reciever {
private ConnectionFactory factory = null;
private Connection connection = null;
private Session session = null;
private Destination destination = null;
private MessageConsumer consumer = null;

public Reciever() {

}

public void receiveMessage() {
try {
factory = new ActiveMQConnectionFactory(
ActiveMQConnection.DEFAULT_BROKER_URL);
connection = factory.createConnection();
connection = factory.createConnection();
connection.start();
session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
Topic topic = session.createTopic("topic1");
//destination = session.createQueue("SAMPLEQUEUE");
consumer = session.createConsumer(topic);
Message message = consumer.receive();

if (message instanceof TextMessage) {
TextMessage text = (TextMessage) message;
System.out.println("1. Message is : " + text.getText());
}
} catch (JMSException e) {
e.printStackTrace();
}
}

public static void main(String[] args) {
Reciever receiver = new Reciever();
receiver.receiveMessage();
}
}