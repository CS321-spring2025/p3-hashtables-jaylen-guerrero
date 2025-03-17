/**
 * Author: Jaylen Guerrero
 * Date: 03/17/2025
 * Description: This LinearProbing class will implement a hash table that uses linear probing to insert and search for elements. It extends hashtable which creates the table itself.
 */

import java.io.FileWriter;
import java.io.IOException;

public class LinearProbing extends Hashtable{

    /**
     * LinearProbing - default constructor that will create a hashtable of size 10
     */
    public LinearProbing() {
        super();
    }

    /**
     * LinearProbing - default constructor that will create a hashtable with a size of size
     * @param size Size of hashtable to be created
     */
    public LinearProbing(int size) {
        super(size);
    }

    /**
     * search - will search for an element in the table
     * @param obj Object to be searched for
     * @return HashObject, will return the obj if found in the table, null otherwise
     */
    public HashObject search(HashObject obj) {
        int current = 0;
        for (int i = 0; i < getM(); i++) { // Iterate through table if needed
            current = (h1(obj.getKey()) + i) % getM();
            if (table[current] != null && (table[current].equals(obj)) && table[current].getState() != HashObject.State.DELETED) {
                return table[current];
            } else if (table[current] != null && (table[current].getState() == HashObject.State.EMPTY)) {
                return null;
            }
        }
        return null;

    }

    /**
     * insert - will insert an obj into a hashtable. If object is already in the table, the frequencyCount will be increased and object will not be put into table
     * @param obj Object to be inserted into table
     */
    public void insert(HashObject obj) {
        if (getLoadFactor() >= getTargetLoadFactor()) { // Hash table is full according to load factor
            rehashTable();
        }
        int current = 0;
        for (int i = 0; i < getM(); i++) {
            current = (h1(obj.getKey()) + i) % getM();
            obj.incrementProbe();
            if (table[current] != null && table[current].equals(obj)) { // Check if obj is in table
                table[current].incrementFrequency();
                incrementDupe();
                return;
            }
            if (table[current] != null) {
                if ((table[current].getState() == HashObject.State.EMPTY) || table[current].getState() == HashObject.State.DELETED) { // Found an empty slot ot insert obj
                    table[current] = obj;
                    table[current].setState(HashObject.State.OCCUPIED);
                    obj.incrementFrequency();
                    incrementSize();
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
    
    /**
     * delete - will delete an object from the table
     * @param obj Object to be deleted from the table
     */
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

    /**
     * debug0 - will print out the debug stats of the hashtable
     */
    public void debug0() {
        calcProbes();
        System.out.println("\tUsing Linear Probing");
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
        try (FileWriter write = new FileWriter("linear-dump.txt")) {
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
        System.out.print("Linear Probe:\t");
        System.out.print("Item no: " + i + "\n");
        if (search(obj) != null) { // object will be a duplicate
            System.out.println("\"" + obj + "\" Object is a duplicate - will not be inserted");
        } else {
            System.out.println("\"" + obj + "\" Object will be inserted into table - first instance of object in table");
        }
    }

}
