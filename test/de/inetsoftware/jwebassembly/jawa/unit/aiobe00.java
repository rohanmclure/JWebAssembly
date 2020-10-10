package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "0")
@RunTest(input = "", exception = "ARRAY_INDEX_OOB")
public class aiobe00 {
    static int m(int x) {
        int[] y = new int[x];
        return y[x - 1];
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
