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

    public Hashtable(int size) {
        this.table = new HashObject[size];
        this.m = table.length;
        for (int i = 0; i < m; i++) {
            table[i] = new HashObject();
        }
    }

    public HashObject search(HashObject obj) {
        if (table[obj.hashCode()].equals(obj)) {
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
        table[obj.hashCode()] = obj;
        incrementSize();
    }

    public void delete(HashObject obj) {
        int pos = obj.hashCode();
        if (search(obj) != null) {
            table[pos] = null;
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

    public void incrementSize() {
        this.size++;
    }

    public void decrementSize() {
        this.size--;
    }

    

}
