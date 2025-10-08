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

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader archivoLectura = new BufferedReader(fr);

            System.out.println("Los alumnos son:");
            line = archivoLectura.readLine();

            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                Student student = new Student(); // crear nuevo alumno por línea

                student.setIdStudent(Integer.parseInt(st.nextToken()));
                student.setName(st.nextToken());
                student.setDegree(Integer.parseInt(st.nextToken())); // ✅ corregido

                System.out.println(student.toString());
                line = archivoLectura.readLine();
            }

            archivoLectura.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("No se pudo leer del archivo");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir un número. Revisa el formato del archivo.");
            e.printStackTrace();
        }
    }
}
