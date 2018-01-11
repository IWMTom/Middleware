/**
 * MessageListener.java
 * An abstract class that defines the template for a MessageListener.
 * The functionality of the messageReceived() method will be added to each
 * agent when they are declared.
 */

package uk.ac.tees.middleware;

public abstract class MessageListener
{
    /**
     * Abstract method that defines how to process a received message
     * 
     * @param m reference to Message
     */
    public abstract void messageReceived(Message m);
}