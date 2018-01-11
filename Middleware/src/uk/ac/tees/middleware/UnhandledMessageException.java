/**
 * UnhandledMessageException.java
 * A custom Exception, thrown when a message hasn't been handled
 * due to a missing MessageListener
 */

package uk.ac.tees.middleware;

public class UnhandledMessageException extends Exception
{
    public UnhandledMessageException()
    {
        super();
    }
}