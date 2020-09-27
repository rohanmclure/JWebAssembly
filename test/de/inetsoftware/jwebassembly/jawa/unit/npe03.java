package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(intInput = 11, exception = "NullPointerException")
public class npe03 {
    int f;

    static int m(int x) {
        int[] y = npe03.make();
        return y.length;
    }

    static int[] make() {
        return null;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
