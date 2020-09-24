package de.inetsoftware.jwebassembly.jawa;

public class JawaOpcodes {

    public enum JawaFuncOpcode {
        AALOAD		    (0x10, JawaImmKind.REF_TYPE),
        AASTORE		    (0x11, JawaImmKind.REF_TYPE),
        ACMPEQ		    (0x12, JawaImmKind.NONE),
        ANEWARRAY	    (0x13, JawaImmKind.REF_TYPE),
        ARRAYLENGTH 	(0x14, JawaImmKind.ARRAY_TYPE),
        ATHROW		    (0x15, JawaImmKind.NONE),
        BALOAD	    	(0x16, JawaImmKind.NONE),
        BASTORE	    	(0x17, JawaImmKind.NONE),
        CALOAD	    	(0x18, JawaImmKind.NONE),
        CASTORE	    	(0x19, JawaImmKind.NONE),
        CHECKCAST   	(0x1A, JawaImmKind.REF_TYPE),
        DALOAD		    (0x1B, JawaImmKind.NONE),
        DASTORE		    (0x1C, JawaImmKind.NONE),
        DCMPG	    	(0x1D, JawaImmKind.NONE),
        DCMPL	    	(0x1E, JawaImmKind.NONE),
        DREM		    (0x1F, JawaImmKind.NONE),
        FALOAD	    	(0x20, JawaImmKind.NONE),
        FASTORE	    	(0x21, JawaImmKind.NONE),
        FCMPG	    	(0x22, JawaImmKind.NONE),
        FCMPL	    	(0x23, JawaImmKind.NONE),
        FREM	    	(0x24, JawaImmKind.NONE),
        GETFIELD    	(0x25, JawaImmKind.INSTANCE_FIELD_SELECTOR),
        GETSTATIC   	(0x26, JawaImmKind.STATIC_FIELD_SELECTOR),
        IALOAD		    (0x27, JawaImmKind.NONE),
        IASTORE		    (0x28, JawaImmKind.NONE),
        INSTANCEOF	    (0x29, JawaImmKind.REF_TYPE),
        INVOKEDYNAMIC	(0x2A, JawaImmKind.INSTANCE_METHOD_SELECTOR),
        INVOKEINTERFACE	(0x2B, JawaImmKind.INTERFACE_METHOD_SELECTOR),
        INVOKESPECIAL	(0x2C, JawaImmKind.INSTANCE_METHOD_SELECTOR),
        INVOKESTATIC	(0x2D, JawaImmKind.STATIC_METHOD_SELECTOR),
        INVOKEVIRTUAL	(0x2E, JawaImmKind.INSTANCE_METHOD_SELECTOR),
        LALOAD	    	(0x2F, JawaImmKind.NONE),
        LASTORE		    (0x30, JawaImmKind.NONE),
        MONITORENTER	(0x31, JawaImmKind.NONE),
        MONITOREXIT	    (0x32, JawaImmKind.NONE),
        MULTIANEWARRAY	(0x33, JawaImmKind.INT_ARRAY_TYPE),
        NEW		        (0x34, JawaImmKind.CLASS_TYPE),
        NEWARRAY	    (0x35, JawaImmKind.ARRAY_TYPE),
        ISNULL		    (0x36, JawaImmKind.NONE),
        PUTFIELD	    (0x37, JawaImmKind.INSTANCE_FIELD_SELECTOR),
        PUTSTATIC	    (0x38, JawaImmKind.STATIC_FIELD_SELECTOR),
        SALOAD		    (0x39, JawaImmKind.NONE),
        SASTORE		    (0x3A, JawaImmKind.NONE),
        SYSCALL         (0x3B, JawaImmKind.STRING);

        public final int opcode;
        public final JawaImmKind immKind;

        private JawaFuncOpcode(int opcode, JawaImmKind immKind) {
            this.opcode = opcode;
            this.immKind = immKind;
        }

    }

    // Codes and immediates for globals that can be imported into a Jawa module.
    public enum JawaGlobalOpcode {
        CLASS_CONST	(0x50, JawaImmKind.CLASS_TYPE),
        INTERFACE_CONST	(0x51, JawaImmKind.INTERFACE_TYPE),
        STRING_CONST	(0x52, JawaImmKind.STRING);

        public final int opcode;
        public final JawaImmKind immKind;
        private JawaGlobalOpcode(int opcode, JawaImmKind immKind) {
            this.opcode = opcode;
            this.immKind = immKind;
        }
    }

    // Codes and immediates for types that can be imported into a Jawa module.
    public enum JawaTypeOpcode {
        BYTE_ARRAY	    	(0x40, JawaImmKind.NONE),
        BOOL_ARRAY	    	(0x41, JawaImmKind.NONE),
        CHAR_ARRAY	    	(0x42, JawaImmKind.NONE),
        SHORT_ARRAY	    	(0x43, JawaImmKind.NONE),
        INT_ARRAY	    	(0x44, JawaImmKind.NONE),
        LONG_ARRAY	    	(0x45, JawaImmKind.NONE),
        FLOAT_ARRAY	    	(0x46, JawaImmKind.NONE),
        DOUBLE_ARRAY		(0x47, JawaImmKind.NONE),
        EXT_CLASS		    (0x48, JawaImmKind.STRING),
        EXT_INTERFACE		(0x49, JawaImmKind.STRING),
        DECL_CLASS		    (0x4A, JawaImmKind.CLASS_DECL),
        DECL_INTERFACE		(0x4B, JawaImmKind.INTERFACE_DECL),
        REF_ARRAY		    (0x4C, JawaImmKind.REF_TYPE),
        DEF_CLASS		    (0x4D, JawaImmKind.CLASS_DEF),
        DEF_INTERFACE		(0x4E, JawaImmKind.INTERFACE_DEF);

