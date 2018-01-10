/**
 * MessageQueue.java
 * A wrapper of a LinkedBlockingQueue that limits interactions
 * to solely the put() and take() methods, all of type Message.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue
{
    private LinkedBlockingQueue q = new LinkedBlockingQueue();
    
    /**
     * Class constructor
     */
    public MessageQueue()
    {
        super();
    }
    
    /**
     * Puts the Message object into the queue
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    public void put(Message m) throws InterruptedException
    {
        q.put(m);
    }
    
    /**
     * Takes a Message object from the queue
     * 
     * @return Message object from queue
     * @throws InterruptedException 
     */
    public Message take() throws InterruptedException
    {
        return (Message) q.take();
    }
    
}