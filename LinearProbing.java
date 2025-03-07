import java.util.Arrays;

public class LinearProbing extends Hashtable{

    public LinearProbing() {
        for (int i = 0; i < table.length; i++) {
            table[i] = table.EMPTY;
        }
    }

    public HashObject search(HashObject obj) {
        for (int i = 0; i < m; i++) {
            if (table[obj.hashCode()] == obj) {
                return obj;
            } else {
                if (i != 0) {
                    if (table[obj.hashCode() + i] == obj) {
                        return obj;
                    }
                }
            }
        }
        return null;

    }

    public void insert(HashObject obj) {
        if (table.length == size) {
            table = Arrays.copyOf(table, size*2);
            m = table.length;
        }
        for (int i = 0; i < m; i++) {
            if (table[obj.hashCode()] == EMPTY) {
                table[obj.hashCode()] = obj;
            }
        }

    }   
}
