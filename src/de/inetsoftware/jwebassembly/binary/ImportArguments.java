package de.inetsoftware.jwebassembly.binary;

import de.inetsoftware.jwebassembly.wasm.AnyType;

import java.io.IOException;

public abstract class ImportArguments {

    abstract void writeTo( WasmOutputStream stream ) throws IOException;

    public static class AnyType extends ImportArguments {
        de.inetsoftware.jwebassembly.wasm.AnyType type;

        public AnyType(de.inetsoftware.jwebassembly.wasm.AnyType t) {
            this.type = t;
        }

        @Override
        void writeTo( WasmOutputStream stream ) throws IOException {
            stream.writeVaruint32(ExternalKind.TypeImport.ordinal());
            stream.writeVaruint32(type.getJawaCode());
        }
    }

    public static class Function extends ImportArguments {
        de.inetsoftware.jwebassembly.module.Function f;

        public Function(de.inetsoftware.jwebassembly.module.Function f) {
            this.f = f;
        }

        @Override
        void writeTo(WasmOutputStream stream) throws IOException {
            stream.writeVaruint32(ExternalKind.Function.ordinal());
            stream.writeVaruint32(((de.inetsoftware.jwebassembly.binary.Function) f).id);
        }

    }

}
