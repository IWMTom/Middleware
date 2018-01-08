/**
 * Middleware.java
 * A driver test harness to test operation of the Middleware.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

public class Middleware
{
    public static Demo demo = new Demo(); // I know, I know
    
    /**
     * Creates a Portal and two User Agents - the User Agents are added
     * to the Portal. Messages are then sent between the two User Agents.
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        
        demo.setVisible(true);
        
        Router r1 = new Router("Central Monitoring Centre");
        
        Portal p1 = new Portal("Power Stations");
        p1.setRouter(r1);
        
        UserAgent a1 = new UserAgent("Hartlepool Power Station");
        a1.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                demo.addRow(m, "Agent: " + a1.getName());
                System.out.println("Hartlepool Power Station has received a message!");
            }
        });
        p1.addAgent(a1);
        
        
        Portal p2 = new Portal("Weather Stations");
        p2.setRouter(r1);
        
        UserAgent a2 = new UserAgent("Met Office Station #3");
        a2.addMessageListener(new MessageListener()
        {
            @Override
            public void messageReceived(Message m)
            {
                demo.addRow(m, "Agent: " + a2.getName());
                System.out.println("Met Office Station #3 has received a message!");
            }
        });        
        p2.addAgent(a2);
        
        a2.sendMessage(new Message("Hartlepool Power Station", "Met Office Station #3", "It's getting mighty chilly!"));
        a1.sendMessage(new Message("Met Office Station #3", "Hartlepool Power Station", "I better start burning some nuclears!"));
    }
}
