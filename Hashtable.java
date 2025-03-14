import java.util.Arrays;

public abstract class Hashtable {
    

    protected HashObject[] table;
    private int size = 0;
    private int m;

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

    public HashObject search(HashObject obj) {
        if (table[h1(obj.getKey())].equals(obj)) {
            return obj;
        } else {
            return null;
        }
    }

    public void insert(HashObject obj) {
        if (table.length == size) {
            table = Arrays.copyOf(table, size*2);
            m = table.length;
        }
        table[h1(obj.getKey())] = obj;
        incrementSize();
    }

    public void delete(HashObject obj) {
        int pos = h1(obj.getKey());
        if (search(obj) != null) {
            table[pos] = new HashObject(HashObject.State.DELETED);
            decrementSize();
        }

    }


    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    public double getLoadFactor() {
        return (size/((double)m));
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

    

}
