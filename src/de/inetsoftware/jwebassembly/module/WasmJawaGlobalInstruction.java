/*
   Copyright 2018 - 2019 Volker Berlin (i-net software)

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
package de.inetsoftware.jwebassembly.module;

import de.inetsoftware.jwebassembly.WasmException;
import de.inetsoftware.jwebassembly.jawa.JawaOpcodes.JawaFuncOpcode;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.ValueType;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * WasmInstruction for constant values.
 * 
 * @author Volker Berlin
 *
 */
class WasmJawaGlobalInstruction extends WasmInstruction {

    private final JawaFuncOpcode op;

    private final Number    value;

    private final ValueType valueType;

    /**
     * Create an instance of a constant instruction
     *
     * @param value
     *            the constant value
     * @param valueType
     *            the data type of the number
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     *            the line number in the Java source code
     */
    WasmJawaGlobalInstruction(JawaFuncOpcode op, Number value, ValueType valueType, int javaCodePos, int lineNumber ) {
        super( javaCodePos, lineNumber );
        this.op = op;
        this.value = value;
        this.valueType = valueType;
    }

    /**
     * Create an instance of a constant instruction
     *  @param value
     *            the constant value
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     * @param op
     */
    WasmJawaGlobalInstruction(Number value, int javaCodePos, int lineNumber, JawaFuncOpcode op) {
        this( op, value, getValueType( value ), javaCodePos, lineNumber );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Type getType() {
        return Type.Const;
    }

    /**
     * Find the matching ValueType for the given value.
     * 
     * @param value
     *            the constant value
     * @return the ValueType
     */
    @Nonnull
    private static ValueType getValueType( Number value ) {
        Class<?> clazz = value.getClass();
        throw new WasmException( "Not supported constant type: " + clazz, -1 );
    }

    /**
     * {@inheritDoc}
     */
    public void writeTo( @Nonnull ModuleWriter writer ) throws IOException {
        writer.writeConst( value, valueType );
    }

    /**
     * {@inheritDoc}
     */
    AnyType getPushValueType() {
        return valueType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getPopCount() {
        return 0;
    }
}
