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
            Student student = new Student();
            System.out.println("Los alumnos son:");
            line = archivoLectura.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line,",");
                student.setIdStudent(Integer.parseInt(st.nextToken()));
                student.setName(st.nextToken());
                student.setDegree(Integer.parseInt(st.nextToken()));
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
        }
    }
}
