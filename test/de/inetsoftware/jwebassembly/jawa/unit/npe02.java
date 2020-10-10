package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(intInput = 11, exception = "NULL_DEREF")
public class npe02 {
    int f;

    static int m(int x) {
        int[] y = npe02.make();
        return y[x];
    }

    static int[] make() {
        return null;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
