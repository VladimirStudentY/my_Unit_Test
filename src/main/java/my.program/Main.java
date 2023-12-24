package my.program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    String[] dir = {"src", "res", "savegames", "temp"};
    String[] arrayFile = {"Main.java", "Utils.java", "temp.txt"};
    String[] subcatalogs = {"main", "test", "drawables", "vectors", "icons"};
    static StringBuilder textLog = new StringBuilder(new Date() + "\n");

    // ***************
    public Main() {
        createDir(dir,subcatalogs);
        fileCreate(arrayFile);
//        fileCreate("D:/Games" + "/" +"UnitDemo.txt");
        logSave(textLog.toString());
    }
    // ---------------------
    public static void main(String[] args) {

        new Main();
        System.out.println("Hello world!");
    }

    // ---------------------
    public static void createDir(String[] array, String[] subcatalogs) {
        for (String tempArray : array) {
            createKatalog("D:/Games" + "/" + tempArray);
        }
        for (int i = 0; i < subcatalogs.length; i++) {
            String pathDir = (i < 2) ? "D:/Games/src/" + subcatalogs[i] : "D:/Games/res/" + subcatalogs[i];
            createKatalog(pathDir);
        }
    }

    // ---------------------
    public static boolean createKatalog(String dirPath) {
        File dir = new File(dirPath);
        if (dir.mkdir()) {
            textLog.append("\tКаталог " + dirPath + " \tсоздан\n");
            return true;
        }
        return false;
    }

    // ---------------------
    private void fileCreate(String name) {
        File newFile = new File(name);
        try {
            if (newFile.createNewFile()) {
                textLog.append("\tФайл " + name + "\t создан успешно\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // ---------------------
    protected static void logSave(String logText) {
        try (FileWriter writer = new FileWriter("D:/Games/temp/temp.txt", true)) {
            writer.write(logText);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // ---------------------


    private void fileCreate(String[] arrayFile) {
        for (int i = 0; i < arrayFile.length - 1; i++) {
            String pathFile = (i < arrayFile.length - 1 ? "D:/Games/src/main/" + arrayFile[i] : "D:/Games/temp/" + arrayFile[i]);
            fileCreate(pathFile);
        }
    }

}