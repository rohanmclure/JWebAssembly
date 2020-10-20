package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 1, output = "0")
public class while00 {
    static int t(int a) {
        while (true)
            return 0;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
