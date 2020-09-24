package de.inetsoftware.jwebassembly.jawa;

public class JawaTypes {

    public class PRIMITIVE_ARRAY extends JawaTypes {
        JawaOpcodes.JawaTypeOpcode typeOpcode;
        PRIMITIVE_ARRAY(JawaOpcodes.JawaTypeOpcode typeOpcode) {
            this.typeOpcode = typeOpcode;
            // assert type is between 0x40 and 0x47
        }

        @Override
        public String toString() {
            return typeOpcode.toString();
        }
    }

//    public class EXT_CLASS {
//        @Override
//        public
//    }
//
//
//    enum JawaTypeOpcode(code: byte, imm: JawaImmKind) {
//        BYTE_ARRAY		(0x40, JawaImmKind.NONE),
//        BOOL_ARRAY		(0x41, JawaImmKind.NONE),
//        CHAR_ARRAY		(0x42, JawaImmKind.NONE),
//        SHORT_ARRAY		(0x43, JawaImmKind.NONE),
//        INT_ARRAY		(0x44, JawaImmKind.NONE),
//        LONG_ARRAY		(0x45, JawaImmKind.NONE),
//        FLOAT_ARRAY		(0x46, JawaImmKind.NONE),
//        DOUBLE_ARRAY		(0x47, JawaImmKind.NONE),
//        EXT_CLASS		(0x48, JawaImmKind.STRING),
//        EXT_INTERFACE		(0x49, JawaImmKind.STRING),
//        DECL_CLASS		(0x4A, JawaImmKind.CLASS_DECL),
//        DECL_INTERFACE		(0x4B, JawaImmKind.INTERFACE_DECL),
//        REF_ARRAY		(0x4C, JawaImmKind.REF_TYPE),
//    }

}
