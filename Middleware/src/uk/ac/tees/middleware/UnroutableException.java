/**
 * UnroutableException.java
 * A custom exception, thrown when a message cannot be routed
 * as the Agent is unavailable.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

public class UnroutableException extends Exception
{
    public UnroutableException()
    {
        super();
    }
}