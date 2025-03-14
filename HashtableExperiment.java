public class HashtableExperiment {
    public static void main(String[] args) {
        
        LinearProbing linearTable = new LinearProbing(8);
        HashObject object1 = new HashObject(1);
        linearTable.insert(object1);

        linearTable.toString();
    }
}
