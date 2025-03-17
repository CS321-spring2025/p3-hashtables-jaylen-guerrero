/**
 * Author: Jaylen Guerrero 
 * Date: 03/17/2025
 * Description: A HashObject class that holds the implementation of an object to be inserted into a hashtable.
 * Has a frequencyCount that holds how many instances of the obj in the hashtable. Has probeCount which shows how many times it took to be inserted into the table.
 */
public class HashObject {
    
    public int frequencyCount = 0;
    public int probeCount = 0;
    private Object key;
    private State state;

    /**
     * State - enum declaring the state of the object
     * EMPTY - EMPTY means the slot is empty with null data value and can be replaced
     * OCCUPIED - OCCUPIED shows that the object holds some sort of data
     * DELETED - DELETED shows that there was a object with data but has been deleted
     */
    public enum State{
        EMPTY,
        OCCUPIED,
        DELETED
    }

    /**
     * HashObject - default contructor that sets to empty state and has null key value
     */
    public HashObject() {
        this.state = State.EMPTY;
        this.key = null;
    }

    /**
     * HashObject - constructor for HashObject that sets state to state and sets key to null
     * @param state State to set new object
     */
    public HashObject(State state) {
        this.state = state;
        this.key = null;
    }

    /**
     * HashObject - constructor for a HashObject with a key
     * @param key Key value for new HashObject
     */
    public HashObject(Object key) {
        this.state = State.OCCUPIED;
        this.key = key;
        
    }

    /**
     * equals - checks if two objects are the equal
     * @param obj Object to be compared to
     * @return True if the objects are equal, false otherwise
     */
    public boolean equals(HashObject obj) {
        if (obj.getKey().equals(this.key)) {
            return true;
        } else  {
            return false;
        }
    }

    /**
     * incrementFrequency - helper method to increase frequency by one
     */
    public void incrementFrequency() {
        this.frequencyCount++;
    }

    /**
     * incrementProbe - helper method to increase probeCount by one
     */
    public void incrementProbe() {
        this.probeCount++;
    }

    /**
     * getKey - getter for the key value
     * @return Key value of HashObject
     */
    public Object getKey() {
        return this.key;
    }

    /**
     * getState - getter for the state of the HashObject
     * @return The state of the HashObject
     */
    public State getState() {
        return this.state;
    }

    /**
     * setState - sets the state of the HashObject
     * @param state The new state to set the HashObject to
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * toString - will print out the key value of the HashObject
     */
    public String toString() {
        return this.key.toString();
    }


    
}
