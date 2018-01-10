/**
 * UnhandledMessageException.java
 * A custom Exception, thrown when a message hasn't been handled
 * due to a missing MessageListener
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

public class UnhandledMessageException extends Exception
{
    public UnhandledMessageException()
    {
        super();
    }
}