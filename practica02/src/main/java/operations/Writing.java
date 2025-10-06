package operations;

import grade.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Writing {

    public void write(String fileName, Student student) {
        System.out.println("Escribiendo información en el archivo");
        
        try {
            FileWriter fw = new FileWriter(fileName);
            //FileWriter fw = new FileWriter (fileName,true);
            BufferedWriter bw = new BufferedWriter(fw);
            String almacenar = student.toString();
            bw.write(almacenar);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
            e.printStackTrace();
        }
    }
}
