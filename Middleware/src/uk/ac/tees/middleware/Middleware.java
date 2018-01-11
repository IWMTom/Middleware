/**
 * Middleware.java
 * A driver test harness to test operation of the Middleware.
 */

package uk.ac.tees.middleware;

public class Middleware
{
    /**
     * Creates a Portal and two User Agents - the User Agents are added
     * to the Portal. Messages are then sent between the two User Agents.
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {   
        AgentMonitor am1 = new AgentMonitor("am1");
        
        Router r1 = new Router("r1");
        r1.setAgentMonitor(am1);
        
        
        Portal p1 = new Portal("p1");
        p1.setRouter(r1);
        p1.setAgentMonitor(am1);
        
        Agent a1 = new Agent("Hartlepool Power Station");
        a1.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: " + m.getMessage());
            }
        });
        a1.setAgentMonitor(am1);
        p1.addAgent(a1);
        
        
        Portal p2 = new Portal("p2");
        p2.setRouter(r1);
        p2.setAgentMonitor(am1);
        
        Agent a2 = new Agent("Met Office Station #3");
        a2.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: " + m.getMessage());
            }
        });        
        a2.setAgentMonitor(am1);
        p2.addAgent(a2);
        
        Agent a3 = new Agent("Met Office Station #5");
        a3.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: " + m.getMessage());
            }
        });        
        a3.setAgentMonitor(am1);
        p2.addAgent(a3);        
        
        
        // Agent to Agent messaging (same portal)
        a3.sendMessage(new Message("Met Office Station #3", "Met Office Station #5", "Hello, World!"));              
        
        // Agent to Agent messaging (different portals via router)
        a1.sendMessage(new Message("Met Office Station #3", "Hartlepool Power Station", "Hello, World!")); 
        
        // Agent to non-existent Agent messaging
        a3.sendMessage(new Message("Nobody", "Met Office Station #5", "Hello, World!")); 
        
    }
}
