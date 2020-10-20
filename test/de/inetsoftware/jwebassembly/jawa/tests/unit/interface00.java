package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "1")
public class interface00 {

    interface A {
        int a();
    }

    static class AImpl implements A {
        @Override
        public int a() {
            return 1;
        }
    }

    static int run(int a) {
        A b = new AImpl();
        return b.a();
    }

    @Export
    public static void main(String[] args) {
        puti(run(args.length));
    }
}
