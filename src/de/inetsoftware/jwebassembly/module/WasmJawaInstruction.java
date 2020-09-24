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
import de.inetsoftware.jwebassembly.module.TypeManager.StructType;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.NamedStorageType;
import de.inetsoftware.jwebassembly.wasm.ValueType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * WasmInstruction for JAWA operations.
 *
 * @author Brenda Wang
 *
 */
class WasmJawaInstruction extends WasmInstruction {

    private final JawaOpcodes.JawaFuncOpcode op;

    private final StructType       type;

    private final NamedStorageType fieldName; // do we need this ?

    private SyntheticFunctionName functionName; // this should be a JAWA function name

    private final WasmOptions     options;

    /**
     * Create an instance of numeric operation.
     *
     * @param op
     *            the struct operation
     * @param typeName
     *            the type name of the parameters
     * @param fieldName
     *            the name of field if needed for the operation
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     *            the line number in the Java source code
     * @param types
     *            the type manager
     */
    WasmJawaInstruction(@Nullable JawaOpcodes.JawaFuncOpcode op, @Nullable String typeName, @Nullable NamedStorageType fieldName, int javaCodePos, int lineNumber, TypeManager types ) {
        super( javaCodePos, lineNumber );
        this.op = op;
        this.type = typeName == null ? null : types.valueOf( typeName );
        this.fieldName = fieldName;
        if( type != null && fieldName != null ) {
            type.useFieldName( fieldName );
        }
        this.options = types.options;
    }

    SyntheticFunctionName createJawaFunction() {
        StringWriter fname = new StringWriter();
        try {
            fname.write(op.opcode);
//            System.out.println("Generating " + op + " " + type);
            switch (op) {
                case NEW:
                    fname.writeSig("NEW" + type.name);
                    functionName = new JawaSyntheticFunctionName(type,"jawa", fname.toString(), type, null, type);
                    break;
                case INSTANCEOF:
                    functionName = new JawaSyntheticFunctionName(type,"jawa", fname.toString(), type, fieldName.getType(), null, ValueType.bool);
                    break;
                case GETFIELD:
                    fname.writeName(fieldName.getName());
                    functionName = new JawaSyntheticFunctionName(type, "jawa", fname.toString(), type, type, null, fieldName.getType());
                    break;
                case GETSTATIC:
                    fname.writeName(fieldName.getName());
                    functionName = new JawaSyntheticFunctionName(type, "jawa", fname.toString(), type, null, fieldName.getType());
                    break;
                case PUTFIELD:
                    fname.writeName(fieldName.getName());
                    functionName = new JawaSyntheticFunctionName(type, "jawa", fname.toString(), type, type, fieldName.getType(), null);
                    break;
                case PUTSTATIC:
                    fname.writeName(fieldName.getName());
                    functionName = new JawaSyntheticFunctionName(type, "jawa", fname.toString(), type, fieldName.getType(), null);
                    break;
                case CHECKCAST:
//                    functionName = new JawaSyntheticFunctionName(type, "jawa", fname.toString(), type, );
                default:
                    throw new WasmException("Cannot deal with " + op + " currently", -1);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return functionName;
    }

    /**
     * Get the StructOperator
     *
     * @return the operator
     */
    JawaOpcodes.JawaFuncOpcode getOperator() {
        return op;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Type getType() {
        return Type.CallJawa;
    }

    /**
     * {@inheritDoc}
     */
    public void writeTo( @Nonnull ModuleWriter writer ) throws IOException {
        writer.writeFunctionCall( functionName, null );
    }

    @Override
    AnyType getPushValueType() {
        switch ( op ) {
            case NEW:
                return type;
            case INSTANCEOF:
                return ValueType.bool;
            case GETFIELD:
            case GETSTATIC:
                return fieldName.getType();
            case PUTFIELD:
            case PUTSTATIC:
                return null;
            case CHECKCAST:
            default:
                throw new WasmException("Unknown push value right now :( " + op, -1);
        }
    }

    @Override
    int getPopCount() {
        switch ( op ) {
            case NEW:
            case GETSTATIC:
                return 0;
            case INSTANCEOF:
            case GETFIELD:
            case PUTSTATIC:
                return 1;
            case PUTFIELD:
                return 2;
            case CHECKCAST:
            default:
                throw new WasmException( "Unknown pop count right now for " + op, -1);
        }
    }

}
