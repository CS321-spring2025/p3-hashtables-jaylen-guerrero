import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    
    TwinPrimeGenerator primeGen = new TwinPrimeGenerator();
    static int m = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
    static LinearProbing linear = new LinearProbing(m);
    static DoubleHashing doubleHash = new DoubleHashing(m);

    public static void printUsage() {
        System.err.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.err.println("<dataSource>: 1 ==> random numbers");
        System.err.println("\t2 ==> date value as a long");
        System.err.println("\t3 ==> word list");
        System.err.println("<loadFactor>: The ratio of objects to table size,\n\tdenoted by alpha n/m");
        System.err.println("<debugLevel>: 0 ==> print summary of experiment");
        System.err.println("\t1 ==> save the two hash tables to a file at the end");
        System.err.println("\t2 ==> print debugging output for each insert");
    }

    public static void dataSource(int source, int n, int debug, long seed) {
        Random gen = new Random(seed);

        HashObject obj, dobj;
        int cur;
        
        if (source == 1) { //  data source 1
            for (int i = 0; i < n; i++) {
                cur = gen.nextInt();
                dobj = new HashObject(cur);
                obj = new HashObject(cur);
                if (debug == 2) {
                    linear.debug2(obj, i);
                    doubleHash.debug2(dobj, i);
                }
                linear.insert(obj);
                doubleHash.insert(dobj);
            }

        } else if (source == 2) { // data source 2
            long current = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                current += 1000;
                Date date = new Date(current);
                obj = new HashObject(date);
                dobj = new HashObject(date);
                if (debug == 2) {
                    linear.debug2(obj, i);
                    doubleHash.debug2(dobj, i);
                }
                linear.insert(obj);
                doubleHash.insert(dobj);
            }

        } else { // data source 3
            String filePath = "word-list.txt";
            String curString;
            try (Scanner scan = new Scanner(new File(filePath))) {
                for (int i = 0; i < n; i++) {
                    curString = scan.next();
                    obj = new HashObject(curString);
                    dobj = new HashObject(curString);
                    if (debug == 2) {
                        linear.debug2(obj, i);
                        doubleHash.debug2(dobj, i);
                    }
                    linear.insert(obj);
                    doubleHash.insert(dobj);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void dataSource(int source, int size) {
        dataSource(source, size, 0, System.currentTimeMillis());
    }

    public static void dataSource(int source, int n, int debug) {
        dataSource(source, n, debug, System.currentTimeMillis());
    }
    

    public static void main(String[] args) {
        // Command line checks
        if (args.length < 2 || args.length > 3) {
            printUsage();
            System.exit(1);
        }
        int dataSource = Integer.parseInt(args[0]);
        int debugLevel = -1;
        double loadFactor = Double.parseDouble(args[1]);
        if (args.length == 3) {
            debugLevel = Integer.parseInt(args[2]);
            if (debugLevel < 0 || debugLevel > 2) {
                printUsage();
                System.exit(1);
            }
        }
        if (dataSource < 1 || dataSource > 3) {
            printUsage();
            System.exit(1);
        }
        if (loadFactor < 0 || loadFactor > 1) {
            printUsage();
            System.exit(1);
        }

        linear.setTargetLoadFactor(loadFactor);
        doubleHash.setTargetLoadFactor(loadFactor);
        int n = (int)Math.ceil(loadFactor * m);


        dataSource(dataSource, n, debugLevel, 0);

        if (debugLevel != -1) {
            if (debugLevel == 0) { // debug 0
                System.out.println("HashtableExperiment: Found a twin prime for table capacity: " + m);
                linear.debug0();
                System.out.println();
                doubleHash.debug0();
            } else if (debugLevel == 1) {
                System.out.println("HashtableExperiment: Found a twin prime for table capacity: " + m);
                linear.debug1();
                System.out.println();
                doubleHash.debug1();
            } else if (debugLevel == 2) {

            }
        } else {
            System.out.println("HashtableExperiment: Found a twin prime for table capacity: " + m);
                linear.debug0();
                System.out.println();
                doubleHash.debug0();
        }

    }
}
