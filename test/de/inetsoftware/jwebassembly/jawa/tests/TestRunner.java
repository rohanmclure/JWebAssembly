package de.inetsoftware.jwebassembly.jawa.tests;

import de.inetsoftware.jwebassembly.JWebAssembly;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestRunner {

    private final static boolean DEBUG = true;
    private final static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    private final static String SEPARATOR = isWindows ? "\\" : "/";
    private final static String OUT_DIR = "build" + SEPARATOR + "code";
    private final static String JAWA_DIR = "java -jar .." + SEPARATOR + "wizard-engine" + SEPARATOR + "bin" + SEPARATOR + "jawa.jar";

    public static void run(Class<?> clazz) {
        // Setup compiler
        JWebAssembly compiler = new JWebAssembly();
        URL url = clazz.getResource( '/' + clazz.getName().replace( '.', '/' ) + ".class" );
        compiler.addFile(url);
        final String[] libraries = System.getProperty("java.class.path").split(File.pathSeparator);
        for( String lib : libraries ) {
            if( lib.endsWith( ".jar" ) || lib.toLowerCase().contains( "jwebassembly-api" ) ) {
                compiler.addLibrary( new File(lib) );
            }
        }

        // Output WAT / WASM
        if (DEBUG) {
            File text = new File(OUT_DIR, clazz.getSimpleName() + ".wat");
            compiler.compileToText(text);
        }
        File binary = new File(OUT_DIR, clazz.getSimpleName() + ".wasm");
        compiler.compileToBinary(binary);

        // Get all unit tests to run
        if (clazz.getAnnotation(RunTests.class) != null) {
            for (RunTest test : clazz.getAnnotation(RunTests.class).value()) {
                RunTestCase(test, binary);
            }
        } else {
            RunTest test = clazz.getAnnotation(RunTest.class);
            if (test == null) Assert.fail("No test cases defined for class");
            RunTestCase(test, binary);
        }
    }

    static void RunTestCase(RunTest test, File binary) {
        try {
            String input = test.input();
            if (test.intInput() >= 0) {
                StringBuilder intInputBuilder = new StringBuilder();
                for (int i = 1; i < test.intInput(); i++) {
                    intInputBuilder.append(i);
                    intInputBuilder.append(' ');
                }
                if (test.intInput() != 0) intInputBuilder.append(test.intInput());
                input = intInputBuilder.toString();
            }

            Process proc = Runtime.getRuntime().exec(JAWA_DIR + " " + binary.getCanonicalPath() + " " + input);
            proc.waitFor();
            InputStream in = proc.getInputStream();
            byte b[] = new byte[in.available()];
            in.read(b, 0, b.length);
            String output = new String(b);
            InputStream err = proc.getInputStream();
            byte berr[] = new byte[err.available()];
            in.read(berr, 0, berr.length);
            String error = new String(berr);
            System.err.println(error);
            if (test.exception().length() != 0) {
                Assert.assertTrue("Ran input \"" + input + "\". Output \"" +  output + "\" did not contain exception " + test.exception(),
                        output.contains(test.exception()));
            } else {
                Assert.assertEquals("Ran input \"" + input + "\"", test.output(), output);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
