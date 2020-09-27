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
import de.inetsoftware.jwebassembly.javascript.JavaScriptSyntheticFunctionName;
import de.inetsoftware.jwebassembly.jawa.JawaOpcodes;
import de.inetsoftware.jwebassembly.jawa.JawaSyntheticFunctionName;
import de.inetsoftware.jwebassembly.jawa.StringWriter;
import de.inetsoftware.jwebassembly.wasm.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

/**
 * WasmInstruction for an array operation.
 * 
 * @author Volker Berlin
 *
 */
class WasmJawaArrayInstruction extends WasmInstruction {

    private final JawaOpcodes.JawaFuncOpcode   op;

    private final AnyType         type;

//    private final ArrayType       arrayType;

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
     *            the type managerdustbunny
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     *            the line number in the Java source code
     */
    WasmJawaArrayInstruction(JawaOpcodes.JawaFuncOpcode op, @Nullable AnyType type, TypeManager types, int javaCodePos, int lineNumber ) {
        super( javaCodePos, lineNumber );
        this.op = op;
        this.type = type;
        this.types = types;
//        this.arrayType = types.arrayType( type );
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
//            System.out.println("Generating " + op.name() + " with type " + type);
            switch ( op ) {
                case BALOAD:
                case CALOAD:
                case DALOAD:
                case IALOAD:
                case LALOAD:
                case SALOAD:
                case FALOAD:
                    functionName = new JawaSyntheticFunctionName(null, moduleName, funcName.toString(), null, null, type);
                    return functionName;
                case BASTORE:
                case CASTORE:
                case DASTORE:
                case IASTORE:
                case LASTORE:
                case SASTORE:
                case FASTORE:
                    functionName = new JawaSyntheticFunctionName(null, moduleName, funcName.toString(), null, type, null);
                    return functionName;
                case AALOAD:
                    functionName = new JawaSyntheticFunctionName(type, moduleName, funcName.toString(), null, type, ValueType.i32, null, ((ArrayType) type).getArrayType());
                    return functionName;
                case AASTORE:
                    functionName = new JawaSyntheticFunctionName(type, moduleName, funcName.toString(), null,types.arrayType(type), ValueType.i32, type, null);
                    return functionName;
                case NEWARRAY:
                    switch ((ValueType) type) {
                        case i32:
                            break;
                        case i64:
                            break;
                        case f32:
                            break;
                        case f64:
                            break;
                        case v128:
                            break;
                        case bool:
                            break;
                        case i8:
                            break;
                        case i16:
                            break;
                        case u16:
                            break;
                        default:
                            throw new WasmException("Unsupported NEWARRAY type", -1);
                    }
                    functionName = new JawaSyntheticFunctionName(types.arrayType(type), moduleName, funcName.toString(), types.arrayType( type ), ValueType.i32, null, types.arrayType( type ));
                    return functionName;
                case ANEWARRAY:
                    functionName = new JawaSyntheticFunctionName(types.arrayType(type), moduleName, funcName.toString(), types.arrayType(type), ValueType.i32, null,types.arrayType(type));
                    return functionName;
                case ARRAYLENGTH:
                    functionName = new JawaSyntheticFunctionName(types.arrayType(type), moduleName, funcName.toString(), types.arrayType( type ), types.arrayType(type), null, ValueType.i32);
                    return functionName;
//                case MULTIANEWARRAY:
//
//                    functionName = new JawaSyntheticFunctionName(moduleName, funcName.toString(), null, null, null);
                default:
                    throw new WasmException("Invalid java opcode " + op, -1);
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
        if( functionName != null ) { // nonGC
            writer.writeFunctionCall( functionName, null );
        }
//        else {
//            writer.writeArrayOperator( op, arrayType );
//        }
    }

    /**
     * {@inheritDoc}
     */
    AnyType getPushValueType() {
        switch( op ) {
            case ANEWARRAY:
            case MULTIANEWARRAY:
            case NEWARRAY:
//                return arrayType;
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case DALOAD:
            case IALOAD:
            case LALOAD:
            case SALOAD:
            case FALOAD:
                return type;
            case AASTORE:
            case BASTORE:
            case CASTORE:
            case DASTORE:
            case IASTORE:
            case LASTORE:
            case SASTORE:
            case FASTORE:
                return null;
            case ARRAYLENGTH:
                return ValueType.i32;
            default:
                throw new WasmException( "Unknown jawa array operation: " + op, -1 );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getPopCount() {
        switch( op ) {
            case BALOAD:
            case CALOAD:
            case DALOAD:
            case IALOAD:
            case LALOAD:
            case SALOAD:
            case FALOAD:
                return 0;
            case ANEWARRAY:
            case NEWARRAY:
            case ARRAYLENGTH:
            case BASTORE:
            case CASTORE:
            case DASTORE:
            case IASTORE:
            case LASTORE:
            case SASTORE:
            case FASTORE:
                return 1;
            case AALOAD:
                return 2;
            case AASTORE:
                return 3;
            case MULTIANEWARRAY:
                throw new WasmException("Not yet supported; check the dimensions", -1);
            default:
                throw new WasmException( "Unknown jawa array operation: " + op, -1 );
        }
    }
}
