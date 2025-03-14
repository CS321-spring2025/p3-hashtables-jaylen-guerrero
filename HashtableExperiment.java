public class HashtableExperiment {
    public static void main(String[] args) {
        
        if (args.length < 2) {
            System.err.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
            System.err.println("<dataSource>: 1 ==> random numbers");
            System.err.println("\t2 ==> date value as a long");
            System.err.println("\t3 ==> word list");
            System.err.println("<loadFactor>: The ratio of objects to table size,\n\tdenoted by alpha n/m");
            System.err.println("<debugLevel>: 0 ==> print summary of experiment");
            System.err.println("\t1 ==> save the two hash tables to a file at the end");
            System.err.println("\t2 ==> print debugging output for each insert");
        }
    }
}
