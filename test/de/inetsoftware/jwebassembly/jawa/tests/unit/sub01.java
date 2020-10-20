package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 77, output = "66")
@RunTest(input = "", intInput = 111, output = "100")
public class sub01 {
    static int run(int a) {
        return a - 11;
    }

    @Export
    public static void main(String[] args) {
        puti(run(args.length));
    }
}
