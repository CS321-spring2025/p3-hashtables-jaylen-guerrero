public class DoubleHashing  extends Hashtable{

    public DoubleHashing() {
        super();
    }

    public DoubleHashing(int size) {
        super(size);
    }

    public HashObject search(HashObject obj) {
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (h1(obj.getKey()) + (i * h2(obj.getKey()))) % getM();
            if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && (table[current]).equals(obj)) {
                return table[current];
            } else if (table[current] != null && table[current].getState() == HashObject.State.EMPTY) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void insert(HashObject obj) {
        int current;
        if (getLoadFactor() >= getTargetLoadFactor()) {
            rehashTable();
        }
        for (int i = 0; i < getM(); i++) {
            current = ((h1(obj.getKey())) + (i * h2(obj.getKey()))) % getM();
            obj.incrementProbe();
            if (table[current] != null && (table[current].getState() == HashObject.State.EMPTY || table[current].getState() == HashObject.State.DELETED)) {
                table[current] = obj;
                table[current].setState(HashObject.State.OCCUPIED);
                incrementSize();
                table[current].incrementFrequency();
                return;
            } else if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && table[current].equals(obj)) {
                table[current].incrementFrequency();
                incrementSize();
                return;
            }
        }
    }

    @Override
    public void delete(HashObject obj) {
        int current = 0;
        if (search(obj) != null && search(obj).equals(obj)) { // obj is in table
            for (int i = 0; i < getM(); i++) {
                current = (h1(obj.getKey()) + (i * h2(obj.getKey()))) % getM();
                if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && table[current].equals(obj)) {
                    table[current] = new HashObject(null);
                    table[current].setState(HashObject.State.DELETED);
                    decrementSize();
                    return;
                }
            }
        }
    }

    private void rehashTable() {
        int current = 0;
        HashObject[] newTable = new HashObject[table.length * 2];
        for (int i = 0; i < newTable.length; i++) { // initialize new table
            newTable[i] = new HashObject(null);
            newTable[i].setState(HashObject.State.EMPTY);
        }
        setM(newTable.length);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getState() == HashObject.State.OCCUPIED) {
                for (int j = 0; j < newTable.length; j++) {
                    current = (h1(table[i].getKey()) + (j * h2(table[i].getKey()))) % (getM());
                    if (newTable[current].getState() == HashObject.State.EMPTY || newTable[current].getState() == HashObject.State.DELETED) {
                        newTable[current] = table[i];
                        newTable[current].setState(HashObject.State.OCCUPIED);
                        break;
                    } else if (newTable[current].getState() == HashObject.State.OCCUPIED && newTable[current].equals(table[i])) {
                        newTable[current].incrementFrequency();
                        break;
                    }

                }
            }
        }

        table = newTable;
    }

    
    
}
