public abstract class Hashtable {
    

    protected HashObject[] table;
    private int size = 0;
    private int m;
    private double loadFactor;
    private double loadFactorTarget = 1;

    public Hashtable() {
        this.table = new HashObject[10];
        this.m = table.length;
        for (int i = 0; i < m; i++) {
            table[i] = new HashObject();
        }
    }

    public Hashtable(int m) {
        this.table = new HashObject[m];
        this.m = table.length;
        for (int i = 0; i < m; i++) {
            table[i] = new HashObject();
        }
    }

    public abstract HashObject search(HashObject obj);

    public abstract void insert(HashObject obj);

    public abstract void delete(HashObject obj);


    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    public double getLoadFactor() {
        this.loadFactor = (1.0 * getSize())/(getM());
        return loadFactor;
    }

    public int getSize() {
        return size;
    }

    public int getM() {
        return m;
    }

    public void setM(int newM) {
        this.m = newM;
    }

    public void incrementSize() {
        this.size++;
    }

    public void decrementSize() {
        this.size--;
    }

    public int h1(Object key) {
        return positiveMod(key.hashCode(), m);
    }

    public int h2(Object key) {
        return (1 + positiveMod(key.hashCode(), m - 2));
    }

    public void setTargetLoadFactor(double lf) {
        this.loadFactorTarget = lf;
    }

    public double getTargetLoadFactor() {
        return loadFactorTarget;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < getM(); i++) {
            if (table[i] != null) {
                if (table[i].getState() != HashObject.State.DELETED || table[i].getState() != HashObject.State.EMPTY) {
                    s = s.concat("Index: " + i + "\tValue: " + table[i].getKey() + "\tProbe count: " + table[i].probeCount + "\tFrequency Count: " + table[i].frequencyCount + "\n");
                } else {
                    s = s.concat("Index: " + i + "\tValue: null\n");
                }
            } else {
                s = s.concat("Index: " + i + "\tValue: null\n");
            }
            
            }
        return s;
    }

}


