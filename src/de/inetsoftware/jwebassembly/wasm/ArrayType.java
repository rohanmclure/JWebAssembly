/*
 * Copyright 2019 - 2020 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.wasm;

import de.inetsoftware.jwebassembly.JWebAssembly;
import de.inetsoftware.jwebassembly.WasmException;
import de.inetsoftware.jwebassembly.jawa.JawaOpcodes;
import de.inetsoftware.jwebassembly.jawa.StringWriter;
import de.inetsoftware.jwebassembly.module.ClassFileLoader;
import de.inetsoftware.jwebassembly.module.FunctionManager;
import de.inetsoftware.jwebassembly.module.ModuleWriter;
import de.inetsoftware.jwebassembly.module.TypeManager;
import de.inetsoftware.jwebassembly.module.TypeManager.StructType;

import java.io.IOException;

import static de.inetsoftware.jwebassembly.jawa.JawaOpcodes.JawaTypeOpcode.*;

/**
 * A reference to an array type
 * 
 * @author Volker Berlin
 *
 */
public class ArrayType extends StructType {

    private AnyType arrayType;

    private int componentClassIndex;

    /**
     * Create a new array type
     * 
     * @param arrayType
     *            the type of the array
     * @param classIndex
     *            the running index of the main class/type
     * @param componentClassIndex
     *            the running index of the component/array class/type
     */
    public ArrayType(TypeManager typeManager, AnyType arrayType, int classIndex, int componentClassIndex ) {
        super(typeManager, getJavaClassName( arrayType ), classIndex );
        this.arrayType = arrayType;
        this.componentClassIndex = componentClassIndex;
    }

    /**
     * Create class name for the array class.
     * 
     * @param arrayType
     *            the type of the array
     * @return the name
     */
    private static String getJavaClassName( AnyType arrayType ) {
        if( !arrayType.isRefType() ) {
            switch( (ValueType)arrayType ) {
                case bool:
                    return "[Z";
                case i8:
                    return "[B";
                case i16:
                    return "[S";
                case u16:
                    return "[C";
                case f64:
                    return "[D";
                case f32:
                    return "[F";
                case i32:
                    return "[I";
                case i64:
                    return "[J";
                case externref:
                    return "[Ljava.lang.Object;";
                default:
                    throw new WasmException( "Not supported array type: " + arrayType, -1 );
            }
        }
        if( arrayType instanceof ArrayType ) {
            return "[" + getJavaClassName( ((ArrayType)arrayType).arrayType );
        }
        return "[L" + ((StructType)arrayType).getName() + ";";
    }

    /**
     * The element type of the array
     * @return the type
     */
    public AnyType getArrayType() {
        return arrayType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getComponentClassIndex() {
        return componentClassIndex;
    }

    public JawaOpcodes.JawaTypeOpcode getTypeOpcode() {
        if (arrayType.getTypeOpcode() != null) return REF_ARRAY;
        switch ((ValueType) arrayType) {
            case i32:
                return INT_ARRAY;
            case i64:
                return LONG_ARRAY;
            case f32:
                return FLOAT_ARRAY;
            case f64:
                return DOUBLE_ARRAY;
            case v128:
                throw new WasmException("Cannot deal with v128 arrays", -1);
            case bool:
                return BOOL_ARRAY;
            case i8:
                return BYTE_ARRAY;
            case i16:
            case u16:
                return SHORT_ARRAY;
            default:
                throw new WasmException("Invalid type in array " + arrayType.toString() + " for " + this.toString() , -1);
        }
    }

    @Override
    protected void writeStructImportType(ModuleWriter writer, FunctionManager functions, TypeManager types, ClassFileLoader classFileLoader) throws IOException {
        JWebAssembly.LOGGER.fine( "import write type: " + name );
        if (arrayType.getTypeOpcode() != null) {
            StringWriter importName = new StringWriter();
            importName.write(getTypeOpcode().opcode);
            writer.importType("jawa", importName.toString(), this, (StructType) this.arrayType, this.arrayType);
        } else {
            StringWriter importName = new StringWriter();
            importName.write(getTypeOpcode().opcode);
            writer.importType("jawa", importName.toString(), this, null); //todo maybe parent is ??
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getCode() {
        // until there is a real type definition we will define write it as externref
        return ValueType.abstractref.getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRefType() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubTypeOf( AnyType type ) {
        return type == this || type == ValueType.externref;
    }
}
