package grade;

public class DegreeFI {
    private int idDegree;
    private String degreeName;

    // 📝 Agregar set & get para degreeName
    void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    String getDegreeName() {
        return this.degreeName;
    }

    void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    int getIdDegree() {
        return this.idDegree;
    }
}
