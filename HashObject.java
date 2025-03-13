public class HashObject {
    
    public int frequencyCount = 0;
    public int probeCount = 0;
    private Object key;
    private State state;

    public enum State{
        EMPTY,
        OCCUPIED,
        DELETED
    }

    public HashObject() {
        this.state = State.EMPTY;
        this.key = null;
    }

    public HashObject(State state) {
        this.state = state;
        this.key = null;
    }

    public HashObject(Object key) {
        this.state = State.OCCUPIED;
        this.key = key;
        
    }

    public boolean equals(HashObject obj) {
        if (obj.getKey().equals(this.key)) {
            return true;
        } else  {
            return false;
        }
    }

    public void incrementFrequency() {
        this.frequencyCount++;
    }

    public void incrementProbe() {
        this.probeCount++;
    }

    public String toString() {
        return "";
    }

    public Object getKey() {
        return this.key;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    


    
}
