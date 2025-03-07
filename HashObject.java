public class HashObject {
    public enum state {
        EMPTY,
        DELETED,
        OCCUPIED
    }
    public int frequencyCount = 0;
    public int probeCount = 0;
    private Object key;
    static state state;

    public HashObject() {
        state = state.EMPTY;
        key = null;
    }

    public HashObject(Object key) {
        this.key = key;
        this.state = state.OCCUPIED;
    }

    public boolean equals(HashObject obj) {
        if (obj.getKey().equals(this.key)) {
            return true;
        } else  {
            return false;
        }
    }

    public String toString() {
        return "";
    }

    public Object getKey() {
        return this.key;
    }

    
}
