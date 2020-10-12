/*
 * Copyright 2017 - 2020 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.module;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import de.inetsoftware.jwebassembly.binary.ImportArguments;
import de.inetsoftware.jwebassembly.module.TypeManager.StructType;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.ArrayOperator;
import de.inetsoftware.jwebassembly.wasm.ArrayType;
import de.inetsoftware.jwebassembly.wasm.FunctionType;
import de.inetsoftware.jwebassembly.wasm.MemoryOperator;
import de.inetsoftware.jwebassembly.wasm.NamedStorageType;
import de.inetsoftware.jwebassembly.wasm.NumericOperator;
import de.inetsoftware.jwebassembly.wasm.StructOperator;
import de.inetsoftware.jwebassembly.wasm.ValueType;
import de.inetsoftware.jwebassembly.wasm.VariableOperator;
import de.inetsoftware.jwebassembly.wasm.WasmBlockOperator;

/**
 * Module Writer base class.
 * 
 * @author Volker Berlin
 */
public abstract class ModuleWriter implements Closeable {

    /**
     * The compiler options.
     */
    protected final WasmOptions           options;

    /**
     * The stream of the data section for constant data like strings and vtables
     */
    protected final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

    /**
     * Create a instance with its options.
     * 
     * @param options
     *            the compiler options
     */
    protected ModuleWriter( WasmOptions options ) {
        this.options = options;
    }

    /**
     *
     */
    protected abstract Function getFunction(FunctionName name );

        /**
         * Finish the prepare after all classes/methods are prepare. This must be call before we can start with write the
         * first method.
         */
    protected abstract void prepareFinish();

    /**
     * Write a type/struct.
     * 
     * @param type
     *            the type to declare/write
     * @return type ID
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract int writeStructType( StructType type ) throws IOException;

    /**
     * Mark to write exceptions
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeException() throws IOException;

    /**
     * Prepare a imported single function in the prepare phase.
     * 
     * @param name
     *            the function name
     * @param importModule
     *            the import module name if it is a import function
     * @param importName
     *            the import name if it is a import function
     * @param delayed
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void prepareImport( FunctionName name, String importModule, String importName, boolean delayed, AnyType args, AnyType second_arg ) throws IOException;

    public abstract void importType(String importModule, String importName, StructType type, StructType parent, AnyType... args) throws IOException;

    public abstract void importCommand(String importModule, String importName, List<ImportArguments> args) throws IOException;

        /**
         * Write an export directive
         * @param name
         *            the function name
         * @param exportName
         *            the export name, if null then the same like the method name
         *
         * @throws IOException
         *             if any I/O error occur
         */
    protected abstract void writeExport( FunctionName name, String exportName ) throws IOException;

    /**
     * Write the method header.
     * 
     * @param name
     *            the function name
     * @param funcType
     *            the type of function
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMethodParamStart( @Nonnull FunctionName name, @Nonnull FunctionType funcType ) throws IOException;

    /**
     * Write a method parameter.
     * 
     * @param kind
     *            "param", "result" or "local"
     * @param valueType
     *            the data type of the parameter
     * @param name
     *            optional name of the parameter
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMethodParam( String kind, AnyType valueType, @Nullable String name ) throws IOException;

    /**
     * Finish the function parameter.
     * 
     * @param name
     *            the function name
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMethodParamFinish( @Nonnull FunctionName name ) throws IOException;

    /**
     * Start the writing of method/function code.
     * 
     * @param name
     *            the function name
     * @param sourceFile
     *            the name of the source file
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMethodStart( FunctionName name, String sourceFile ) throws IOException;

    /**
     * Mark the current output position with Java code position for crating of a source map.
     * 
     * @param javaSourceLine
     *            the line number in the Java code
     */
    protected abstract void markSourceLine( int javaSourceLine );

    /**
     * Complete the method
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMethodFinish( ) throws IOException;

    /**
     * Write a constant number value
     * 
     * @param value
     *            the value
     * @param valueType
     *            the data type of the number
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeConst( Number value, ValueType valueType ) throws IOException;

    /**
     * Write a local variable operation.
     * 
     * @param op
     *            the operation
     * @param idx
     *            the index of the parameter variable
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeLocal( VariableOperator op, int idx ) throws IOException;

    /**
     * Write a global variable operation
     * @param load
     *            true: if load or GET
     * @param name
     *            the variable name
     * @param type
     *            the type of the variable
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeGlobalAccess( boolean load, FunctionName name, AnyType type ) throws IOException;

    /**
     * Write a table operation.
     * @param load
     *            true: if "get" else "set"
     * @param idx
     *            the index of the table
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeTable( boolean load, @Nonnegative int idx ) throws IOException;

    /**
     * Write the default/initial value for a type.
     * 
     * @param type
     *            the type
     * @throws IOException
     *             if an I/O error occurs.
     */
    protected abstract void writeDefaultValue( AnyType type ) throws IOException;

    /**
     * Write a add operator
     * 
     * @param numOp
     *            the numeric operation
     * @param valueType
     *            the type of the parameters
     * 
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeNumericOperator( NumericOperator numOp, @Nullable ValueType valueType ) throws IOException;

    /**
     * Cast a value from one type to another
     * 
     * @param cast
     *            the operator
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeCast( ValueTypeConvertion cast ) throws IOException;

    /**
     * Write a call to a function.
     * 
     * @param name
     *            the function name
     * @param comment
     *            optional comment for the text format
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeFunctionCall( FunctionName name, String comment ) throws IOException;

    /**
     * Write a function call to an instance function. On the stack there must be the object.
     * 
     * @param name
     *            the function name
     * @param type
     *            the base type that should be called
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeVirtualFunctionCall( FunctionName name, AnyType type ) throws IOException;

    /**
     * Write a block/branch code
     * 
     * @param op
     *            the operation
     * @param data
     *            extra data depending of the operator
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeBlockCode( @Nonnull WasmBlockOperator op, @Nullable Object data ) throws IOException;

    /**
     * Write an array operation.
     * 
     * @param op
     *            the operation
     * @param type
     *            the type of the array
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeArrayOperator( @Nonnull ArrayOperator op, ArrayType type ) throws IOException;

    /**
     * Write a struct operation
     * 
     * @param op
     *            the operation
     * @param type
     *            the type of the struct
     * @param fieldName
     *            the fieldName if the operation is per field
     * @param idx
     *            the index of the field if the operation is per field
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeStructOperator( StructOperator op, AnyType type, NamedStorageType fieldName, int idx ) throws IOException;

    /**
     * Write a memory operation for the linear memory.
     * 
     * @param memOp
     *            the memory operation
     * @param valueType
     *            the value type of the stack value
     * @param offset
     *            the offset into the memory. Should be ideally a factor of 4.
     * @param alignment
     *            the alignment of the value on the linear memory (0: 8 Bit; 1: 16 Bit; 2: 32 Bit)
     * @throws IOException
     *             if any I/O error occur
     */
    protected abstract void writeMemoryOperator( MemoryOperator memOp, ValueType valueType, int offset, int alignment ) throws IOException;
}
