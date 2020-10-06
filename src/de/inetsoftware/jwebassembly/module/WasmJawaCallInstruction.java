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
import de.inetsoftware.jwebassembly.jawa.JawaSignature;
import de.inetsoftware.jwebassembly.jawa.JawaSyntheticFunctionName;
import de.inetsoftware.jwebassembly.jawa.StringWriter;
import de.inetsoftware.jwebassembly.module.TypeManager.StructType;
import de.inetsoftware.jwebassembly.wasm.AnyType;

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
class WasmJawaCallInstruction extends WasmInstruction {

    private final JawaOpcodes.JawaFuncOpcode op;

    private final StructType       type;

    private FunctionName fName;

    private SyntheticFunctionName functionName; // this should be a JAWA function name

    private boolean needThisParam;

    private final WasmOptions     options;

    private final JawaSignature sig;

    /**
     * Create an instance of numeric operation.
     *
     * @param op
     *            the struct operation
     * @param typeName
     *            the type name of the parameters
     * @param javaCodePos
     *            the code position/offset in the Java method
     * @param lineNumber
     *            the line number in the Java source code
     * @param types
     *            the type manager
     */
    WasmJawaCallInstruction(@Nullable JawaOpcodes.JawaFuncOpcode op, @Nullable String typeName, @Nullable FunctionName fName, boolean needThisParam, int javaCodePos, int lineNumber, TypeManager types ) {
        super( javaCodePos, lineNumber );
        this.op = op;
        this.type = typeName == null ? null : types.valueOf( typeName );
        this.fName = fName;
        this.needThisParam = needThisParam;
        this.options = types.options;
        sig = new JawaSignature(fName.signature, options.types);
    }

    SyntheticFunctionName createJawaFunction() {
        try {
            StringWriter fname = new StringWriter();
            fname.write(op.opcode);
            fname.writeName(fName.methodName);
            fname.writeSig(fName.className);
            switch ( op ) {
                case INVOKESPECIAL:
                case INVOKEVIRTUAL:
//                    List<AnyType> sl = sig.convertToList();
//                    sl.add(0, type);
//                    functionName = new JawaSyntheticFunctionName(type,"jawa", fname.toString(), type, sl.toArray(new AnyType[0]));
//                    break;
                case INVOKESTATIC:
                    List<AnyType> sl = sig.convertToList();
//                    if (needThisParam)
                    sl.add(0, type);
//                    System.out.println("INVOKEVIRTUAL " + op + " " + fName.fullName + " " + needThisParam);
                    functionName = new JawaSyntheticFunctionName(type,"jawa", fname.toString(), type, sl.toArray(new AnyType[0]));
                    break;
                case INVOKEINTERFACE:
                case INVOKEDYNAMIC:
                default:
                    throw new WasmException( "Unknown pop count right now", -1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        if (functionName != null) {
            writer.writeFunctionCall( functionName, null );
        }

    }

    @Override
    AnyType getPushValueType() {
        switch ( op ) {
            case INVOKEVIRTUAL:
            case INVOKESTATIC:
            case INVOKESPECIAL:
                return sig.result();
            case SYSCALL:
            default:
                throw new WasmException("Unknown push value right now :(", -1);
        }
    }

    @Override
    int getPopCount() {
        switch ( op ) {
            case INVOKEVIRTUAL:
            case INVOKESPECIAL:
            case INVOKESTATIC:
                return sig.params().size() + (needThisParam ? 1 : 0);
            case SYSCALL: // todo need to think of a better way to do this :(
                return 1;
            default:
                throw new WasmException( "Unknown pop count right now", -1);
        }
    }

}
