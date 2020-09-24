package de.inetsoftware.jwebassembly.jawa;

public class JawaImmKind { // None
    public class STRING extends JawaImmKind {
        private int length;
        private String bytes; // UTF-8 encoded UTF-16
        STRING(String string) {
            length = string.length();
            bytes = string;
        }
    }

    public class TYPE extends JawaImmKind {
        private int typeIndex;
        TYPE(int typeIndex) { this.typeIndex = typeIndex; }
    }

    public class CLASS_TYPE extends TYPE {
        CLASS_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
    }

    public class INTERFACE_TYPE extends TYPE {
        INTERFACE_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
    }

    public class REF_TYPE extends TYPE {
        REF_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
    }

    public class ARRAY_TYPE extends TYPE {
        ARRAY_TYPE(int typeIndex) { super(typeIndex); } // assert index is type(?)
    }

    public class TYPE_SELECTOR extends TYPE {
        STRING fieldOrMethod;
        TYPE_SELECTOR(int typeIndex, String fieldOrMethod) {
            super(typeIndex);
            this.fieldOrMethod = new STRING(fieldOrMethod);
        }
    }

    public class INSTANCE_FIELD_SELECTOR extends TYPE_SELECTOR {
        INSTANCE_FIELD_SELECTOR(int typeIndex, String instanceField) { super(typeIndex, instanceField); }
    }

    public class STATIC_FIELD_SELECTOR extends TYPE_SELECTOR {
        STATIC_FIELD_SELECTOR(int typeIndex, String staticField) { super(typeIndex, staticField); }
    }

    public class INTERFACE_METHOD_SELECTOR extends TYPE_SELECTOR {
        INTERFACE_METHOD_SELECTOR(int typeIndex, String interfaceMethod) { super(typeIndex, interfaceMethod); }
    }

    public class INSTANCE_METHOD_SELECTOR extends TYPE_SELECTOR {
        INSTANCE_METHOD_SELECTOR(int typeIndex, String instanceMethod) { super(typeIndex, instanceMethod); }
    }

    public class STATIC_METHOD_SELECTOR extends TYPE_SELECTOR {
        STATIC_METHOD_SELECTOR(int typeIndex, String staticMethod) { super(typeIndex, staticMethod); }
    }

    public class INT_ARRAY_TYPE extends JawaImmKind { // TODO? ji4, ji4: type index, must be array
    }

    public class CLASS_DECL extends JawaImmKind {
        STRING className;
        int typeIndex;
        int[] interfaceTypeIndices;
        CLASS_DECL(String className, int typeIndex, int... interfaceTypeIndices) {
            this.className = new STRING(className);
            this.typeIndex = typeIndex;
            this.interfaceTypeIndices = interfaceTypeIndices;
        }
    }
    public class INTERFACE_DECL extends JawaImmKind {
        STRING interfaceName;
        int[] interfaceTypeIndices;
        INTERFACE_DECL(String interfaceName, int... interfaceTypeIndices) {
            this.interfaceName = new STRING(interfaceName);
            this.interfaceTypeIndices = interfaceTypeIndices;
        }
    }

    public class CLASS_DEF extends JawaImmKind {
        int typeIndex;
        int[] instanceFields;
        int[] instanceMethods;
        int[] staticFields;
        int[] staticMethods;
        CLASS_DEF(int typeIndex, int[] instanceFields, int[] instanceMethods, int[] staticFields, int[] staticMethods) {
            this.typeIndex = typeIndex;
            this.instanceFields = instanceFields;
            this.instanceMethods = instanceMethods;
            this.staticFields = staticFields;
            this.staticMethods = staticMethods;
        }
    }

    public class INTERFACE_DEF extends JawaImmKind {
        int typeIndex;
        int[] interfaceMethods;
        INTERFACE_DEF(int typeIndex, int[] interfaceMethods) {
            this.typeIndex = typeIndex;
            this.interfaceMethods = interfaceMethods;
        }
    }
}