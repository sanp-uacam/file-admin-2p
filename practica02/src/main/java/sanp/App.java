//ADIEL JAFET POOT PECH ADMINISTRACION DE ARCHIVOS APP MAIN
package sanp;

import grade.Degree;
import grade.Student;
import grade.EstadoAcademico; 
import operations.Reading;
import operations.Writing;

import java.util.*;


//Clase principal que implementa el CRUD para la base de datos de alumnos.
public class App {
    
    private static final String BIN_FILE = "studentsDB.bin";
    private static final String JSON_FILE = "studentsDB.json";
    
    private static Scanner scanner = new Scanner(System.in);
    private static Reading reading = new Reading();
    private static Writing writing = new Writing();
    private static int nextId = 1;
    

    public static void main(String[] args) {
        int formato = elegirFormato(); // 1=ObjectStream, 2=JSON

        // Inicializamos nextId asegurándonos de que sea mayor que el ID más alto existente
        try {
            List<Student> initialLoad = loadStudents(formato);
            if (!initialLoad.isEmpty()) {
                nextId = initialLoad.stream()
                                  .mapToInt(Student::getIdStudent)
                                  .max()
                                  .orElse(0) + 1;
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar la base de datos inicial. Iniciando con ID 1.");
        }
        
        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }
            switch (opcion) {
                case 1: crearAlumno(formato); break;
                case 2: leerAlumnos(formato); break;
                case 3: actualizarAlumno(formato); break;
                case 4: eliminarAlumno(formato); break;
                case 5: System.out.println("¡Hasta pronto!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    /** Muestra el menú de opciones al usuario. */
    private static void mostrarMenu() {
        System.out.println("\n--- Menú CRUD de Alumnos ---");
        System.out.println("1. Crear nuevo alumno");
        System.out.println("2. Ver todos los alumnos");
        System.out.println("3. Actualizar alumno por Matricula (ID)");
        System.out.println("4. Eliminar alumno por Matricula (ID)");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    /** Permite al usuario elegir el formato de persistencia. */
    private static int elegirFormato() {
        System.out.println("Tipo de formato de archivo desea usar:");
        System.out.println("1. Binario Serialización (ObjectStream - " + BIN_FILE + ")");
        System.out.println("2. JSON (Texto - " + JSON_FILE + ")");
        System.out.print("Seleccione 1 o 2: ");
        try {
            int formato = Integer.parseInt(scanner.nextLine());
            if (formato >= 1 && formato <= 2) {
                return formato;
            }
        } catch (NumberFormatException e) {
            // Ignoramos el error, se pedirá de nuevo
        }
        System.out.println("Selección no valida. Usando Binario Serialización por defecto.");
        return 1; // Por defecto, ObjectStream
    }
    
    // --- Lógica de Persistencia Centralizada ---

    /** Carga la lista de alumnos desde el archivo según el formato. */
    private static List<Student> loadStudents(int formato) {
        if (formato == 1) { // ObjectStream
            return reading.readBinary(BIN_FILE);
        } else { // JSON
            return reading.readJson(JSON_FILE);
        }
    }
    
    /** Guarda la lista de alumnos en el archivo según el formato. */
    private static void saveStudents(int formato, List<Student> alumnos) {
        if (formato == 1) { // ObjectStream
            writing.writeBinary(BIN_FILE, alumnos);
        } else { // JSON
            writing.writeJson(JSON_FILE, alumnos);
        }
    }

    // --- Operaciones CRUD ---

    /** 1. Crea un nuevo alumno. */
    private static void crearAlumno(int formato) {
        System.out.println("\n--- Crear Nuevo Alumno ---");
        Student newStudent = pideDatosStudent(nextId++); // Obtiene datos del usuario y asigna ID
        
        List<Student> alumnos = loadStudents(formato);
        alumnos.add(newStudent);
        saveStudents(formato, alumnos);
        System.out.println("Alumno con ID " + newStudent.getIdStudent() + " creado y guardado.");
    }

    /** 2. Lee y muestra todos los alumnos. */
    private static void leerAlumnos(int formato) {
        System.out.println("\n--- Lista de Alumnos ---");
        List<Student> alumnos = loadStudents(formato);
        
        if (alumnos.isEmpty()) {
            System.out.println("La base de datos está vacía.");
            return;
        }

        System.out.println("ID \t| \tNombre \t| \tApellido \t| \tCarrera \t| \tSemestre \t| \tPromedio \t| \tEstado Academico \t| \tTelefono\t | \tDireccion"); 
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        alumnos.forEach(s -> System.out.println(s.toString().replace(",", " | ")));
    }

    private static void actualizarAlumno(int formato) {
        System.out.println("\n--- Actualizar Alumno ---");
        System.out.print("Ingrese la Matricula (ID) del alumno a actualizar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            List<Student> alumnos = loadStudents(formato);
            
            Optional<Student> studentToUpdate = alumnos.stream()
                .filter(s -> s.getIdStudent() == id)
                .findFirst();

            if (studentToUpdate.isPresent()) {
                Student oldStudent = studentToUpdate.get();
                System.out.println("Alumno encontrado: " + oldStudent.getName() + " " + oldStudent.getLastName());
                System.out.println("Presione ENTER para omitir un campo y mantener el valor actual.");

                // --- 1. Name (String) ---
                System.out.print("Nombre (" + oldStudent.getName() + "): ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    oldStudent.setName(nuevoNombre);
                }

                // --- 2. LastName (String) ---
                System.out.print("Apellido (" + oldStudent.getLastName() + "): ");
                String nuevoApellido = scanner.nextLine();
                if (!nuevoApellido.isEmpty()) {
                    oldStudent.setLastName(nuevoApellido);
                }

                // --- 3. Degree (Enum) ---
                oldStudent.setDegree(pideEnumActualizado("Carrera", Degree.class, oldStudent.getDegree()));

                // --- 4. Semester (Numeric) ---
                System.out.print("Semestre (" + oldStudent.getSemester() + "): ");
                String nuevoSemestreStr = scanner.nextLine();
                if (!nuevoSemestreStr.isEmpty()) {
                    try {
                        oldStudent.setSemester(Byte.parseByte(nuevoSemestreStr));
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Se mantiene el valor anterior (" + oldStudent.getSemester() + ").");
                    }
                }
                
                // --- 5. Promedio (Numeric) ---
                System.out.print("Promedio (" + oldStudent.getPromedio() + "): ");
                String nuevoPromedioStr = scanner.nextLine();
                if (!nuevoPromedioStr.isEmpty()) {
                    try {
                        oldStudent.setPromedio(Double.parseDouble(nuevoPromedioStr));
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Se mantiene el valor anterior (" + oldStudent.getPromedio() + ").");
                    }
                }

                // --- 6. Estado Académico (Enum) ---
                oldStudent.setEstadoAcademico(pideEnumActualizado("Estado Académico", EstadoAcademico.class, oldStudent.getEstadoAcademico())); // CAMBIO

                // --- 7. PhoneNumber (String) ---
                System.out.print("Teléfono (" + oldStudent.getPhoneNumber() + "): ");
                String nuevoTelefono = scanner.nextLine();
                if (!nuevoTelefono.isEmpty()) {
                    oldStudent.setPhoneNumber(nuevoTelefono);
                }

                // --- 8. Address (String) ---
                System.out.print("Dirección (" + oldStudent.getAddress() + "): ");
                String nuevaDireccion = scanner.nextLine();
                if (!nuevaDireccion.isEmpty()) {
                    oldStudent.setAddress(nuevaDireccion);
                }

                // --- Save ---
                saveStudents(formato, alumnos);
                System.out.println("Alumno con ID " + id + " actualizado y guardado.");
            } else {
                System.out.println("No se encontró ningún alumno con la Matrícula (ID) " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    /** 4. Elimina un alumno. */
    private static void eliminarAlumno(int formato) {
        System.out.println("\n--- Eliminar Alumno ---");
        System.out.print("Ingrese la Matricula del alumno a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            List<Student> alumnos = loadStudents(formato);
            
            boolean removed = alumnos.removeIf(s -> s.getIdStudent() == id);

            if (removed) {
                saveStudents(formato, alumnos);
                System.out.println("Alumno con ID " + id + " eliminado y base de datos guardada.");
            } else {
                System.out.println("No se encontró ningún alumno con la Matrícula (ID) " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }


    // --- Métodos Auxiliares de Interacción con Usuario ---

    /** Pide todos los datos del alumno por consola (para CREAR). */
    private static Student pideDatosStudent(int id) {
        Student s = new Student();
        s.setIdStudent(id);
        
        System.out.println("Asignando Matrícula: " + id);
        
        System.out.print("Nombre: ");
        s.setName(scanner.nextLine());
        
        System.out.print("Apellido: ");
        s.setLastName(scanner.nextLine());

        s.setDegree(pideEnum("Carrera", Degree.class));
        
        System.out.print("Semestre (1-10): ");
        s.setSemester(Byte.parseByte(scanner.nextLine()));
        
        System.out.print("Promedio (ej. 8.5): ");
        s.setPromedio(Double.parseDouble(scanner.nextLine()));

        s.setEstadoAcademico(pideEnum("Estado Académico", EstadoAcademico.class)); // CAMBIO
        
        System.out.print("Teléfono: ");
        s.setPhoneNumber(scanner.nextLine());
        
        System.out.print("Dirección: ");
        s.setAddress(scanner.nextLine());

        return s;
    }

     //Método genérico para pedir al usuario la selección de un valor de Enum (para CREAR).

    private static <E extends Enum<E>> E pideEnum(String prompt, Class<E> enumClass) {
        E[] values = enumClass.getEnumConstants();
        E selectedValue = null;
        boolean valid = false;

        do {
            System.out.println("Seleccione " + prompt + " (Ingrese el número):");
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i].name());
            }
            System.out.print("Opción: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= values.length) {
                    selectedValue = values[choice - 1];
                    valid = true;
                } else {
                    System.out.println("Opción fuera de rango.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, por favor ingrese un número.");
            }
        } while (!valid);
        
        return selectedValue;
    }
    
    /**
     * Pide un Enum para ACTUALIZAR, permitiendo omitir (Enter).
     */
    private static <E extends Enum<E>> E pideEnumActualizado(String prompt, Class<E> enumClass, E valorActual) {
        E[] values = enumClass.getEnumConstants();
        
        System.out.println("Seleccione " + prompt + " (Actual: " + valorActual.name() + ")");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i].name());
        }
        System.out.print("Opción (Deje en blanco para no cambiar): ");
        
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return valorActual; // Devuelve el valor original si se presiona Enter
        }

        try {
            int choice = Integer.parseInt(input);
            if (choice > 0 && choice <= values.length) {
                return values[choice - 1]; // Devuelve el nuevo valor seleccionado
            } else {
                System.out.println("Opción fuera de rango. Se mantiene el valor anterior.");
                return valorActual; // Si el número es inválido, mantiene el original
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Se mantiene el valor anterior.");
            return valorActual; // Si la entrada no es número, mantiene el original
        }
    }
}