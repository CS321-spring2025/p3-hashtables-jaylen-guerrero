import java.util.Arrays;

public abstract class Hashtable {
    

    protected HashObject[] table = new HashObject[10];
    public int size = 0;
    public int m = table.length;

    public Hashtable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = state.EMPTY;
        }
    }

    public HashObject search(HashObject obj) {
        
        if (table[obj.hashCode()] == obj)  {
            return obj;
        }
        
        return null;
    }

    public void insert(HashObject obj) {
        if (table.length == size) {
            table = Arrays.copyOf(table, size*2);
            m = table.length;
        }
        table[obj.hashCode()] = obj;
        size++;
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

    

}
