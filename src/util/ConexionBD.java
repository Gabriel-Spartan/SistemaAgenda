// autor: gabriel0llerena@gmail.com/Gabriel-Spartan
package util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final Dotenv dotenv = Dotenv.configure()
        .directory("./")        // Ruta al .env (raíz del proyecto)
        .ignoreIfMissing()      // No falla si no existe (opcional)
        .load();

    private static final String HOST = dotenv.get("DB_HOST", "localhost");
    private static final String PORT = dotenv.get("DB_PORT", "3306");
    private static final String DB = dotenv.get("DB_NAME", "agenda_db");
    private static final String USER = dotenv.get("DB_USER", "root");
    private static final String PASSWORD = dotenv.get("DB_PASS", "");

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB +
            "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
