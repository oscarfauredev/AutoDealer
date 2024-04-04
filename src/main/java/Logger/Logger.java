package Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final String LOG_FILE = "AutoDealerLogs.txt";

    public static void log(String action, String tableName, String values) {
        try {
            File file = new File(LOG_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String logEntry = String.format("[%s] %s %s %s", timeStamp, action, tableName, values);
                writer.println(logEntry);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier de logs : " + e.getMessage());
        }
    }

    public static void logAdd(String tableName, String values) {
        log("add", tableName, values);
    }

    public static void logUpdate(String tableName, String values) {
        log("update", tableName, values);
    }
    
    public static void logDelete(String tableName, String values) {
        log("delete", tableName, values);
    }
}

