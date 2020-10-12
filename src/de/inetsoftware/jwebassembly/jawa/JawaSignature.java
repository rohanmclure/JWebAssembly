package de.inetsoftware.jwebassembly.jawa;

import de.inetsoftware.jwebassembly.WasmException;
import de.inetsoftware.jwebassembly.module.TypeManager;
import de.inetsoftware.jwebassembly.wasm.AnyType;
import de.inetsoftware.jwebassembly.wasm.ValueType;
import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class JawaSignature {

    private final String sig;
    private int idx;
    private final TypeManager types;

    private String jawaSig;
    private List<AnyType> jawaTypes = new ArrayList<>();

    public JawaSignature(String javaSig, TypeManager types) {
        this.sig = javaSig;
        this.types = types;
        if (sig.startsWith("(")) idx++;
    }

    public String getJawaSig() {
        if (jawaSig == null) processJawa();
        return jawaSig;
    }

    public List<AnyType> getJawaTypes() {
        if (jawaSig == null) processJawa();
        return jawaTypes;
    }

    public static String getTypeName(String type) {
        switch (type) {
            case "Z": // boolean
            case "B": // byte
            case "C": // char
            case "S": // short
            case "I": // int
            case "D": // double
            case "F": // float
            case "J": // long
            case "V": // void
                return type;
            default:
                return "L";
        }
    }

    public static AnyType getJawaType(String type, TypeManager types) {
        boolean array = type.toCharArray()[0] == '[';
        type = array ? type.substring(1) : type;
        switch (type.toCharArray()[0]) {
            case 'Z': // boolean
                if (array) return types.arrayType(ValueType.bool);
            case 'B': // byte
            case 'C': // char
                if (array) return types.arrayType(ValueType.i8);
            case 'S': // short
                if (array) return types.arrayType(ValueType.i16);
            case 'I': // int
                if (array) return types.arrayType(ValueType.i32);
            case 'D': // double
                if (array) return types.arrayType(ValueType.f64);
            case 'F': // float
                if (array) return types.arrayType(ValueType.f32);
            case 'J': // long
                if (array) return types.arrayType(ValueType.i64);
            case 'V': // void
                return null;
            default:
                type = type.substring(1, type.length() - 1);
                return array ? types.arrayType(types.valueOf(type)) : types.valueOf(type);
        }
    }

    public void processJawa() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sig.length(); i++) {
            char s = sig.toCharArray()[i];
            switch (s) {
                case '[':
                    i++;
                    sb.append('L');
                    switch(sig.toCharArray()[i]) {
                        case 'L':
                            int start = i;
                            int end = 0;
                            while(sig.toCharArray()[i] != ';') {
                                end = i++;
                            }
                            jawaTypes.add(types.arrayType(types.valueOf(sig.substring(start + 1,end + 1))));
                            break;
                        case 'Z': // boolean
                            jawaTypes.add(types.arrayType(ValueType.bool));
                            break;
                        case 'B': // byte
                        case 'C': // char
                            jawaTypes.add(types.arrayType(ValueType.i8));
                            break;
                        case 'S': // short
                            jawaTypes.add(types.arrayType(ValueType.i16));
                            break;
                        case 'I': // int
                            jawaTypes.add(types.arrayType(ValueType.i32));
                            break;
                        case 'D': // double
                            jawaTypes.add(types.arrayType(ValueType.f64));
                            break;
                        case 'F': // float
                            jawaTypes.add(types.arrayType(ValueType.f32));
                            break;
                        case 'J': // long
                            jawaTypes.add(types.arrayType(ValueType.i64));
                            break;
                        case 'V': // void
                        default:
                            throw new WasmException("Invalid Array " + sig, -1);
                    }
                    break;
                case 'L':
                    sb.append('L');
                    int start = i;
                    int end = 0;
                    while(sig.toCharArray()[i] != ';') {
                        end = i++;
                    }
                    jawaTypes.add(types.valueOf(sig.substring(start + 1,end + 1)));
                    break;
                case 'Z': // boolean
                case 'B': // byte
                case 'C': // char
                case 'S': // short
                case 'I': // int
                case 'D': // double
                case 'F': // float
                case 'J': // long
                case 'V': // void
                    sb.append(s);
                    break;
                default:
            }
        }
        jawaSig = sb.toString();
    }

    public AnyType result() {
        AnyType r = null;
        boolean reachedNull = false;
        boolean nextArray = false;
        idx = 1;
        while (idx < sig.length()) {
            switch (sig.charAt(idx++)) {
                case ')':
                    reachedNull = true;
                    break;
                case '[': // array
                    if (reachedNull)
                        nextArray = true;
                    break;
                case 'L':
                    if (reachedNull) { // deal with arrays
                        int idx2 = sig.indexOf(';', idx);
                        String name = sig.substring(idx, idx2);
                        idx = idx2 + 1;
                        return types.valueOf(name);
                    } else {
                        while (sig.charAt(idx++) != ';');
                    }
                case 'Z': // boolean
                    if (reachedNull)
                        return !nextArray ? ValueType.bool : types.arrayType(ValueType.bool);;
                case 'B': // byte
                case 'C': // char
                    if (reachedNull)
                        return !nextArray ? ValueType.i8 : types.arrayType(ValueType.i8);
                case 'S': // short
                    if (reachedNull)
                        return !nextArray ? ValueType.i16 : types.arrayType(ValueType.i16);
                case 'I': // int
                    if (reachedNull)
                        return !nextArray ? ValueType.i32 : types.arrayType(ValueType.i32);
                case 'D': // double
                    if (reachedNull)
                        return !nextArray ? ValueType.f64 : types.arrayType(ValueType.f64);
                case 'F': // float
                    if (reachedNull)
                        return !nextArray ? ValueType.f32 : types.arrayType(ValueType.f32);
                case 'J': // long
                    if (reachedNull)
                        return !nextArray ? ValueType.i64 : types.arrayType(ValueType.i64);
                case 'V': // void
                    if (reachedNull)
                        return null;
                    break;
                default:
                    throw new WasmException( "Not supported Java data type in method signature: " + sig.substring( idx - 1 ), -1 );
            }
        }
        return null;
    }

    public List<AnyType> params() {

        ArrayList<AnyType> sigList = new ArrayList<>();
        int nextArray = 0; // TODO todo deal with nextArrays
        idx = 1;
        while (idx < sig.length()) {
            switch (sig.charAt(idx++)) {
                case ')':
                    return sigList;
                case '[': // array
                    nextArray += 1;
                    break;
                case 'L':
                    int idx2 = sig.indexOf( ';', idx );
                    String name = sig.substring( idx, idx2 );
                    idx = idx2 + 1;
                    AnyType t = types.valueOf(name);
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'Z': // boolean
                    t = ValueType.bool;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'B': // byte
                case 'C': // char
                    t = ValueType.i8;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'S': // short
                    t = ValueType.i16;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'I': // int
                    t = ValueType.i32;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'D': // double
                    t = ValueType.f64;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'F': // float
                    t = ValueType.f32;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'J': // long
                    t = ValueType.i64;
                    for (int i = 0; i < nextArray; i++) {
                        t = types.arrayType(t);
                    }
                    sigList.add(t);
                    nextArray = 0;
                    break;
                case 'V': // void
                default:
                    throw new WasmException( "Not supported Java data type in method signature: " + sig.substring( idx - 1 ), -1 );
            }
        }
        return sigList;
    }

    public List<AnyType> convertToList() {

        List<AnyType> params = params();
        params.add(null);
        AnyType result = result();
        if (result != null)
            params.add(result);
        return params;
    }

}
