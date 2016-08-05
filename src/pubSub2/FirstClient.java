package pubSub2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicPublisher;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import activeMQd.Sender;


public class FirstClient {
	
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageProducer producer = null;
	
	private Topic topic = null;
	private TopicPublisher publisher = null;

	public FirstClient() {

	}

	public void sendMessage() {

	try {
		//ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://localhost:61616,tcp://localhost:61626)");

	factory = new ActiveMQConnectionFactory(
			"failover:(tcp://localhost:61616,tcp://localhost:61626)");
	//System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL);
	connection = factory.createConnection();
	connection.start();
	session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	topic=session.createTopic("topic1");
	producer = session.createProducer(topic);
	String text="Hi";
	   TextMessage textMessage = session.createTextMessage(text);

       // send the message to the topic destination
      producer.send(textMessage);
      System.out.println("Sent: "+text);

	} catch (JMSException e) {
	e.printStackTrace();
	}
	}

	public static void main(String[] args) {
		FirstClient sender = new FirstClient();
		sender.sendMessage();
	}

}
