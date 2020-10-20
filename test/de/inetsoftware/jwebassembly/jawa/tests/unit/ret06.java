package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 3, output = "999")
@RunTest(input = "", intInput = 103, output = "1099")
public class ret06 {
    static int w(int a) {
        return a + ret06.f() + ret06.g();
    }

    static int f() {
        return 666;
    }

    static int g() {
        return 330;
    }

    @Export
    public static void main(String[] args) {
        puti(w(args.length));
    }
}
