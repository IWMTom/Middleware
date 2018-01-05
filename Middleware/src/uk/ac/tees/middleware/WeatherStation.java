/**
 * WeatherStation.java
 * A concrete implementation of a User Agent
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

public class WeatherStation extends UserAgent
{
    /**
     * Class constructor
     * 
     * @param name name of the User Agent 
     */    
    public WeatherStation(String name)
    {
        super(name);
    }

    /**
     * Handles an incoming message
     * 
     * @param m reference to Message
     */
    @Override
    void handleMessage(Message m)
    {
        System.out.println(m.getRecipient() + " has received a message from " + m.getSender() + " - it says: \"" + m.getMessage() + "\"");
    }

}