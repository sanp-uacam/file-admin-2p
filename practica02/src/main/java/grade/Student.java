package grade;

public class Student {
    private int idStudent; // Matrícula
    private String name;
    private String lastName;
    private int degree; // <-- Ya corregido a tipo entero
    private String semester;
    private double promedio;

    // 📝 Nuevos atributos de diferentes tipos
    private boolean activo;     // tipo boolean
    private char genero;        // tipo char
    private String correo;      // tipo String

    // Setters
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDegree(int degree) { this.degree = degree; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public void setGenero(char genero) { this.genero = genero; }
    public void setCorreo(String correo) { this.correo = correo; }

    // Getters
    public int getIdStudent() { return idStudent; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public int getDegree() { return degree; }
    public String getSemester() { return semester; }
    public double getPromedio() { return promedio; }
    public boolean isActivo() { return activo; }
    public char getGenero() { return genero; }
    public String getCorreo() { return correo; }

    // Método toString mejorado
    public String toString() {
        return idStudent + "," + name + "," + lastName + "," +
               degree + "," + semester + "," + promedio + "," +
               activo + "," + genero + "," + correo;
    }
}
