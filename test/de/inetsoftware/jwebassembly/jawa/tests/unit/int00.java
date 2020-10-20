package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "1337")
public class int00 {
    static int m(int a) {
        return 1337;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
