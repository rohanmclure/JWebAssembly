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
import de.inetsoftware.jwebassembly.wasm.NamedStorageType;
import de.inetsoftware.jwebassembly.wasm.ValueType;
import org.omg.CORBA.Any;

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
     * @param fieldName
     *            the name of field if needed for the operation
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
//        if( type != null && fieldName != null ) {
//            // The fieldName of the struct operation does not contain the class name in which the field was declared. It contains the class name of the variable. This can be the class or a subclass.
//            List<NamedStorageType> fields = type.getFields();
//            boolean classNameMatched = type.getName().equals( fieldName.geClassName() );
//            for( int i = fields.size()-1; i >= 0; i-- ) {
//                NamedStorageType field = fields.get( i );
//                if( !classNameMatched && field.geClassName().equals( fieldName.geClassName() ) ) {
//                    classNameMatched = true;
//                }
//                if( classNameMatched && field.getName().equals( fieldName.getName() ) ) {
//                    idx = i;
//                    break;
//                }
//            }
//            if( !classNameMatched ) {
//                // special case, the type self does not add a needed field, that we search in all fields
//                for( int i = fields.size()-1; i >= 0; i-- ) {
//                    NamedStorageType field = fields.get( i );
//                    if( field.getName().equals( fieldName.getName() ) ) {
//                        idx = i;
//                        break;
//                    }
//                }
//            }
//        }
        if (functionName != null) {
            writer.writeFunctionCall( functionName, null );
        }
//        if( functionName != null ) { // nonGC
//            if( fieldName != null ) {
//                writer.writeConst( idx, ValueType.i32 );
//            }
//            writer.writeFunctionCall( functionName, null );
//        } else {
//            writer.writeStructOperator( op, type, fieldName, idx );
//        }
    }

    @Override
    AnyType getPushValueType() {
        switch ( op ) {
            case NEW:
            case NEWARRAY:
                return type;
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
//                return sig.params().size() + 1;
            case INVOKESTATIC:
                return sig.params().size() + (needThisParam ? 1 : 0);
            case SYSCALL: // todo need to think of a better way to do this :(
                return 1;
            default:
                throw new WasmException( "Unknown pop count right now", -1);
        }
    }

}
