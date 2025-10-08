package grade;

public class Student {
    private int idStudent; // Matrícula
    private String name;
    private String lastName;
    private int degree; // <-- Se cambió a tipo entero
    private String semester;
    private double promedio;

    // 📝 Nuevos atributos de diferente tipo
    private String email;       // tipo String
    private int age;            // tipo int
    private boolean active;     // tipo boolean

    // ---- SETTERS ----
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDegree(int degree) { this.degree = degree; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setActive(boolean active) { this.active = active; }

    // ---- GETTERS ----
    public int getIdStudent() { return idStudent; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public int getDegree() { return degree; }
    public String getSemester() { return semester; }
    public double getPromedio() { return promedio; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public boolean isActive() { return active; }

    // ---- toString ----
@Override
public String toString() {
    return idStudent + ", " 
            + name + ", " 
            + lastName + ", " 
            + degree + ", " 
            + semester + ", " 
            + promedio + ", " 
            + email + ", " 
            + age + ", " 
            + active;
}

    }

