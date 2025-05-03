# 📅 Sistema de Agenda con Recordatorios

Sistema desarrollado en Java con interfaz gráfica Swing que permite agendar tareas diarias, emitir recordatorios en tiempo real mediante hilos, y generar reportes en PDF y gráficos usando JasperReports.

## 🚀 Tecnologías y herramientas utilizadas

- Java (JDK 23)
- MySQL (XAMPP)
- Git & GitHub
- JasperReports 6.0.0
- JCalendar
- Swing (AbsoluteLayout)
- Apache Commons (beanutils, collections, digester, logging)
- iTextPDF
- Obsidian (para documentación técnica)

---

## 🧱 Arquitectura del proyecto

El proyecto está estructurado bajo el patrón **MVC + DAO**, facilitando mantenimiento y escalabilidad.

```
src/
├── model/ ← Clases como Usuario, Evento
├── dao/ ← Acceso a la base de datos (EventoDAO, UsuarioDAO)
├── controller/ ← Lógica de negocio (validador, temporizador)
├── view/ ← Interfaces gráficas (Login, Agenda, Reportes)
├── util/ ← Hilos, conexión BD, utilidades
```

---

## 🗄️ Base de Datos (MySQL)

**Nombre de la base de datos:** `agenda_db`

```sql
CREATE TABLE USUARIOS (
    ID_USU VARCHAR(10) PRIMARY KEY,
    NOM_USU VARCHAR(30) NOT NULL,
    APE_USU VARCHAR(30) NOT NULL,
    CON_USU VARCHAR(255) NOT NULL
    
);

CREATE TABLE EVENTOS (
    ID_USU_PER VARCHAR(10),
    FEC_EVE DATE NOT NULL,
    HOR_EVE TIME NOT NULL,
    TIT_EVE VARCHAR(100) NOT NULL,
    DES_EVE VARCHAR(255),
    FOREIGN KEY (ID_USU_PER) REFERENCES USUARIOS(ID_USU)
);
```

🔧 Instalación y ejecución del proyecto
📥 Clonar el repositorio
Primero, clona el proyecto desde GitHub a tu máquina local:
```
git clone https://github.com/Gabriel-Spartan/SistemaAgenda.git
cd SistemaAgenda
```

⚙️ Requisitos previos
Asegúrate de tener instaladas las siguientes herramientas:

-Java JDK 23
-XAMPP (MySQL)
-IDE (preferiblemente NetBeans o IntelliJ con soporte para Java Swing)
-Git
-Librerías externas en /lib (ya incluidas en el repositorio)
-Obsidian (opcional, para lectura/edición de documentación técnica)

## 🗄️ Configuración de la base de datos
Inicia XAMPP y asegúrate de tener activo MySQL.

Abre phpMyAdmin o el cliente de consola y ejecuta el script database/agenda_db.sql para crear las tablas.

Asegúrate de que la clase ConexionBD.java esté configurada así:
```
String url = "jdbc:mysql://localhost:3306/agenda_db";
String user = "root";
String password = "";
```
💡 Ajusta el user y password según tu configuración de MySQL.
