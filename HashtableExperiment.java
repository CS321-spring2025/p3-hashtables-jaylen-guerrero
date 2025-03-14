public class HashtableExperiment {
    public static void main(String[] args) {
        
        LinearProbing linearTable = new LinearProbing(8);
        HashObject obj0 = new HashObject(8);
        HashObject obj1 = new HashObject(91);
        HashObject obj2 = new HashObject(9);
        HashObject obj3 = new HashObject(24);
        HashObject obj4 = new HashObject(9);
        HashObject obj5 = new HashObject(88);
        HashObject obj6 = new HashObject(32);
        HashObject obj7 = new HashObject(9);

        linearTable.insert(obj0);
        linearTable.insert(obj1);
        linearTable.insert(obj2);
        linearTable.insert(obj3);
        linearTable.insert(obj4);
        linearTable.insert(obj5);
        linearTable.insert(obj6);
        linearTable.insert(obj7);

        System.out.println(linearTable.toString());
    }
}
