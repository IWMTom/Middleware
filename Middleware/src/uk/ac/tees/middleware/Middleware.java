/**
 * Middleware.java
 * A driver test harness to test operation of the Middleware.
 * 
 * @author Tom Wilson (S605130)
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
        Router r1 = new Router("r1");
        
        
        Portal p1 = new Portal("p1");
        p1.setRouter(r1);
        
        Agent a1 = new Agent("Hartlepool Power Station");
        a1.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: " + m.getMessage());
            }
        });
        p1.addAgent(a1);
        
        
        Portal p2 = new Portal("p2");
        p2.setRouter(r1);
        
        Agent a2 = new Agent("Met Office Station #3");
        a2.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: " + m.getMessage());
            }
        });        
        p2.addAgent(a2);
        

        a2.sendMessage(new Message("Hartlepool Power Station", "Met Office Station #3", "It's getting mighty chilly!"));
        a1.sendMessage(new Message("Met Office Station #3", "Hartlepool Power Station", "I better start burning some nuclears!"));              
    }
}
