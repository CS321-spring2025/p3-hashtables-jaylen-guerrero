public class HashtableExperiment {
    public static void main(String[] args) {
        Hashtable linearHash = new LinearProbing(10);
        HashObject object1 = new HashObject("Hello");

        if (linearHash.search(object1) == object1) {
            System.out.println("Object found");
        } else {
            System.out.println("Object not found");
        }
    }
}
