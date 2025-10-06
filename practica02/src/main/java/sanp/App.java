package sanp;

import grade.Student;
import operations.Reading;
import operations.Writing;

public class App 
{
    public static void main( String[] args )
    {
        Student student;
        student = readStudent();
        saveStudent(student);
        loadStudent();
    }

    public static Student readStudent(){
        Student student = new Student();
        student.setName("Sergio");
        student.setIdStudent(1);
        student.setDegree(1);
        return student;
    }

    public static void saveStudent(Student student){
        Writing writer = new Writing();
        writer.write("studentsDB.txt", student);
    }
    
    public static void loadStudent(){
        Reading reader = new Reading();
        reader.read("studentsDB.txt");
    }
}
