package grade;

import java.io.Serializable;

//Clase que representa a un estudiante, implementa Serializable para persistencia binaria.
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idStudent; //Matricula
    private String name;
    private String lastName;
    private Degree degree;
    private byte semester;
    private Double promedio;
     // Atributos adicionales
    private EstadoAcademico estadoAcademico;
    private String phoneNumber;
    private String address;
    
//metodos setters
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDegree(Degree degree) { this.degree = degree; }
    public void setSemester(byte semester) { this.semester = semester; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }
    // Setters de los 3 atributos nuevos
    public void setEstadoAcademico(EstadoAcademico estadoAcademico) { this.estadoAcademico = estadoAcademico; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }

//metodos getters
    public int getIdStudent() { return idStudent;   }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public Degree getDegree() { return degree; }
    public byte getSemester() { return semester; }
    public Double getPromedio() { return promedio; }
    // Getters de los 3 atributos nuevos
    public EstadoAcademico getEstadoAcademico() { return estadoAcademico; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }


    @Override
    public String toString() { // Para formato de texto simple (CSV o similar)
        String message = this.idStudent + "," + this.name + "," + this.lastName + "," + this.degree + "," + this.semester + "," 
                + this.promedio + "," + this.estadoAcademico + "," + this.phoneNumber + "," + this.address;
        return message;
    }
    
    /**
     * Convierte el objeto Student a una cadena de formato JSON simple.
     * retorna un String en formato JSON.
     */
    public String toJson() {
        // StringBuilder para construir la cadena
        StringBuilder jsonBuilder = new StringBuilder();

        // Abrir el objeto JSON
        jsonBuilder.append("{");

        // Utilizando String.format para un formato JSON más limpio y estándar
        jsonBuilder.append(String.format(
            "\"idStudent\": %d, \"name\": \"%s\", \"lastName\": \"%s\", \"degree\": \"%s\", \"semester\": %d, \"promedio\": %.2f, \"estadoAcademico\": \"%s\", \"phoneNumber\": \"%s\", \"address\": \"%s\"",
            this.idStudent,
            this.name,
            this.lastName,
            this.degree.name(), // Usamos name() para obtener el String del enum
            this.semester,
            this.promedio,
            this.estadoAcademico.name(),
            this.phoneNumber,
            this.address
        ));

        // Cerrar el objeto JSON
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}