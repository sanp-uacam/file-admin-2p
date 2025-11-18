public class Main {
    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        Operations op = new Operations();

        db.connect();

        Grade g1 = new Grade(1, "Carlos", 88.5, "Matematicas", Status.ACTIVE);

        op.create(g1);

        System.out.println("LECTURA DEL ARCHIVO:");
        op.readAll().forEach(System.out::println);

        db.disconnect();
    }
}
