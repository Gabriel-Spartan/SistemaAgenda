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

En caso de hacer pruebas puede utilizar el `datosPrueba.sql` que tiene datos con los que se puede probar el funionamiento del sistema

⚠️ Asegúrate de que NetBeans esté usando un JDK compatible con el proyecto.
Puedes verificarlo en:
Menú `Tools > Java Platforms` y luego asignarlo en `Project > Properties > Libraries > Java Platform`.

Se recomienda usar **JDK 17 o superior**.

🔧 Instalación y ejecución del proyecto
📥 Clonar el repositorio
Primero, clona el proyecto desde GitHub a tu máquina local:
```
git clone https://github.com/Gabriel-Spartan/SistemaAgenda.git
cd SistemaAgenda
```
📄 Crear el archivo project.properties en nbproject/
Esto permite cargar automáticamente los .jar desde /lib/ y compilar el proyecto correctamente:

```
annotation.processing.enabled=true
annotation.processing.enabled.in.editor=false
annotation.processing.processor.options=
annotation.processing.processors.list=
annotation.processing.run.all.processors=true
annotation.processing.source.output=${build.generated.sources.dir}/ap-source-output

build.dir=build
build.classes.dir=${build.dir}/classes
build.classes.excludes=**/*.java,**/*.form
build.generated.dir=${build.dir}/generated
build.generated.sources.dir=${build.dir}/generated-sources
build.test.classes.dir=${build.dir}/test/classes
build.test.results.dir=${build.dir}/test/results
build.sysclasspath=ignore

dist.dir=dist
dist.jar=${dist.dir}/SistemaAgenda.jar
dist.javadoc.dir=${dist.dir}/javadoc
dist.jlink.dir=${dist.dir}/jlink
dist.jlink.output=${dist.jlink.dir}/SistemaAgenda
dist.archive.excludes=

includes=**
excludes=
jar.compress=false

# === LIBRERÍAS EXTERNAS ===
file.reference.mysql-connector-j-9.3.0.jar=lib/mysql-connector-j-9.3.0.jar
file.reference.dotenv-java-2.3.0.jar=lib/dotenv-java-2.3.0.jar

javac.classpath=\
    ${file.reference.mysql-connector-j-9.3.0.jar}:\  
    ${file.reference.dotenv-java-2.3.0.jar}
 
javac.deprecation=false
javac.external.vm=true
javac.modulepath=
javac.processormodulepath=
javac.processorpath=${javac.classpath}
javac.compilerargs=

javac.test.classpath=${javac.classpath}:${build.classes.dir}
javac.test.modulepath=
javac.test.processorpath=${javac.test.classpath}

run.classpath=${javac.classpath}:${build.classes.dir}
run.modulepath=${javac.modulepath}
run.jvmargs=

run.test.classpath=${javac.test.classpath}:${build.test.classes.dir}
run.test.modulepath=${javac.test.modulepath}

debug.classpath=${run.classpath}
debug.modulepath=${run.modulepath}
debug.test.classpath=${run.test.classpath}
debug.test.modulepath=${run.test.modulepath}

javadoc.additionalparam=
javadoc.author=false
javadoc.encoding=${source.encoding}
javadoc.html5=false
javadoc.noindex=false
javadoc.nonavbar=false
javadoc.notree=false
javadoc.private=false
javadoc.splitindex=true
javadoc.use=true
javadoc.version=false
javadoc.windowtitle=

jlink.additionalmodules=
jlink.additionalparam=
jlink.launcher=true
jlink.launcher.name=SistemaAgenda

main.class=
manifest.file=manifest.mf
meta.inf.dir=${src.dir}/META-INF
mkdist.disabled=false

platform.active=default_platform
source.encoding=UTF-8
src.dir=src
test.src.dir=test
```

🗂️ Crear el archivo .env en la raíz del proyecto
Este archivo configura los datos de conexión a la base de datos:

```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=agenda_db
DB_USER=root
DB_PASS=
```

⚙️ Requisitos previos
Asegúrate de tener instaladas las siguientes herramientas:

-Java JDK 23
-XAMPP (MySQL)
-IDE (preferiblemente NetBeans o IntelliJ con soporte para Java Swing)
-Git
-Librerías externas en /lib (ya incluidas en el repositorio)
-Obsidian (opcional, para lectura/edición de documentación técnica)

🛠️ Configurar el JDK en NetBeans
Si tu sistema no tiene JDK 23, puedes usar uno compatible. Verifica o cambia el JDK activo en:

**Tools > Java Platforms**

Luego en: **Project > Properties > Libraries > Java Platform**
