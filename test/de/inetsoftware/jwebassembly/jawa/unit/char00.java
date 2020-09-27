package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

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