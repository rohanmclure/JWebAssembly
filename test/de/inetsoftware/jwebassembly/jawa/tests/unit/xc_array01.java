package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "5")
public class xc_array01 {
    static int a(int l) {
        int[][] x = new int[l][];
        return x.length;
    }

    @Export
    public static void main(String[] args) {
        puti(a(args.length));
    }
}
