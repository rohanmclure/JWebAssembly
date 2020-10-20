package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "3")
public class char00 {

    static int foo(int a) {
        return 'D' - 'A';
    }

    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }

}