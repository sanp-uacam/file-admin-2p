# Sistema de Base de Datos Binaria y JSON en Java

Este proyecto implementa un sistema sencillo para guardar, leer, modificar y eliminar registros almacenados en archivos **binarios** y **JSON**, utilizando streams de datos.

---

## 📌 Clases del proyecto

### **1. Clase `Grade`**
Representa un registro en la base de datos.

Atributos:
- `int id`
- `String studentName`
- `double score`
- `String subject`
- `Status status` (ENUM)
- `long timestamp`

Métodos importantes:
- `toString()` → devuelve los datos en formato legible.
- `toJson()` → convierte el objeto en una cadena JSON.
- Constructor que permite crear el objeto desde cada archivo.

---

## 📌 Clase `Operations`
Se encarga de las operaciones CRUD.

### **Métodos Implementados**

#### **1. create(Grade g, String path)**
Guarda un nuevo registro en archivo binario o JSON.
- Usa `FileOutputStream` y `DataOutputStream`.
- Maneja excepciones con `try-with-resources`.

#### **2. read(String path)**
Lee todos los registros guardados.
- En binario usa `DataInputStream`.
- En JSON decodifica línea por línea.

#### **3. update(int id, Grade nuevo, String path)**
- Carga la lista.
- Reemplaza el registro con el ID indicado.
- Guarda todo de nuevo.

#### **4. delete(int id, String path)**
- Lee la lista.
- Elimina el registro que coincida.
- Vuelve a escribir los datos.

---

## 📌 **Esquema JSON**

El proyecto guarda objetos con esta estructura:

```json
{
  "id": 1,
  "studentName": "Juan Perez",
  "score": 95.5,
  "subject": "Math",
  "status": "APPROVED",
  "timestamp": 1731633000
}
