package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "10")
@RunTest(input = "", intInput = 4, output = "13")
public class var00 {
    static int m(int a) {
        int i = 0, j = 0, k = 0, m = 9;
        return m + a;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
