package uk.ac.tees.middleware;

import java.util.ArrayList;

public class AgentMonitor extends MetaAgent
{
    private ArrayList<Message> messageList = new ArrayList<>();

    public AgentMonitor(String name)
    {
        super(name);
    }

    @Override
    public synchronized void handleMessage(Message m) throws Exception
    {
        messageList.add(m);
    }
    
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