package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 77, output = "1001")
@RunTest(input = "", intInput = 1, output = "13")
public class mul01 {
    static int run(int a) {
        return a * 13;
    }

    @Export
    public static void main(String[] args) {
        puti(run(args.length));
    }

}
