package operations;

import grade.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Reading {
    public void read(String fileName) {
        String line = "";
        FileReader fr;
        
        try {
            fr = new FileReader(fileName);
            BufferedReader archivoLectura = new BufferedReader(fr);
            System.out.println("Los alumnos son:");

            // Leer línea por línea del archivo
            line = archivoLectura.readLine();
            while (line != null) {
                Student student = new Student(); // Crear nuevo objeto por cada línea
                StringTokenizer st = new StringTokenizer(line, ",");

                // Asignar los valores
                student.setIdStudent(Integer.parseInt(st.nextToken()));
                student.setName(st.nextToken());
                student.setDegree(Integer.parseInt(st.nextToken())); // ✅ Convertir a int

                // Mostrar alumno
                System.out.println(student.toString());

                // Leer la siguiente línea
                line = archivoLectura.readLine();
            }

            archivoLectura.close();
        } catch (FileNotFoundException e) {
            System.out.println("❌ No se pudo encontrar el archivo: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ No se pudo leer el archivo correctamente");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: el campo 'degree' no es un número válido en el archivo");
        }
    }
}
