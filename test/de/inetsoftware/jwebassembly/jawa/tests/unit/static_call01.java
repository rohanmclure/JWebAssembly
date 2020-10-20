package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 999, output = "999")
@RunTest(input = "", intInput = 33, output = "33")
public class static_call01 {
    static int m(int a) {
        return static_call01.g(a);
    }

    static int g(int a) {
        return a;
    }


    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
