/**
 * Message.java
 * A class that describes the format of a message within the Middleware
 */

package uk.ac.tees.middleware;

public class Message
{
    private String recipient, sender, message, currentNode;
    
    /**
     * Class constructor
     * 
     * @param recipient name of the recipient agent
     * @param sender name of the sending agent
     * @param message message content
     */
    public Message(String recipient, String sender, String message)
    {
        this.recipient  = recipient;
        this.sender     = sender;
        this.message    = message;
    }
    
    /**
     * Alternative class constructor for system messages
     * 
     * @param message message content
     */
    public Message(String recipient, String sender, String message, String currentNode)
    {
        this.recipient      = recipient;
        this.sender         = sender;
        this.message        = message;
        this.currentNode    = currentNode;
    }
    
    /**
     * Gets the recipient agent
     * 
     * @return recipient agent
     */
    public String getRecipient()
    {
        return this.recipient;
    }
    
    /**
     * Gets the sending agent
     * 
     * @return sending agent
     */
    public String getSender()
    {
        return this.sender;
    }
    
    /**
     * Gets the message content
     * 
     * @return message content 
     */
    public String getMessage()
    {
        return this.message;
    }
    
    
    public String getCurrentNode()
    {
        return this.currentNode;
    }
}