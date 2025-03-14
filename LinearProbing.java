public class LinearProbing extends Hashtable{

    final double LOAD_FACTOR = 1;

    public LinearProbing() {
        super();
    }

    public LinearProbing(int size) {
        super(size);
    }

    public HashObject search(HashObject obj) {
        // int pos = h1(obj.getKey());
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (h1(obj.getKey()) + i) % getM();
            if (table[current] != null && (table[current].equals(obj)) && table[current].getState() != HashObject.State.DELETED) {
                return table[current];
            } else if (table[current] != null && (table[current].getState() == HashObject.State.EMPTY)) {
                return null;
            }
        }
        return null;

    }

    public void insert(HashObject obj) {
        if (getLoadFactor() > LOAD_FACTOR) { // Hash table is full
            rehashTable();
        }
        if (search(obj) != null) { // obj is already in table
            obj.incrementFrequency();
            return;
        }
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (h1(obj.getKey()) + i) % getM();
            obj.incrementProbe();
            if (table[current] != null) {
                if ((table[current].getState() == HashObject.State.EMPTY) || table[current].getState() == HashObject.State.DELETED) {
                    table[current] = obj;
                    table[current].setState(HashObject.State.OCCUPIED);
                    incrementSize();
                    return;
                }
            }
        }
    } 

    private void rehashTable() {
        HashObject[] newTable = new HashObject[table.length * 2];
        int current;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getState() == HashObject.State.OCCUPIED) {
                for (int j = 0; j < newTable.length; j++) {
                    current = (h1(table[i].getKey()) + j) % (newTable.length);
                    if (newTable[current] == null ) {
                        newTable[current] = table[i];
                        newTable[current].setState(HashObject.State.OCCUPIED);
                        break;
                    }
                }
                
            }
        }
        table = newTable;
        setM(newTable.length);
        
    }
    
    public void delete(HashObject obj) {
        int pos = h1(obj.getKey());
        int current = 0;
        if (search(obj) != null) { // Object is in hash table
            for (int i = 0; i < getM(); i++) {
                current = (pos + i) % getM();
                if (table[current] != null && table[current].equals(obj)) {
                    // table[current] = null;
                    table[current].setState(HashObject.State.DELETED);
                    decrementSize();
                    return;
                }

            }
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < getM(); i++) {
            if (table[i].getState() != HashObject.State.DELETED || table[i].getState() != HashObject.State.EMPTY) {
                s = s.concat("Index: " + i + "\tValue: " + table[i].getKey() + "\n");
            } else {
                s = s.concat("Index: " + i + "\tValue: null\n");
            }
        }
        return s;
    }

}
