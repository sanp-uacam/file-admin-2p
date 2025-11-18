//ADIEL JAFET POOT PECH ADMINISTRACION DE ARCHIVOS READING
package operations;

import grade.Degree;
import grade.Student;
import grade.EstadoAcademico; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Implementa FileInputStream/FileReader, Buffered*, ObjectStream.
 
public class Reading {
    
    /**
     * Lee una lista de objetos Student desde un archivo JSON (FileReader/BufferedReader).
     * el parametro es el nombre del archivo JSON.
     * regresa una lista de objetos Student. Retorna una lista vacía si el archivo no existe o hay un error.
     */
    public List<Student> readJson(String jsonFileName) {
        List<Student> alumnos = new ArrayList<>();
        File file = new File(jsonFileName);
        
        if (!file.exists()) {
            return alumnos;
        }

        System.out.println("Leyendo alumnos en formato JSON (FileReader/BufferedReader)...");
        // Implementa 'try-with-resources' (BufferedReader y FileReader)
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    // Usa el método auxiliar de parseo
                    alumnos.add(StudentFromJson(line));
                } catch (Exception e) {
                    System.out.println("Error al parsear línea JSON: " + line);
                }
            }
            System.out.println("Lectura JSON completada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo JSON: " + jsonFileName);
            e.printStackTrace();
        }
        return alumnos;
    }
    
    /**
     * Lee una lista de objetos Student serializados desde un archivo binario 
     * (ObjectStream/BufferedInputStream/FileInputStream).
     * El parametro es el nombre del archivo binario.
     * regresa una lista de objetos Student. Retorna una lista vacía si el archivo no existe o hay un error.
     */
    public List<Student> readBinary(String binFileName) {
        List<Student> alumnos = new ArrayList<>();
        File file = new File(binFileName);
        
        if (!file.exists() || file.length() == 0) {
            return alumnos;
        }
        
        System.out.println("Leyendo alumnos en formato Binario (ObjectStream/BufferedInputStream/FileInputStream)...");
        // Usa 'try-with-resources' (ObjectStream, BufferedStream, FileStream)
        try (ObjectInputStream ois = new ObjectInputStream(
             new BufferedInputStream(
             new FileInputStream(binFileName)))) 
        {
            alumnos = (List<Student>) ois.readObject();
            System.out.println("Lectura Binaria (ObjectStream) completada exitosamente.");
        } catch (FileNotFoundException e) {
             // Ya manejado por file.exists()
        } catch (IOException e) {
            System.out.println("Error al leer del archivo binario ObjectStream: " + binFileName);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Clase Student no encontrada al deserializar.");
        } catch (ClassCastException e) {
             System.out.println("El contenido del archivo binario no es una lista de Students.");
        }
        
        return alumnos;
    }


    /**
     * Método auxiliar para parsear una línea JSON simple a un objeto Student.
     */
    private Student StudentFromJson(String json) {
        Student s = new Student();
        // Lógica de parseo simple basada en el formato toJson() de Student.java
        try {
            // Reemplaza { y } y " (comillas)
            String jsonContent = json.trim().replace("{","").replace("}","").replace("\"", "");
            // Divide por comas
            String[] parts = jsonContent.split(",");

            for (String part : parts) {
                // Divide por : para obtener clave-valor
                String[] kv = part.split(":");
                if (kv.length < 2) continue; // Saltar partes incompletas

                String key = kv[0].trim();
                String value = kv[1].trim();

                switch (key) {
                    case "idStudent": s.setIdStudent(Integer.parseInt(value)); break;
                    case "name": s.setName(value); break;
                    case "lastName": s.setLastName(value); break;
                    case "degree": s.setDegree(Degree.valueOf(value)); break;
                    case "semester": s.setSemester(Byte.parseByte(value)); break;
                    case "promedio": s.setPromedio(Double.parseDouble(value)); break;
                    case "estadoAcademico": s.setEstadoAcademico(EstadoAcademico.valueOf(value)); break;
                    case "phoneNumber": s.setPhoneNumber(value); break;
                    case "address": s.setAddress(value); break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error de parseo en StudentFromJson para: " + json);
            // Retornar objeto parcialmente poblado o vacío.
        }
        return s;
    }
}