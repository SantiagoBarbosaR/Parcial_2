
# 💉 Sistema de Gestión de Consultas Médicas
sistema para gestionar las consultas médicas en una clínica. El sistema debe permitir registrar pacientes, asignar médicos a consultas, almacenar el historial de consultas, y permitir búsquedas por paciente o médico.

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

## 🖼️ Capturas de pantalla

### Login

![image](https://github.com/user-attachments/assets/b67926d5-4c68-42c8-b6c8-21e2aa47c1a7)
![image](https://github.com/user-attachments/assets/b67926d5-4c68-42c8-b6c8-21e2aa47c1a7)

---

### Menú principal (Admin)

![image](https://github.com/user-attachments/assets/7e7921ac-fe73-4366-a45e-d8b50a2f9eab)
![image](https://github.com/user-attachments/assets/7e7921ac-fe73-4366-a45e-d8b50a2f9eab)

---

### Menú principal (Médico)

![image](https://github.com/user-attachments/assets/3056ec5e-9ddf-4af1-bc41-c42282af2a17)
![image](https://github.com/user-attachments/assets/3056ec5e-9ddf-4af1-bc41-c42282af2a17)

---

### Registro de paciente o médico

![image](https://github.com/user-attachments/assets/71281d31-1104-41d3-8171-a4e2a391e60d)
![image](https://github.com/user-attachments/assets/71281d31-1104-41d3-8171-a4e2a391e60d)
El de Paciente es igual pero sin contraseña, cambia segun el JComboBox

---

### Asignar consulta

![image](https://github.com/user-attachments/assets/67407386-aa8c-4b9d-9a50-5ece9017280e)
![image](https://github.com/user-attachments/assets/67407386-aa8c-4b9d-9a50-5ece9017280e)

---

### Historial clínico

![image](https://github.com/user-attachments/assets/5f20a1ba-6d57-475e-89f9-8673a42a8a06)
![image](https://github.com/user-attachments/assets/5f20a1ba-6d57-475e-89f9-8673a42a8a06)
El histria cunado estamos en una seccion de medico es igual pero sin la opcion de ver consultas de medico ya que no tiene sentido que vea las consultas de otros medicos

---

## 👨‍💻 Autor

- **Nombre**: Santiago Barbosa
- **Curso**: Programación Orientada a Objetos
- **Fecha**: Junio 2025

