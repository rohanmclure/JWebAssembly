package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

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
