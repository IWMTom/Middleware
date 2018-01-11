/**
 * UnroutableException.java
 * A custom exception, thrown when a message cannot be routed
 * as the Agent is unavailable.
 */

package uk.ac.tees.middleware;

public class UnroutableException extends Exception
{
    public UnroutableException()
    {
        super();
    }
}