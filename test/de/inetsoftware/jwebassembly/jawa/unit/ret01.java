package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 88, output = "88")
@RunTest(input = "", intInput = 99, output = "99")
public class ret01 {
    static int m(int x) {
        int f = ret01.n(x);
        return f;
    }

    static int n(int x) {
        return x;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
