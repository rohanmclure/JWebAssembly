package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", exception = "ARRAY_INDEX_OOB")
@RunTest(input = "1", exception = "ARRAY_INDEX_OOB")
@RunTest(input = "2 2", exception = "ARRAY_INDEX_OOB")
public class aiobe02 {
    static int m(int x) {
        int[] y = new int[x];
        return y[x];
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
