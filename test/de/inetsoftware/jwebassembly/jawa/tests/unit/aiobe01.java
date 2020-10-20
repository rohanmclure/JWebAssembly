package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "5")
@RunTest(input = "1 2", exception = "ARRAY_INDEX_OOB")
public class aiobe01 {
    static int m(int x) {
        int[] y = new int[1];
        y[0] = 5;
        return y[x];
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
