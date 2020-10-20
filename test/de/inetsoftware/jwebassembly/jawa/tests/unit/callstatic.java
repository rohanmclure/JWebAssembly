package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "4")
public class callstatic {
    static int foo(int b) {

        callstatic a = new callstatic();
        return bar();

    }

    static int bar() {
        return 4;
    }

    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }
}