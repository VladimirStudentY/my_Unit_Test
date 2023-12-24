package my.program;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Disabled;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainTest {

    @AfterAll
    public static void completeSuite() {
        System.out.println("Test complete: ");
    }
    @Disabled("Для видимости исполнения тестов")
    @AfterAll
    public static void deinit() throws IOException {
        String[] dir = {"src", "res", "savegames", "temp"};
        String[] subcatalogs = {"main", "test", "drawables", "vectors", "icons"};
        Files.delete(Path.of("D:/Games/temp/temp.txt"));
        for (int i = 0; i < subcatalogs.length; i++) {
            String pathDir = (i < 2) ? "D:/Games/src/" + subcatalogs[i] : "D:/Games/res/" + subcatalogs[i];
            Files.delete(Path.of(pathDir));
        }
        for (String tempArray : dir) {
            Files.delete(Path.of("D:/Games" + "/" + tempArray));
        }
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
    }

    @Test
    @Order(1)
    void createDirTest() {
        // given:
        String[] dir = {"src", "res", "savegames", "temp"};
        String[] subcatalogs = {"main", "test", "drawables", "vectors", "icons"};
        // when:
        Main.createDir(dir, subcatalogs);
        boolean srcDir = Files.isDirectory(Path.of("D:/Games" + "/" + "src/"));
        boolean resDir = Files.isDirectory(Path.of("D:/Games" + "/" + "res/"));
        boolean savegamesDir = Files.isDirectory(Path.of("D:/Games" + "/" + "savegames/"));
        boolean tempDir = Files.isDirectory(Path.of("D:/Games" + "/" + "temp/"));
        boolean mainDir = Files.isDirectory(Path.of("D:/Games/src/main"));
        boolean testDir = Files.isDirectory(Path.of("D:/Games/src/test"));
        boolean drawablesDir = Files.isDirectory(Path.of("D:/Games/res/drawables"));
        boolean vectorsDir = Files.isDirectory(Path.of("D:/Games/res/vectors"));
        boolean iconsDir = Files.isDirectory(Path.of("D:/Games/res/icons"));

        // then:
        assertTrue(resDir, "Каталог 'res' отсутсвует");
        assertTrue(srcDir, "Каталог 'src' отсутсвует");
        assertTrue(savegamesDir, "Каталог 'savegames' отсутсвует");
        assertTrue(tempDir, "Каталог 'temp' отсутсвует");
        assertTrue(mainDir, "Каталог 'mainDir' отсутсвует");
        assertTrue(testDir, "Каталог 'testDir' отсутсвует");
        assertTrue(drawablesDir, "Каталог 'drawablesDir' отсутсвует");
        assertTrue(vectorsDir, "Каталог 'vectorsDir' отсутсвует");
        assertTrue(iconsDir, "Каталог 'iconsDir' отсутсвует");
    }

    @Test
    @Order(2)
    void logSaveTest() {
        // given:
        String logText = "\tТест записи log файла\n ";
        Path path = Path.of("D:/Games/temp/temp.txt");
        // when:
        Main.logSave(logText);
        boolean result = Files.isRegularFile(path);
        // then:
        // Проверили наличие файла
        assertTrue(result, " Файл отсутствует ");
    }

    @Test
    @Order(3)
    void changeLogTest() throws IOException {
        // given:
        String logText = "\tТест изменения log файла\n ";
        Path path = Path.of("D:/Games/temp/temp.txt");
        long result = Files.size(path);
        // when:
        Main.logSave(logText);
        // then:
        long resultAfter = Files.size(path);
        // Проверили изменение размера файла
        assertTrue((result != resultAfter), " Размер файла не изменился ");

    }
}


// ---------------------
// ---------------------
// ---------------------
// ---------------------