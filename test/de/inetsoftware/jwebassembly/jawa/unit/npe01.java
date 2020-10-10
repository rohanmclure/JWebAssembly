package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(intInput = 11, exception = "NULL_DEREF")
public class npe01 {
    int f;

    static int m(int x) {
        npe01 j = npe01.make();
        j.f = x;
        return j.f;
    }

    static npe01 make() {
        return null;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
