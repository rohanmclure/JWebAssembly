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
package de.inetsoftware.jwebassembly.jawa.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static de.inetsoftware.jwebassembly.jawa.tests.TestRunner.run;
import de.inetsoftware.jwebassembly.jawa.tests.unit.*;

import java.io.File;

public class Empty {

    final static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    final static String SEPARATOR = isWindows ? "\\" : "/";

    @BeforeClass
    public static void setup() {
        File directory = new File("build" + SEPARATOR + "code");
        if (! directory.exists()){
            directory.mkdirs();
        }
    }

/** REPLACE **/

}
