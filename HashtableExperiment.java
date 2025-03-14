import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    final static int MAX_ELEMENTS = 100;
    TwinPrimeGenerator primeGen = new TwinPrimeGenerator();
    static int m = 160;//TwinPrimeGenerator.generateTwinPrime(95500, 96000);
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

    public static void dataSource(int source, long seed) {
        Random gen = new Random(seed);
        int amount = MAX_ELEMENTS;//gen.nextInt(MAX_ELEMENTS);
        HashObject obj, dobj;
        int cur;
        
        if (source == 1) { //  data source 1
            for (int i = 0; i < amount; i++) {
                cur = gen.nextInt();
                dobj = new HashObject(cur);
                obj = new HashObject(cur);
                linear.insert(obj);
                doubleHash.insert(dobj);
            }

        } else if (source == 2) { // data source 2
            long current = System.currentTimeMillis();
            for (int i = 0; i < amount; i++) {
                current += 1000;
                Date date = new Date(current);
                obj = new HashObject(date);
                dobj = new HashObject(date);
                linear.insert(obj);
                doubleHash.insert(dobj);
            }

        } else { // data source 3
            String filePath = "word-list.txt";
            String curString;
            try (Scanner scan = new Scanner(new File(filePath))) {
                for (int i = 0; i < amount; i++) {
                    curString = scan.next();
                    obj = new HashObject(curString);
                    dobj = new HashObject(curString);
                    linear.insert(obj);
                    doubleHash.insert(dobj);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }

    }

    public static void dataSource(int source) {
        dataSource(source, System.currentTimeMillis());
    }

    public static void main(String[] args) {
        // Command line checks
        if (args.length < 2 || args.length > 3) {
            printUsage();
            System.exit(1);
        }
        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        if (args.length == 3) {
            int debugLevel = Integer.parseInt(args[2]);
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

        dataSource(dataSource, 0);

        linear.debug0();
        System.out.println();
        doubleHash.debug0();

    }
}
