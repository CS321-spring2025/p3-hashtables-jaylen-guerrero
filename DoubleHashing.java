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
        int current = 0;
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
                return;
            } else if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && table[current].equals(obj)) {
                table[current].incrementFrequency();
                return;
            }
        }
    }

    @Override
    public void delete(HashObject obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private void rehashTable() {

    }
    
}
