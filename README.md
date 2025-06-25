
# 💉 Sistema de Gestión de Consultas Médicas
Permite gestionar pacientes, médicos y consultas en una clínica universitaria, usando Java, Swing y el patrón MVVM.

---

##  Funcionalidades principales

- 🧾 Registro de pacientes y médicos
- 👨‍⚕️ Asignación de consultas médicas
- 📖 Visualización de historiales clínicos
- 🔐 Inicio de sesión con control de acceso por rol (`admin` / `medico`)
- 💾 Persistencia de datos en archivos `.txt`
- 🎨 Interfaz gráfica hecha con Java Swing

---

## 🏛️ Arquitectura del proyecto (MVVM)

```
📦 gestionconsultasmedicas
 ┣ 📂 model         → Entidades: Paciente, Medico, Consulta, Clinica
 ┣ 📂 view          → Interfaces gráficas (Swing)
 ┣ 📂 viewmodel     → Lógica de negocio: ClinicaViewModel
 ┣ 📂 persistencia  → Lectura/escritura de archivos
 ┣ 📂 excepciones   → Validaciones personalizadas
 ┗ 📄 GestionConsultasMedicas.java → Punto de entrada
```

---

## ✅ Requisitos implementados (con ejemplos de código)

### 📐 Patrón MVVM

### 👨‍👩‍👧‍👦 Herencia / Composición / Asociación

- **Herencia**:  
  ```java
  public class Medico extends Persona { ... }
  ```

- **Composición**:
  ```java
  private List<Paciente> pacientes;
  private List<Consulta> consultas;
  ```

- **Asociación**:
  ```java
  public class Consulta {
      private Paciente paciente;
      private Medico medico;
  }
  ```

---

### 🧱 Clase abstracta e interfaz

- **Clase abstracta**:
  ```java
  public abstract class Persona {
      private String nombre;
      public abstract String getTipo();
  }
  ```

- **Interfaz IPersistencia**:
  ```java
  public interface IPersistencia {
      void guardar(Clinica clinica) throws IOException;
      Clinica cargar() throws IOException;
  }
  ```

---

### 📚 Colecciones genéricas

```java
private List<Medico> medicos = new ArrayList<>();
private List<Paciente> pacientes = new ArrayList<>();
private List<Consulta> consultas = new ArrayList<>();
```

---

### 💾 Persistencia en archivos `.txt`

```java
public void guardar(Clinica clinica) {
    guardarPacientes(clinica.getPacientes());
    guardarMedicos(clinica.getMedicos());
    guardarConsultas(clinica.getConsultas());
}
```

```java
public Clinica cargar() {
    Clinica clinica = new Clinica();
    clinica.setPacientes(cargarPacientes());
    clinica.setMedicos(cargarMedicos());
    clinica.setConsultas(cargarConsultas(clinica));
    return clinica;
}
```

---

### ⚠️ Excepciones personalizadas

```java
public class CampoVacioException extends Exception {
    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}
```

```java
if (nombre.isEmpty() || id.isEmpty()) {
    throw new CampoVacioException("Todos los campos son obligatorios.");
}
```

---

### 🖼️ Interfaz gráfica con Java Swing

```java
JLabel lblIdConsulta = new JLabel("ID Consulta:");
JTextField txtIdConsulta = new JTextField();
add(lblIdConsulta); add(txtIdConsulta);
```

---

## 🔐 Control de acceso

| Rol    | Acciones permitidas                                        |
|--------|-------------------------------------------------------------|
| `admin`  | Registrar médicos/pacientes, ver historial completo        |
| `medico` | Asignar consultas, ver historial de pacientes (por ID)     |

---

## 📂 Archivos de persistencia

| Archivo        | Contenido                        |
|----------------|----------------------------------|
| `usuarios.txt`  | id;contraseña;rol                |
| `pacientes.txt` | nombre;id;nacimiento             |
| `medicos.txt`   | nombre;id;especialidad           |
| `consultas.txt` | id;idPaciente;idMedico;síntomas;diagnóstico;tratamiento |

---

## ▶️ Cómo ejecutar

1. Abre el proyecto en **NetBeans**
2. Ejecuta la clase `GestionConsultasMedicas.java`
3. Inicia sesión con un usuario de `usuarios.txt`
4. Usa las funcionalidades según tu rol

---

## 👨‍💻 Autor

- **Nombre**: [TU NOMBRE AQUÍ]
- **Curso**: Programación Orientada a Objetos – Parcial 2
- **Fecha**: Junio 2025

---

## 🖼️ Capturas de pantalla

### Login
*(Inserta aquí una captura del login)*

---

### Menú principal (Admin)
*(Inserta aquí una captura del menú cuando entra un admin)*

---

### Menú principal (Médico)
*(Inserta aquí una captura del menú cuando entra un médico)*

---

### Registro de paciente o médico
*(Inserta aquí una captura del formulario de registro)*

---

### Asignar consulta
*(Inserta aquí una captura del formulario de consulta)*

---

### Historial clínico
*(Inserta aquí una captura del historial por paciente o médico)*

---

## ✅ Conclusión

Este sistema demuestra el uso completo de:
- Principios POO
- Arquitectura MVVM
- Interfaces gráficas con Swing
- Control de acceso por roles
- Persistencia sin base de datos

Listo para ser ejecutado y entregado.
