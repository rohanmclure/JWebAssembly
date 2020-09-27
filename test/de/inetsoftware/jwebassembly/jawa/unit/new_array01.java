package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 4, output = "4")
@RunTest(input = "", intInput = 127, output = "127")
public class new_array01 {
    static int m(int x) {
        int[] y = new int[x];
        return x;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
