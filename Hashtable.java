/**
 * Author: Jaylen Guerrero
 * Date: 03/17/2025
 * Description: This Hashtable class implements an abstract hashtable used to track total counts of duplicates and probes among other values. Has abstract methods to be used with different
 * types of probing methods for a hashtable
 */

import java.text.DecimalFormat;

public abstract class Hashtable {
    

    protected HashObject[] table;
    private int size = 0;
    private int m;
    private double loadFactor;
    private double loadFactorTarget = 1;
    private int totDupes = 0;
    private int totProbeCount = 0;
    DecimalFormat format = new DecimalFormat(".##");

    /**
     * Hashtable - default constructor that will create a new array of size 10 and fill it with empty HashObject objects
     */
    public Hashtable() {
        this.table = new HashObject[10];
        this.m = table.length;
        for (int i = 0; i < m; i++) {
            table[i] = new HashObject();
        }
    }

    /**
     * Hashtable - constructor with the size of table
     * @param m Size of the table to be created
     */
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

    /**
     * positiveMod - helper method to ensure there is a positive value after a modulo is computed
     * @param dividend The dividend of the modulo to be computed
     * @param divisor The divisor of the modulo to be computed
     * @return Positive integer that resulted from the modulo computation
     */
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    /**
     * getLoadFactor - getter for the loadFactor
     * @return loadFactor of the table
     */
    public double getLoadFactor() {
        this.loadFactor = (1.0 * getSize())/(getM());
        return loadFactor;
    }

    /**
     * getSize - getter for the amount of elements in the table
     * @return Amount of elements in table
     */
    public int getSize() {
        return size;
    }

    /**
     * getM - getter for the m value of table, or length of table
     * @return Length of table
     */
    public int getM() {
        return m;
    }

    /**
     * setM - setter for the m value or length of table
     * @param newM New length for the table
     */
    public void setM(int newM) {
        this.m = newM;
    }

    /**
     * incrementSize - helper for incrementing size by 1
     */
    public void incrementSize() {
        this.size++;
    }

    /**
     * resetSize - resets size to 0
     */
    public void resetSize() {
        this.size = 0;
    }

    /**
     * decrementSize - helper method do decrease size by 1
     */
    public void decrementSize() {
        this.size--;
    }

    /**
     * h1 - h1 equation for hashing
     * @param key Key of object to be hashed
     * @return Hash value of the key
     */
    public int h1(Object key) {
        return positiveMod(key.hashCode(), m);
    }

    /**
     * incrementDupe - helper method to increase the duplicate count of the table
     */
    public void incrementDupe() {
        totDupes++;
    }

    /**
     * getDupes - getter for the amount of duplicates in the table
     * @return Amount of duplicates
     */
    public int getDupes() {
        return totDupes;
    }

    /**
     * h2 - h2 function for hashing
     * @param key Key of object to be hashed
     * @return Hash value of the key
     */
    public int h2(Object key) {
        return (1 + (positiveMod(key.hashCode(), (m - 2))));
    }

    /**
     * setTargetLoadFactor - setter for the target load factor
     * @param lf Target load factor
     */
    public void setTargetLoadFactor(double lf) {
        this.loadFactorTarget = lf;
    }

    /**
     * getTargetLoadFactor - getter for the targetLoadFactor
     * @return Target load factor
     */
    public double getTargetLoadFactor() {
        return loadFactorTarget;
    }

    /**
     * calcProbes - will add up the total amount of probe counts for each object in table
     */
    public void calcProbes() {
        totProbeCount = 0;
        for (int i = 0; i < getM(); i++) {
            if (table[i] != null && table[i].getState() == HashObject.State.OCCUPIED) {
                totProbeCount += table[i].probeCount;
            }
        }
    }

    /**
     * getProbeCount - getter for the total probe count
     * @return Total amount of probes
     */
    public int getProbeCount() {
        return totProbeCount;
    }

    /**
     * toString - prints out the data from the table. Shows the size of table, amount of elements inserted, probe count, and duplicates
     */
    public String toString() {
        
        String s = "";
        for (int i = 0; i < getM(); i++) {
            if (table[i] != null) {
                if (table[i].getState() != HashObject.State.DELETED || table[i].getState() != HashObject.State.EMPTY) {
                    s = s.concat("Index: " + i + "\tValue: " + table[i].getKey() + "\tProbe count: " + table[i].probeCount + "\tFrequency Count: " + table[i].frequencyCount + "\n");
                } else {
                    s = s.concat("Index: " + i + "\tValue: null\n");
                }
                totProbeCount += table[i].probeCount;
            } else {
                s = s.concat("Index: " + i + "\tValue: null\n");
            }
            
            }
            s = s.concat("\nTotal probes: " + totProbeCount + "\tTotal collisions: " + (totProbeCount - getSize()));
        return s;
    }

    

}


