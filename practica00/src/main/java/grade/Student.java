package grade;

public class Student {
    private int idStudent;
    private String name;
    private String degree;

    
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setDegree(String degree) { this.degree = degree; }
    
    public int getIdStudent() { return idStudent;   }
    public String getname() { return name; }
    public String getDegree() { return degree; }

    public String toString() {
        String message = "";
        message = this.idStudent + "," + this.name + "," +
        this.degree;
        return message;
    }
}
