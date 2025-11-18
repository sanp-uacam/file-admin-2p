# Manual Breve de Usuario

## Compilación
- **nginx**  
  - Copiar código  
- **javac *.java** - Compila todos los archivos Java

## Ejecución
- **css**  
  - Copiar código  
- **java Main** - Ejecuta la aplicación principal

## Funcionalidades

### Crear un registro
El sistema solicitará los siguientes datos:
- ID
- Nombre  
- Calificación
- Materia
- Estado (enum)

### Lectura
Muestra todos los registros almacenados en el sistema.

### Actualización (Update)
Requiere el ID del registro que será modificado.

### Eliminación (Delete)  
Elimina el registro con el ID indicado.

## Tipos de archivos soportados

| Tipo    | Extensión | Descripción |
|---------|-----------|-------------|
| Binario | .dat      | Guardado con DataOutputStream |
| JSON    | .json     | Guardado en formato JSON por línea |

---

*Este manual proporciona una guía básica para el uso del sistema.*