        public final int opcode;
        public final JawaImmKind immKind;
        private JawaTypeOpcode(int opcode, JawaImmKind immKind) {
            this.opcode = opcode;
            this.immKind = immKind;
        }
    }

    public enum JawaImmKind {
        NONE,
        STRING,				// ji2: length, bytes: UTF-8 encoded UTF-16
        CLASS_TYPE,			// ji4: type index, must be class
        INTERFACE_TYPE,			// ji4: type index, must be interface
        REF_TYPE,			// ji4: type index, must be ref
        ARRAY_TYPE,			// ji4: type index, must be array
        INSTANCE_FIELD_SELECTOR,	// ji4: type index, must be class, string, must refer to class field
        STATIC_FIELD_SELECTOR,		// ji4: type index, string, must refer to static class field
        INTERFACE_METHOD_SELECTOR,	// ji4: type index, string, must refer to interface method
        INSTANCE_METHOD_SELECTOR,	// ji4: type index, must be class,  string, must refer to class method
        STATIC_METHOD_SELECTOR,		// ji4: type index, string, must refer to static class method
        INT_ARRAY_TYPE,			// ji4, ji4: type index, must be array
        CLASS_DECL,			// string, ji4: type index, must be class, ji4*: type index, must be interfaces
        INTERFACE_DECL,			// string, ji4*: type index, must be interfaces
        CLASS_DEF,			// ji4: type index, must be class, instance fields/methods, static fields/methods
        INTERFACE_DEF;			// ji4: type index, must be interface, interface methods
    }

//    public class JawaImmKind { // None
//
//        public class STRING extends JawaImmKind {
//            private int length;
//            private String bytes; // UTF-8 encoded UTF-16
//            STRING(String string) {
//                length = string.length();
//                bytes = string;
//            }
//        }
//
//        public class TYPE extends JawaImmKind {
//            private int typeIndex;
//            TYPE(int typeIndex) { this.typeIndex = typeIndex; }
//        }
//
//        public class CLASS_TYPE extends TYPE {
//            CLASS_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
//        }
//
//        public class INTERFACE_TYPE extends TYPE {
//            INTERFACE_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
//        }
//
//        public class REF_TYPE extends TYPE {
//            REF_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
//        }
//
//        public class ARRAY_TYPE extends TYPE {
//            ARRAY_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
//        }
//
//        public class TYPE_SELECTOR extends TYPE {
//            STRING fieldOrMethod;
//            TYPE_SELECTOR(int typeIndex, String fieldOrMethod) {
//                super(typeIndex);
//                this.fieldOrMethod = new STRING(fieldOrMethod);
//            }
//        }
//
//        public class INSTANCE_FIELD_SELECTOR extends TYPE_SELECTOR {
//            INSTANCE_FIELD_SELECTOR(int typeIndex, String instanceField) { super(typeIndex, instanceField); }
//        }
//
//        public class STATIC_FIELD_SELECTOR extends TYPE_SELECTOR {
//            STATIC_FIELD_SELECTOR(int typeIndex, String staticField) { super(typeIndex, staticField); }
//        }
//
//        public class INTERFACE_METHOD_SELECTOR extends TYPE_SELECTOR {
//            INTERFACE_METHOD_SELECTOR(int typeIndex, String interfaceMethod) { super(typeIndex, interfaceMethod); }
//        }
//
//        public class INSTANCE_METHOD_SELECTOR extends TYPE_SELECTOR {
//            INSTANCE_METHOD_SELECTOR(int typeIndex, String instanceMethod) { super(typeIndex, instanceMethod); }
//        }
//
//        public class STATIC_METHOD_SELECTOR extends TYPE_SELECTOR {
//            STATIC_METHOD_SELECTOR(int typeIndex, String staticMethod) { super(typeIndex, staticMethod); }
//        }
//
//        public class INT_ARRAY_TYPE extends JawaImmKind { // TODO? ji4, ji4: type index, must be array
//        }
//
//        public class CLASS_DECL extends JawaImmKind {
//            STRING className;
//            int typeIndex;
//            int[] interfaceTypeIndices;
//            CLASS_DECL(String className, int typeIndex, int... interfaceTypeIndices) {
//                this.className = new STRING(className);
//                this.typeIndex = typeIndex;
//                this.interfaceTypeIndices = interfaceTypeIndices;
//            }
//        }
//        public class INTERFACE_DECL extends JawaImmKind {
//            STRING interfaceName;
//            int[] interfaceTypeIndices;
//            INTERFACE_DECL(String interfaceName, int... interfaceTypeIndices) {
//                this.interfaceName = new STRING(interfaceName);
//                this.interfaceTypeIndices = interfaceTypeIndices;
//            }
//        }
//
//        public class CLASS_DEF extends JawaImmKind {
//            int typeIndex;
//            int[] instanceFields;
//            int[] instanceMethods;
//            int[] staticFields;
//            int[] staticMethods;
//            CLASS_DEF(int typeIndex, int[] instanceFields, int[] instanceMethods, int[] staticFields, int[] staticMethods) {
//                this.typeIndex = typeIndex;
//                this.instanceFields = instanceFields;
//                this.instanceMethods = instanceMethods;
//                this.staticFields = staticFields;
//                this.staticMethods = staticMethods;
//            }
//        }
//
//        public class INTERFACE_DEF extends JawaImmKind {
//            int typeIndex;
//            int[] interfaceMethods;
//            INTERFACE_DEF(int typeIndex, int[] interfaceMethods) {
//                this.typeIndex = typeIndex;
//                this.interfaceMethods = interfaceMethods;
//            }
//        }
//    }

}