import java.util.Arrays;

public class LinearProbing extends Hashtable{

    public LinearProbing() {
        super();
    }

    public LinearProbing(int size) {
        super(size);
    }

    public HashObject search(HashObject obj) {
        int pos = h1(obj.getKey());
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (pos + i) % getM();
            if (table[current] != null && (table[current].equals(obj))) {
                return table[current];
            } else if (table[current] != null && (table[current].getState() == HashObject.State.EMPTY)) {
                return null;
            }
        }
        return null;

    }

    public void insert(HashObject obj) {
        if (getSize() == table.length) { // Hash table is full
            table = Arrays.copyOf(table, getSize() * 2); // Double Size of hash table
        }
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            int pos = h1(obj.getKey());
            current = (pos + i) % getM();
            if ((table[current].getState() == HashObject.State.EMPTY) || table[current].getState() == HashObject.State.DELETED) {
                table[current] = obj;
                incrementSize();
                return;
            }
        }
    } 
    
    public void delete(HashObject obj) {
        int pos = obj.hashCode();
        if (search(obj).equals(obj)) { // Object is in hash table
            for (int i = 0; i < getM(); i++) {
                if (table[pos + i].equals(obj)) {
                    table[pos + i] = null;
                    table[pos + i] = new HashObject(HashObject.State.DELETED);
                    decrementSize();
                }

            }
        }
    }

}
