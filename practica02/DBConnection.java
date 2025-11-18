public class DBConnection implements IDataBase {

    @Override
    public void connect() {
        System.out.println("Conectado a la base de datos (archivo binario).");
    }

    @Override
    public void disconnect() {
        System.out.println("Desconectado.");
    }
}
