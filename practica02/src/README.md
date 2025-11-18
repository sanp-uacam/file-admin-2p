# Sistema de Gestión CRUD de Alumnos (Java SE)

## Adiel Jafet Poot pech Administracion de archivos

Este proyecto implementa una **aplicación de consola** para gestionar un
registro de estudiantes utilizando operaciones **CRUD** (Crear, Leer,
Actualizar, Eliminar).

Una característica central es la capacidad de manejar la **persistencia
de datos en dos formatos distintos**, demostrando el uso eficiente de
las clases de I/O de Java:

-   **Binario:** Serialización de Java (`ObjectOutputStream`).
-   **JSON:** Formato de texto simple (`BufferedWriter`).

## 📁 Estructura del Proyecto

    ├── grade/
    │   ├── Degree.java          # Enum de las carreras disponibles.
    │   ├── EstadoAcademico.java # 🆕 Enum con el estado académico (REGULAR, IRREGULAR, etc.).
    │   └── Student.java         # Clase modelo del estudiante (implementa Serializable).
    ├── operations/
    │   ├── Reading.java         # Lógica para leer la base de datos (Binario y JSON).
    │   └── Writing.java         # Lógica para escribir/guardar la base de datos (Binario y JSON).
    └── sanp/
        └── App.java             # Clase principal (Main) con el menú de consola y la lógica CRUD.

## ⚙️ Persistencia de Datos

La aplicación usa los archivos **`studentsDB.bin`** o
**`studentsDB.json`** para la persistencia.\
Al iniciar, el usuario elige el formato a utilizar para la sesión.
### **1. Formato JSON (`studentsDB.json`)**

Cada línea en el archivo representa un alumno en formato de texto JSON,
legible y fácil de inspeccionar.

### 📌 Esquema del Objeto Student

| Campo           | Tipo    | Descripción                                       |
|-----------------|---------|---------------------------------------------------|
| idStudent       | int     | Matrícula única                                    |
| name            | String  | Nombre(s)                                          |
| lastName        | String  | Apellidos                                          |
| degree          | String  | Carrera (ej. *ING_SOFTWARE*)                       |
| semester        | byte    | Semestre actual (1–10)                             |
| promedio        | double  | Promedio académico                                 |
| estadoAcademico | String  | Estado Académico (REGULAR, BAJA_TEMPORAL, etc.)    |
| phoneNumber     | String  | Teléfono de contacto                               |
| address         | String  | Dirección física                                   |


### 📝 Ejemplo de Registro JSON

``` json
{
    "idStudent": 101,
    "name": "Juan",
    "lastName": "Perez",
    "degree": "ING_SISTEMAS_COMPUTACIONALES",
    "semester": 5,
    "promedio": 9.25,
    "estadoAcademico": "REGULAR",
    "phoneNumber": "5512345678",
    "address": "Calle Falsa 123"
}
```

### **2. Formato Binario (`studentsDB.bin`)**

Utiliza la **serialización de Java** para guardar la lista completa
(`List<Student>`) como un único objeto binario.

-   Es más rápido para I/O a gran escala.
-   El archivo **no es legible** por humanos.

## 🛠️ Instrucciones de Uso

### ✔️ Compilación

``` bash
javac -d . grade/*.java operations/*.java sanp/*.java
```

### ▶️ Ejecución

``` bash
java sanp.App
```

## 📌 Flujo de Uso en Consola

1.  Selección de Formato (1 = Binario, 2 = JSON).
2.  Crear: Registrar alumno nuevo.
3.  Ver: Mostrar lista.
4.  Actualizar: Modificar datos por ID.
5.  Eliminar: Quitar alumno por ID.
6.  Salir.

### 📌 Funciones del Sistema y su Propósito

| Función                     | Propósito                                                                                                                                                                   | Categoría      |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|
| **main()**                 | Punto de entrada. Inicializa la aplicación, gestiona la carga inicial de datos (Binario o JSON) y ejecuta el bucle principal del menú.                                       | Principal      |
| **crearAlumno()**          | **CREATE.** Solicita los datos del nuevo alumno, asigna el siguiente ID consecutivo y lo agrega a la lista en memoria.                                                       | CRUD           |
| **leerAlumnos()**          | **READ.** Carga la base de datos actual y muestra todos los registros en la consola con formato tabular.                                                                    | CRUD           |
| **actualizarAlumno()**     | **UPDATE.** Busca un alumno por Matrícula (ID) y permite modificar sus atributos. ENTER permite mantener el valor actual de un campo.                                        | CRUD           |
| **eliminarAlumno()**       | **DELETE.** Busca un alumno por Matrícula (ID) y lo elimina de la lista en memoria.                                                                                         | CRUD           |
| **loadStudents()**         | Abstracción de persistencia. Llama a `Reading.readBinary()` o `Reading.readJson()` según el formato elegido.                                                                 | Persistencia   |
| **saveStudents()**         | Abstracción de persistencia. Llama a `Writing.writeBinary()` o `Writing.writeJson()` para guardar los cambios.                                                               | Persistencia   |
| **pideEnum() / pideEnumActualizado()** | Métodos auxiliares genéricos para manejar la entrada de usuario para valores de las enumeraciones (**Degree** y **EstadoAcademico**).                                    | Utilidad       |

## 📦 Paquete `operations` (Persistencia I/O)

Este paquete gestiona la lectura y escritura de archivos, utilizando las clases de la interfaz `java.io`.

### 📄 Detalle de Clases y Funciones

| Clase        | Función                                   | Detalle de I/O                                                                                                                                 |
|--------------|--------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| **Reading.java** | **readBinary(String binFileName)**        | Carga la `List<Student>` desde el archivo binario (`studentsDB.bin`) utilizando `ObjectInputStream` para deserializar el objeto.                |
|              | **readJson(String jsonFileName)**           | Lee cada línea del archivo JSON (`studentsDB.json`) usando `BufferedReader` y llama a `StudentFromJson` para reconstruir el objeto `Student`.   |
|              | **StudentFromJson(String json)**            | Método auxiliar que parsea manualmente una línea JSON simple y la convierte en un objeto `Student`.                                             |
| **Writing.java** | **writeBinary(String binFileName, List<Student> alumnos)** | Escribe la lista completa `List<Student>` como un único objeto serializado utilizando `ObjectOutputStream`.                                      |
|              | **writeJson(String jsonFileName, List<Student> alumnos)** | Itera sobre la lista de alumnos y escribe `student.toJson()` en una nueva línea usando `BufferedWriter`.                                         |

## 3. 📦 Paquete `grade` (Modelo de Datos)

| Clase                | Función / Propósito                                                                                  |
|----------------------|------------------------------------------------------------------------------------------------------|
| **Student.java**     | **toJson():** Convierte el objeto Student en una cadena JSON para guardado.<br>**toString():** Genera una representación simple separada por comas para mostrar en consola. |
| **Degree.java**      | **(Enumeración)** Define las carreras disponibles (ej. *ING_SOFTWARE*).                              |
| **EstadoAcademico.java** | **(Enumeración)** Define el estado académico del estudiante (ej. *REGULAR*).                     |
