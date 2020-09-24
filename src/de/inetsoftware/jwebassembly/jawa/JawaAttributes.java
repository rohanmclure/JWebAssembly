package de.inetsoftware.jwebassembly.jawa;

public class JawaAttributes {

    public enum JawaFieldAttr {
        PUBLIC		(0x0001),
        PRIVATE		(0x0002),
        PROTECTED	(0x0004),
        FINAL		(0x0010),
        VOLATILE	(0x0040),
        TRANSIENT	(0x0080),
        SYNTHETIC	(0x1000),
        ENUM		(0x2000);

        public final int opcode;

        private JawaFieldAttr(int opcode) {
            this.opcode = opcode;
        }

        public static int convertTo(int java) {
            boolean publ = (java & JavaAttributes.FieldAttributes.ACC_PUBLIC.opcode) != 0;
            boolean priv = (java & JavaAttributes.FieldAttributes.ACC_PRIVATE.opcode) != 0;
            boolean prot = (java & JavaAttributes.FieldAttributes.ACC_PROTECTED.opcode) != 0;
//            boolean stat = (java & JavaAttributes.FieldAttributes.ACC_STATIC.opcode) != 0;
            boolean finl = (java & JavaAttributes.FieldAttributes.ACC_FINAL.opcode) != 0;
            boolean vola = (java & JavaAttributes.FieldAttributes.ACC_VOLATILE.opcode) != 0;
            boolean tran = (java & JavaAttributes.FieldAttributes.ACC_TRANSIENT.opcode) != 0;
            boolean synt = (java & JavaAttributes.FieldAttributes.ACC_SYNTHETIC.opcode) != 0;
            boolean enun = (java & JavaAttributes.FieldAttributes.ACC_ENUM.opcode) != 0;

            int jawaFieldFlag = 0;
            if (publ) jawaFieldFlag += PUBLIC.opcode;
            if (priv) jawaFieldFlag += PRIVATE.opcode;
            if (prot) jawaFieldFlag += PROTECTED.opcode;
            if (finl) jawaFieldFlag += FINAL.opcode;
            if (vola) jawaFieldFlag += VOLATILE.opcode;
            if (tran) jawaFieldFlag += TRANSIENT.opcode;
            if (synt) jawaFieldFlag += SYNTHETIC.opcode;
            if (enun) jawaFieldFlag += ENUM.opcode;
            return jawaFieldFlag;
        }

    }

    public enum JawaMethodAttr {
        PUBLIC		(0x0001),
        PRIVATE		(0x0002),
        PROTECTED	(0x0004),
        FINAL		(0x0010),
        SYNCHRONIZED	(0x0020),
        BRIDGE		(0x0040),
        VARARGS		(0x0080),
        NATIVE		(0x0100),
        ABSTRACT	(0x0400),
        STRICT		(0x0800),
        SYNTHETIC	(0x1000);

        public final int opcode;

        private JawaMethodAttr(int opcode) {
            this.opcode = opcode;
        }

        public static int convertTo(int java) {
            boolean publ = (java & JavaAttributes.MethodAttributes.ACC_PUBLIC.opcode) != 0;
            boolean priv = (java & JavaAttributes.MethodAttributes.ACC_PRIVATE.opcode) != 0;
            boolean prot = (java & JavaAttributes.MethodAttributes.ACC_PROTECTED.opcode) != 0;
//            boolean stat = (java & JavaAttributes.MethodAttributes.ACC_STATIC.opcode) != 0;
            boolean finl = (java & JavaAttributes.MethodAttributes.ACC_FINAL.opcode) != 0;
            boolean sync = (java & JavaAttributes.MethodAttributes.ACC_SYNCHRONIZED.opcode) != 0;
            boolean brid = (java & JavaAttributes.MethodAttributes.ACC_BRIDGE.opcode) != 0;
            boolean vara = (java & JavaAttributes.MethodAttributes.ACC_VARARGS.opcode) != 0;
            boolean nati = (java & JavaAttributes.MethodAttributes.ACC_NATIVE.opcode) != 0;
            boolean abst = (java & JavaAttributes.MethodAttributes.ACC_ABSTRACT.opcode) != 0;
            boolean strc = (java & JavaAttributes.MethodAttributes.ACC_STRICT.opcode) != 0;
            boolean synt = (java & JavaAttributes.MethodAttributes.ACC_SYNTHETIC.opcode) != 0;

            int jawaMethodFlag = 0;
            if (publ) jawaMethodFlag += PUBLIC.opcode;
            if (priv) jawaMethodFlag += PRIVATE.opcode;
            if (prot) jawaMethodFlag += PROTECTED.opcode;
            if (finl) jawaMethodFlag += FINAL.opcode;
            if (sync) jawaMethodFlag += SYNCHRONIZED.opcode;
            if (brid) jawaMethodFlag += BRIDGE.opcode;
            if (vara) jawaMethodFlag += VARARGS.opcode;
            if (nati) jawaMethodFlag += NATIVE.opcode;
            if (abst) jawaMethodFlag += ABSTRACT.opcode;
            if (strc) jawaMethodFlag += STRICT.opcode;
            if (synt) jawaMethodFlag += SYNTHETIC.opcode;
            return jawaMethodFlag;

        }

    }

    public enum JawaClassAttr {
        PUBLIC		(0x0001),
        FINAL		(0x0002),
        SUPER		(0x0004),
        ABSTRACT	(0x0008),
        SYNTHETIC	(0x0010),
        ANNOTATION	(0x0020),
        ENUM		(0x0040);

        public final int opcode;

        private JawaClassAttr(int opcode) {
            this.opcode = opcode;
        }

        public static int convertToJawa(int java) {
            boolean publ = (java & JavaAttributes.ClassAttributes.ACC_PUBLIC.opcode) != 0;
//            boolean priv = (java & JavaAttributes.ClassAttributes.ACC_PRIVATE.opcode) != 0;
//            boolean prot = (java & JavaAttributes.ClassAttributes.ACC_PROTECTED.opcode) != 0;
//            boolean stat = (java & JavaAttributes.ClassAttributes.ACC_STATIC.opcode) != 0;
            boolean finl = (java & JavaAttributes.ClassAttributes.ACC_FINAL.opcode) != 0;
            boolean supr = (java & JavaAttributes.ClassAttributes.ACC_SUPER.opcode) != 0;
//            boolean intf = (java & JavaAttributes.ClassAttributes.ACC_INTERFACE.opcode) != 0;
            boolean abst = (java & JavaAttributes.ClassAttributes.ACC_ABSTRACT.opcode) != 0;
            boolean synt = (java & JavaAttributes.ClassAttributes.ACC_SYNTHETIC.opcode) != 0;
            boolean anno = (java & JavaAttributes.ClassAttributes.ACC_ANNOTATION.opcode) != 0;
            boolean enun = (java & JavaAttributes.ClassAttributes.ACC_ENUM.opcode) != 0;
            int jawaClassFlag = 0;

            if (publ) jawaClassFlag += PUBLIC.opcode;
            if (finl) jawaClassFlag += FINAL.opcode;
            if (supr) jawaClassFlag += SUPER.opcode;
            if (abst) jawaClassFlag += ABSTRACT.opcode;
            if (synt) jawaClassFlag += SYNTHETIC.opcode;
            if (anno) jawaClassFlag += ANNOTATION.opcode;
            if (enun) jawaClassFlag += ENUM.opcode;

            return jawaClassFlag;
        }
    }

}