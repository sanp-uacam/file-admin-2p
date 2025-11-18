//ADIEL JAFET POOT PECH ADMINISTRACION DE ARCHIVOS WRITING
package operations;

import grade.Student;

import java.io.*;
import java.util.List;

/**
 * Clase para manejar la escritura (guardado) de la base de datos de alumnos en diferentes formatos.
 * Implementa File*, Buffered*, ObjectStream.
 */
public class Writing {

    /**
     * Escribe la lista completa de objetos Student en un archivo binario usando serialización 
     * (ObjectStream sobre BufferedOutputStream sobre FileOutputStream).
     * el parametro binFileName es el nombre del archivo binario.
     * retorna unaa lista de objetos Student a guardar.
     */
    public void writeBinary(String binFileName, List<Student> alumnos) {
        System.out.println("Escribiendo " + alumnos.size());
        // Usamos BufferedOutputStream para eficiencia
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new BufferedOutputStream(
             new FileOutputStream(binFileName)))) 
        {
            // Se escribe la lista completa como un solo objeto serializado
            oos.writeObject(alumnos);
            oos.flush(); // Asegurarse que el buffer se escriba
            System.out.println("Guardado Binario.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo binario: " + binFileName);
            e.printStackTrace();
        }
    }

    /**
     * Escribe la lista completa de objetos Student en un archivo JSON (BufferedWriter sobre FileWriter).
     * el parametro jsonFileName El nombre del archivo JSON.
     * regresa una lista de objetos Student a guardar.
     */
    public void writeJson(String jsonFileName, List<Student> alumnos) {
        System.out.println("Escribiendo " + alumnos.size());
        // Usamos BufferedWriter para escribir el archivo de manera eficiente
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFileName, false))) { 
            // 'false' en FileWriter indica que sobrescribirá el archivo (no append)
            for (Student student : alumnos) {
                // Escribimos el objeto Student como una cadena JSON y añadimos un salto de línea
                bw.write(student.toJson());
                bw.newLine();
            }
            bw.flush();
            System.out.println("Guardado JSON");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + jsonFileName);
            e.printStackTrace();
        }
    }
}