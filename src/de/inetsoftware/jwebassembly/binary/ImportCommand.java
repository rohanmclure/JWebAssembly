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
import java.util.List;

/**
 * An entry in the import section of the WebAssembly.
 * 
 * @author Volker Berlin
 */
class ImportCommand extends Function {

    final String module;

    final String name;

    final List<ImportArguments> args;

    ImportCommand(String module, String name, List<ImportArguments> args) {
        this.module = module;
        this.name = name;
        this.args = args;
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
        if (args.size() > 0) {
            stream.writeVaruint32( ExternalKind.Args.ordinal() );
            stream.writeVaruint32( args.size() ); // args count
            for (ImportArguments arg : args) {
                arg.writeTo(stream);
            }
        }
        stream.writeVaruint32( ExternalKind.Command.ordinal() );
    }
}
