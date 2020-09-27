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
package de.inetsoftware.jwebassembly.jawa;

import de.inetsoftware.jwebassembly.javascript.JavaScriptWriter;
import de.inetsoftware.jwebassembly.module.ArraySyntheticFunctionName;
import de.inetsoftware.jwebassembly.module.TypeManager;
import de.inetsoftware.jwebassembly.wasm.AnyType;

import java.sql.Struct;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Synthetic JavaScript import function.
 * 
 * @author Volker Berlin
 *
 */
public class JawaSyntheticFunctionName extends ArraySyntheticFunctionName {

    public AnyType arg;

    /**
     * Create a synthetic function which based on imported, dynamically generated WASM from JAWA
     *
     * @param module
     *            the module name
     * @param functionName
     *            the name of the function
     * @param signature
     *            the types of the signature
     */
    public JawaSyntheticFunctionName( AnyType arg, String module, String functionName, TypeManager.StructType type, AnyType... signature ) {
        super( module, functionName, signature );

        StringBuilder sigName = new StringBuilder("(");
        for (AnyType t : signature) {
            if (t == null) {
                sigName.append(')');
                continue;
            }
            sigName.append(t.toString());
        }
        if (arg != null) {
            sigName.append('[');
            sigName.append(arg.toString());
            sigName.append(']');
        }
        this.signature = sigName.toString();
        this.signatureName = this.fullName + this.signature;
        this.arg = arg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasWasmCode() {
        return false;
    }

    @Override
    protected Function<String, Object> getAnnotation() {
        return ( key ) -> { return null; };
    }
}
