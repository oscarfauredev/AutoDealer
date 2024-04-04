package GetFileInfo;

import java.io.File;
import java.io.FileNotFoundException;

public class GetFile {
    public static File getDataFile() throws FileNotFoundException {
        String filePath = "data.txt";
        
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new FileNotFoundException("Le fichier data.txt n'a pas été trouvé dans le projet.");
        }
        
        return file;
    }
}
