package asynchronous;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver implements MessageListener  {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	Thread idleThread = null;
	
	public void recieve(){
	try {
		factory = new ActiveMQConnectionFactory(
		ActiveMQConnection.DEFAULT_BROKER_URL);
		connection = factory.createConnection();
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("SAMPLEQUEUE");
		consumer = session.createConsumer(destination);
		consumer.setMessageListener(this);
		Runnable idleRunnable = new Runnable() {
			@Override
			public void run() {
			while (true) {
			// stay here idle..the program should not exit till the
			// response receives..Thats why...
			}
			}

			};
			idleThread = new Thread(idleRunnable, "idleThread");
			idleThread.start();
		} catch (JMSException e) {
		e.printStackTrace();
		}
	}
	
	public Receiver() {

	}

	
	@Override
	public void onMessage(Message message) { 
	    TextMessage msg = null; 
	    try { 
	        if (message instanceof TextMessage) { 
	            msg = (TextMessage) message; 
	             System.out.println("Reading message: " + msg.getText()); 
	        } else { 
	             System.out.println("Message is not a " + "TextMessage"); 
	        } 
	    } catch (JMSException e) { 
	        System.out.println("JMSException in onMessage(): " + e.toString()); 
	    } catch (Throwable t) { 
	        System.out.println("Exception in onMessage():" + t.getMessage()); 
	    }
	}


	public static void main(String[] args) {
	Receiver receiver = new Receiver();
	receiver.recieve();
	}
}
