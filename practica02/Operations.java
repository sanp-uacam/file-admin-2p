import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Operations {

    private final String file = "grades.dat";

    public void create(Grade g) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, true))) {
            dos.writeInt(g.getId());
            dos.writeUTF(g.toString());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<String> readAll() {
        List<String> list = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                int id = dis.readInt();
                String data = dis.readUTF();
                list.add("ID=" + id + " | " + data);
            }
        } catch (EOFException eof) {} 
        catch (IOException e) { e.printStackTrace(); }

        return list;
    }

    public void update(int idToFind, Grade newData) {
        List<String> rows = readAll();
        List<String> newRows = new ArrayList<>();

        for (String row : rows) {
            if (row.startsWith("ID=" + idToFind)) {
                newRows.add("ID=" + newData.getId() + " | " + newData.toString());
            } else newRows.add(row);
        }
        rewrite(newRows);
    }

    public void delete(int idToDelete) {
        List<String> rows = readAll();
        List<String> newRows = new ArrayList<>();

        for (String row : rows) {
            if (!row.startsWith("ID=" + idToDelete)) newRows.add(row);
        }
        rewrite(newRows);
    }

    private void rewrite(List<String> data) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (String row : data) {
              String[] parts = row.split("\\|");
                int id = Integer.parseInt(parts[0].replace("ID=", "").trim());
                dos.writeInt(id);
                dos.writeUTF(parts[1].trim());
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
