public class LinearProbing extends Hashtable{

    public LinearProbing() {
        super();
    }

    public LinearProbing(int size) {
        super(size);
    }

    public HashObject search(HashObject obj) {
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
        if (getLoadFactor() >= getTargetLoadFactor()) { // Hash table is full
            rehashTable();
        }
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (h1(obj.getKey()) + i) % getM();
            obj.incrementProbe();
            if (table[current] != null && table[current].equals(obj)) {
                table[current].incrementFrequency();
                incrementDupe();
                return;
            }
            if (table[current] != null) {
                if ((table[current].getState() == HashObject.State.EMPTY) || table[current].getState() == HashObject.State.DELETED) {
                    table[current] = obj;
                    table[current].setState(HashObject.State.OCCUPIED);
                    obj.incrementFrequency();
                    incrementSize();
                    return;
                }
            }
        }
    } 

    private void rehashTable() {
        HashObject[] newTable = new HashObject[table.length * 2];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new HashObject(null);
            newTable[i].setState(HashObject.State.EMPTY);
        }
        int current;
        setM(newTable.length);
        for (int i = 0; i < table.length; i++) {
                if (table[i] != null && table[i].getState() == HashObject.State.OCCUPIED) {
                    for (int j = 0; j < newTable.length; j++) {
                        current = (h1(table[i].getKey()) + j) % (getM());
                        if (!(newTable[current].equals(table[i]))) { // if obj is already in new table
                            if (newTable[current].getState() == HashObject.State.EMPTY || newTable[current].getState() == HashObject.State.DELETED) {
                                newTable[current] = table[i];
                                newTable[current].setState(HashObject.State.OCCUPIED);
                                // incrementSize();
                                break;
                            }
                        } else {
                            newTable[current].incrementFrequency();
                            break;
                        }
                    }
                    
                }
        }
        table = newTable;
        
        
    }

    
    
    public void delete(HashObject obj) {
        int pos = h1(obj.getKey());
        int current = 0;
        if (search(obj) != null) { // Object is in hash table
            for (int i = 0; i < getM(); i++) {
                current = (pos + i) % getM();
                if (table[current] != null && table[current].equals(obj)) {
                    table[current] = new HashObject(null);
                    table[current].setState(HashObject.State.DELETED);
                    decrementSize();
                    return;
                }

            }
        }
    }

    public void debug0() {
        calcProbes();
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableExperiment: size of hash table is " + getM());
        System.out.println("\tInserted " + getSize() + " elements, of which " +  getDupes() + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format((1.0 * getProbeCount())/getSize()));

    }

}
