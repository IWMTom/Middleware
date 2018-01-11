/**
 * AgentMonitor.java
 * An extension of MetaAgent - all messages are sent to the AgentMonitor
 * and stored in an ArrayList. The list can then be retrieved.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.ArrayList;

public class AgentMonitor extends MetaAgent
{
    private ArrayList<Message> messageList = new ArrayList<>();

    /**
     * Class constructor
     * 
     * @param name name of the MetaAgent 
     */
    public AgentMonitor(String name)
    {
        super(name);
    }

    /**
     * Adds the message to the ArrayList
     * 
     * @param m reference to Message
     * @throws Exception 
     */
    @Override
    public synchronized void handleMessage(Message m) throws Exception
    {
        messageList.add(m);
    }
    
    /**
     * Gets the full message list as a string
     * 
     * @return message list 
     */
    public synchronized String getMessageList()
    {
        StringBuilder sb = new StringBuilder();
        
        for (Message m : messageList)
        {
            sb.append("A message was handled by ");
            sb.append(m.getCurrentNode());
            sb.append(" | Sender: ");
            sb.append(m.getSender());
            sb.append(" | Recipient: ");
            sb.append(m.getRecipient());
            sb.append(" | Content: ");
            sb.append(m.getMessage());
            sb.append("\n");
        }
        
        return sb.toString();
    }
}