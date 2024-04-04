package GetFileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListDataFromFile {
    public static void readAndDisplayFileContent() throws FileNotFoundException {
        try {
            File file = GetFile.getDataFile();
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                System.out.println(line);
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Le fichier data.txt n'a pas été trouvé dans le projet.");
        }
    }
}
