package grade;

public class Student {
    private int idStudent; //Matricula
    private String name;
    private String lastName;
    private Degree degree; 
    private String semester;
    private double promedio;
    // 📝 agregar 3 atributos de diferente tipos, con set & get

    
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDegree(Degree degree) { this.degree = degree; }
    
    public int getIdStudent() { return idStudent;   }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public Degree getDegree() { return degree; }

    public String toString() {
        String message = "";
        message = this.idStudent + "," + this.name + "," +
        this.degree;
        return message;
    }
}
