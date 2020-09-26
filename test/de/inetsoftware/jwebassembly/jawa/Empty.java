/*
 * Copyright 2017 - 2020 Volker Berlin (i-net software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.inetsoftware.jwebassembly.jawa;

import de.inetsoftware.jwebassembly.JWebAssembly;
import de.inetsoftware.jwebassembly.jawa.unit.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Empty {

    private final static boolean DEBUG = true;
    private final static String OUT_DIR = "build\\code";
    private final static String JAWA_DIR = "java -jar ..\\wizard-engine\\bin\\jawa.jar";

/** REPLACE **/
    public void run(Class<?> clazz) {
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

    void RunTestCase(RunTest test, File binary) {
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
            Assert.assertEquals("Ran input \"" + input + "\"", test.output(), output);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
