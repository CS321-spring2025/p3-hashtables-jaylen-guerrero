/**
 * Author: Jaylen Guerrero
 * Date: 03/17/2025
 * Description: This DoubleHashing class will implement a hash table that uses double hashing to insert and search for elements. It extends hashtable which creates the table itself.
 */

import java.io.FileWriter;
import java.io.IOException;

public class DoubleHashing  extends Hashtable{

    /**
     * DoubleHashing - default constructor for a double hash hashtable which will create a new table with size of 10.
     */
    public DoubleHashing() {
        super();
    }

    /**
     * DoubleHashing - constructor for a double hash hashtable which will create a new table with size of size.
     * @param size Size of table to be created
     */
    public DoubleHashing(int size) {
        super(size);
    }

    /**
     * search - will search for an object in the hashtable and return the object found
     * @param obj The object being searched for
     * @return Returns a HashObject, will return obj if the obj is in the table, null if not found
     */
    public HashObject search(HashObject obj) {
        int current = 0;
        for (int i = 0; i < getM(); i++) { // Iterates through the hashtable if needed
            current = (h1(obj.getKey()) + (i * h2(obj.getKey()))) % getM();
            if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && (table[current]).equals(obj)) { //Object is found
                return table[current];
            } else if (table[current] != null && table[current].getState() == HashObject.State.EMPTY) { // Object is not found
                return null;
            }
        }
        return null;
    }

    /**
     * insert - will insert an object into the hashtable. If the object is already in the hashtable, the frequencyCount of the object will be increased 
     * and will not be added to another slot in the table/
     * @param obj The object to be inserted into the hashtable
     */
    public void insert(HashObject obj) {
        int current;
        if (getLoadFactor() >= getTargetLoadFactor()) { // Check if table is too full according to load factor
            rehashTable();
        }
        for (int i = 0; i < getM(); i++) { // Iterates through hashtable if needed, adding obj when an eligible spot is found
            long temp = ((h1(obj.getKey())) + (i * h2(obj.getKey())));
            current = (int)temp % getM();
            obj.incrementProbe();
            if (table[current] != null && (table[current].getState() == HashObject.State.EMPTY || table[current].getState() == HashObject.State.DELETED)) {
                table[current] = obj;
                table[current].setState(HashObject.State.OCCUPIED);
                incrementSize();
                table[current].incrementFrequency();
                return;
            } else if (table[current] != null && table[current].getState() == HashObject.State.OCCUPIED && table[current].equals(obj)) { // Check if obj is already in hashtable
                table[current].incrementFrequency();
                incrementDupe();
                return;
            }
        }
    }

    /**
     * delete - will delete an obj from the hashtable
     * @param obj Object to be deleted from table
     */
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

    /**
     * rehashTable - private helper method that will rehash the elements in the table becasue the load factor has been reached
     * Will create a new table with twice the size of the current table and assign new values and reassign the old table with the new copy
     */
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
                    long temp = (h1(table[i].getKey()) + (j * h2(table[i].getKey())));
                    current = (int)temp % getM();
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

        table = newTable; // Reassignment of table
    }

    /**
     * debug0 - will print out the debug stats of the hashtable
     */
    public void debug0() {
        calcProbes();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableExperiment: size of hash table is " + getM());
        System.out.println("\tInserted " + (getSize() + getDupes()) + " elements, of which " +  getDupes() + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format((1.0 * getProbeCount())/(getSize() + getDupes())));

    }

    /**
     * debug1 - will print out the debug stats of the hashtable and save it to a file
     */
    public void debug1() {
        debug0();
        System.out.println("HashtableExperiment: Saved dump of hash table");
        try (FileWriter write = new FileWriter("double-dump.txt")) {
            for (int i = 0; i < getM(); i++) {
                if (table[i] != null && table[i].getState() == HashObject.State.OCCUPIED) {
                    write.write("table[" + i + "]: " + table[i].getKey() + " " + table[i].frequencyCount + " " + table[i].probeCount + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * debug2 - will print out a detailed version of the entire hashtable
     */
    public void debug2(HashObject obj, int i) {
        System.out.print("Double Hash:\t");
        System.out.print("Item no: " + i + "\n");
        if (search(obj) != null) { // object will be a duplicate
            System.out.println("\"" + obj + "\" Object is a duplicate - will not be inserted");
        } else {
            System.out.println("\"" + obj + "\" Object will be inserted into table - first instance of object in table");
        }
    }

    
    
}
