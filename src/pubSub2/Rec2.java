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

import activeMQd.Receiver;

public class Rec2 {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	
public Rec2(){}

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
System.out.println("2. Message is : " + text.getText());
}
} catch (JMSException e) {
e.printStackTrace();
}
}

public static void main(String args[]) {
	Rec2 rec=new Rec2();
	rec.receiveMessage();
	System.out.println("I got message");
}
}
