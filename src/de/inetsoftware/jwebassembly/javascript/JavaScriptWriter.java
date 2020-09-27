/*
   Copyright 2019 Volker Berlin (i-net software)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
package de.inetsoftware.jwebassembly.javascript;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import de.inetsoftware.jwebassembly.module.WasmTarget;

/**
 * Write JavaScipt glue code.
 * 
 * @author Volker Berlin
 */
public class JavaScriptWriter {

    private WasmTarget                       target;

    /** annotation attribute for the JavaScript content */
    static final String                      JAVA_SCRIPT_CONTENT = "js";

    private Map<String, Map<String, Function<String, Object>>> modules             = new HashMap<>();

    /**
     * Create a new instance
     * 
     * @param target
     *            the target for the module data.
     */
    public JavaScriptWriter( WasmTarget target ) {
        this.target = target;
    }

    /**
     * Add an import from a needed function with import annotation.
     * 
     * @param module
     *            the module name
     * @param name
     *            the function name
     * @param annotationValues
     *            the other values of the annotation
     */
    public void addImport( String module, String name, Function<String, Object> annotationValues ) {
        Map<String, Function<String, Object>> moduleEntries = modules.get( module );
        if( moduleEntries == null ) {
            modules.put( module, moduleEntries = new HashMap<>() );
        }

        Function<String, Object> old = moduleEntries.put( name, annotationValues );
        if( old != null && !module.equals("jawa")) {
            System.err.println( "Redefine JavaScript function: " + module + "." + name );
        }
    }

    /**
     * Finish the accumulate of imports and write the JavaScript file.
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    public void finish() throws IOException {
        Appendable out = target.getJavaScriptOutput();
        if( out == null ) {
            return;
        }
        finish( out );
    }

    /**
     * Finish the accumulate of imports and write the JavaScript to the Appendable.
     * 
     * @param out
     *            the target for the script
     * @throws IOException
     *             if any I/O error occur
     */
    void finish( Appendable out ) throws IOException {
        out.append( "'use strict';var wasmImports = {\n" );
        boolean isFirst = true;
        for( Entry<String, Map<String, Function<String, Object>>> module : modules.entrySet() ) {
            if( !isFirst ) {
                out.append( ",\n" );
            }
            out.append( module.getKey() ).append( ':' );
            Map<String, Function<String, Object>> functions = module.getValue();

            boolean hasContent = false;
            for( Function<String, Object> annotationValues : functions.values() ) {
                String content = (String)annotationValues.apply( JAVA_SCRIPT_CONTENT );
                if( content != null && !content.isEmpty() ) {
                    hasContent = true;
                    break;
                }
            }

            if( hasContent ) {
                out.append( "{\n" );

                // write the function
                boolean isFirstFunc = true;
                for( Entry<String, Function<String, Object>> func : functions.entrySet() ) {
                    if( !isFirstFunc ) {
                        out.append( ",\n" );
                    }
                    String content = (String)func.getValue().apply( JAVA_SCRIPT_CONTENT );
                    out.append( func.getKey() ).append( ':' ).append( content );
                    isFirstFunc = false;
                }

                out.append( "\n}" );
            } else {
                out.append( module.getKey() );
            }
            isFirst = false;
        }
        out.append( "\n};\nif (typeof module !== 'undefined') module.exports = wasmImports;" );
    }
}
