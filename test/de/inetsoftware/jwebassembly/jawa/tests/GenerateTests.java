package de.inetsoftware.jwebassembly.jawa.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class GenerateTests {

    final static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    final static String SEPARATOR = isWindows ? "\\" : "/";
    static final String JAWA_TEST_ROOT = "test" + SEPARATOR + "de" + SEPARATOR + "inetsoftware" + SEPARATOR + "jwebassembly" + SEPARATOR + "jawa" + SEPARATOR + "tests";
    static final String TEMPLATE_FILE = JAWA_TEST_ROOT + "" + SEPARATOR + "Empty.java";
    static final String WRITE_TO = JAWA_TEST_ROOT + "" + SEPARATOR + "RunJawaTests.java";
    static final String TEST_DIR = JAWA_TEST_ROOT + "" + SEPARATOR + "unit";
    static final String TEST_TEMPLATE = "    @Test\n    public void REPLACE() { run( REPLACE.class ); }\n\n";

    public static void main(String[] args) {
        String filename = TEMPLATE_FILE;
        Path pathToFile = Paths.get(filename);
        String fileContent = null;
        try {
            fileContent = new String(Files.readAllBytes(pathToFile.toAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Could not load template file");
            e.printStackTrace();
        }

        fileContent = fileContent.replace("Empty", "RunJawaTests");

        StringBuilder testReplacement = new StringBuilder();
        File testFolder = new File(TEST_DIR);
        for (File testFile : testFolder.listFiles()) {
            String testName = testFile.getName().substring(0, testFile.getName().lastIndexOf('.'));
            testReplacement.append(TEST_TEMPLATE.replace("REPLACE", testName));
        }

        fileContent = fileContent.replace("/** REPLACE **/", testReplacement.toString());

        try {
            FileWriter writeTo = new FileWriter(WRITE_TO);
            writeTo.write("/* This file is generated by GenerateTests.java. Please do not modify. */\n");
            writeTo.write(fileContent);
            writeTo.close();
        } catch (IOException e) {
            System.err.println("Could not create file to write to");
            e.printStackTrace();
        }
    }

}
