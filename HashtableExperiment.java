public class HashtableExperiment {
    public static void main(String[] args) {
        
        LinearProbing linearProbing = new LinearProbing(8);
        DoubleHashing doubleHash = new DoubleHashing(8);
        HashObject obj0 = new HashObject(8);
        HashObject obj1 = new HashObject(16); 
        HashObject obj2 = new HashObject(24);
        HashObject obj3 = new HashObject(32);
        HashObject obj4 = new HashObject(40);
        HashObject obj5 = new HashObject(88);
        HashObject obj6 = new HashObject(36);
        HashObject obj7 = new HashObject(4); 
        HashObject obj8 = new HashObject(89);
        HashObject obj9 = new HashObject(42);
        HashObject obj10 = new HashObject(3); 
        HashObject obj11 = new HashObject(32);

        HashObject dobj0 = new HashObject(8); 
        HashObject dobj1 = new HashObject(16); 
        HashObject dobj2 = new HashObject(24);
        HashObject dobj3 = new HashObject(32);
        HashObject dobj4 = new HashObject(40); 
        HashObject dobj5 = new HashObject(88); 
        HashObject dobj6 = new HashObject(36);
        HashObject dobj7 = new HashObject(4); 
        HashObject dobj8 = new HashObject(89);
        HashObject dobj9 = new HashObject(42); 
        HashObject dobj10 = new HashObject(3); 
        HashObject dobj11 = new HashObject(32);
        

        doubleHash.insert(dobj0);
        doubleHash.insert(dobj1);
        doubleHash.insert(dobj2);
        doubleHash.insert(dobj3);
        doubleHash.insert(dobj4);
        doubleHash.insert(dobj5);
        doubleHash.insert(dobj6);
        doubleHash.insert(dobj7);
        doubleHash.insert(dobj8);
        doubleHash.insert(dobj9);
        doubleHash.insert(dobj10);
        doubleHash.insert(dobj11);

        linearProbing.insert(obj0);
        linearProbing.insert(obj1);
        linearProbing.insert(obj2);
        linearProbing.insert(obj3);
        linearProbing.insert(obj4);
        linearProbing.insert(obj5);
        linearProbing.insert(obj6);
        linearProbing.insert(obj7);
        linearProbing.insert(obj8);
        linearProbing.insert(obj9);
        linearProbing.insert(obj10);
        linearProbing.insert(obj11);

        doubleHash.delete(dobj3);
        // doubleHash.delete(dobj2);

        linearProbing.delete(obj3);
        // linearProbing.delete(obj2);

        System.out.println(doubleHash.toString());
        System.out.println(linearProbing.toString());

    }
}
