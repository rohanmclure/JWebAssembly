/*
   Copyright 2018 - 2020 Volker Berlin (i-net software)

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
import de.inetsoftware.jwebassembly.jawa.JawaOpcodes;
import de.inetsoftware.jwebassembly.jawa.JawaSyntheticFunctionName;
import de.inetsoftware.jwebassembly.jawa.StringWriter;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.ValueType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

import static de.inetsoftware.jwebassembly.wasm.NumericOperator.eqz;

/**
 * WasmInstruction for an array operation.
 * 
 * @author Volker Berlin
 *
 */
class WasmJawaCompareInstruction extends WasmInstruction {

    public JawaOpcodes.JawaFuncOpcode   op;

    public boolean eq;

    private final AnyType         type;

    private final TypeManager     types;

    private SyntheticFunctionName functionName;



    /**
     * Create an instance of an array operation.
     *
     * @param op
     *            the array operation
     * @param type
     *            the type of the parameters
     * @param types
     *            the type manager
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     *            the line number in the Java source code
     */
    WasmJawaCompareInstruction(JawaOpcodes.JawaFuncOpcode op, boolean eq, @Nullable AnyType type, TypeManager types, int javaCodePos, int lineNumber ) {
        super( javaCodePos, lineNumber );
        this.op = op;
        this.eq = eq;
        this.type = type;
        this.types = types;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Type getType() {
        return Type.CallJawa;
    }

    SyntheticFunctionName createJawaFunction() {
        String moduleName = "jawa";
        StringWriter funcName = new StringWriter();
        try {
            funcName.write(op.opcode);
            switch ( op ) {
                case ACMPEQ:
                    functionName = new JawaSyntheticFunctionName(null, moduleName, funcName.toString(), false, null, types.valueOf("java/lang/Object"), types.valueOf("java/lang/Object"), null, ValueType.bool);
                    return functionName;
                case FCMPG:
                case FCMPL:
                case FREM:
                case DCMPG:
                case DCMPL:
                case DREM:
                default:
                    throw new WasmException("Invalid jawa opcode " + op, -1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return functionName;
    }

    /**
     * {@inheritDoc}
     */
    public void writeTo( @Nonnull ModuleWriter writer ) throws IOException {
        writer.writeFunctionCall( functionName, null );
        if (!eq) {
            writer.writeNumericOperator(eqz, ValueType.i32);
        }
    }

    /**
     * {@inheritDoc}
     */
    AnyType getPushValueType() {
        switch ( op ) {
            case ACMPEQ:
                return ValueType.i32;
            case FCMPG:
            case FCMPL:
            case FREM:
            case DCMPG:
            case DCMPL:
            case DREM:
            default:
                throw new WasmException("Invalid jawa opcode for push value type " + op, -1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getPopCount() {
        switch ( op ) {
            case ACMPEQ:
            case FCMPG:
            case FCMPL:
            case FREM:
            case DCMPG:
            case DCMPL:
            case DREM:
                return 2;
            default:
                throw new WasmException("Invalid jawa opcode for pop value type " + op, -1);
        }
    }
}
