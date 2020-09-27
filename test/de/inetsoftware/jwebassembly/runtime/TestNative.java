/*
 * Copyright 2017 - 2019 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.runtime;

import de.inetsoftware.jwebassembly.ScriptEngine;
import de.inetsoftware.jwebassembly.WasmRule;
import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.api.annotation.Import;
import de.inetsoftware.jwebassembly.api.annotation.WasmTextCode;
import org.junit.ClassRule;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Volker Berlin
 */
public class TestNative extends AbstractBaseTest {

    @ClassRule
    public static WasmRule rule = new WasmRule( TestClass.class );

    public TestNative(ScriptEngine script, String method, Object[] params ) {
        super( rule, script, method, params );
    }

    @Parameters(name="{0}-{1}")
    public static Collection<Object[]> data() {
        ArrayList<Object[]> list = new ArrayList<>();
        for( ScriptEngine script : ScriptEngine.testEngines() ) {
            addParam( list, script, "intCall" );
        }
        rule.setTestParameters( list );
        return list;
    }

    static class TestClass {

        @Export
        static int intCall() {
            Object a = new Object();
            return a.hashCode();
        }


    }
}
