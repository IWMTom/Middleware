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
        Portal p1 = new Portal("p1");
        
        PowerStation a1 = new PowerStation("Hartlepool Power Station");
        p1.addAgent(a1);
        WeatherStation a2 = new WeatherStation("Met Office Station #3");
        p1.addAgent(a2);
        
        a2.sendMessage(new Message("Hartlepool Power Station", "Met Office Station #3", "It's getting mighty chilly!"));
        a1.sendMessage(new Message("Met Office Station #3", "Hartlepool Power Station", "I better start burning some nuclears!"));
    }
}
