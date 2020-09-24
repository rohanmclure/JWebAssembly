/*
 * Copyright 2018 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.binary;

import de.inetsoftware.jwebassembly.jawa.JawaOpcodes;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.ValueType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * An entry in the import section of the WebAssembly.
 * 
 * @author Volker Berlin
 */
class ImportGlobal extends Function {

    final String module;

    final String name;

    final AnyType type;

    final AnyType args;

    ImportGlobal(String module, String name, AnyType type, AnyType arg) {
        this.module = module;
        this.name = name;
        this.type = type;
        this.args = arg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void writeSectionEntry( WasmOutputStream stream ) throws IOException {
        byte[] bytes = this.module.getBytes( StandardCharsets.UTF_8 );
        stream.writeVaruint32( bytes.length );
        stream.write( bytes );
        bytes = this.name.getBytes( StandardCharsets.UTF_8 );
        stream.writeVaruint32( bytes.length );
        stream.write( bytes );
        stream.writeVaruint32( ExternalKind.Global.ordinal() );

        stream.writeValueType(ValueType.abstractref); // value type is abstract
        stream.writeValueType(new AnyType() {
            @Override
            public int getCode() {
                return type.getJawaCode();
            }

            @Override
            public JawaOpcodes.JawaTypeOpcode getTypeOpcode() {
                return null;
            }

            @Override
            public int getJawaCode() {
                return 0;
            }

            @Override
            public boolean isRefType() {
                return false;
            }

            @Override
            public boolean isSubTypeOf(AnyType type) {
                return false;
            }

            @Override
            public boolean useRefType() {
                return false;
            }
        }); // index
        stream.write(new byte[]{(byte) 0}); // const mutability

    }

}
