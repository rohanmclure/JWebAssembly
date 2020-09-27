package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

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