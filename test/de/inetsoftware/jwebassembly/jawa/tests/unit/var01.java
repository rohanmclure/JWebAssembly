package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "1")
@RunTest(input = "", intInput = 1, output = "2")
@RunTest(input = "", intInput = 2, output = "3")
@RunTest(input = "", intInput = 3, output = "4")
@RunTest(input = "", intInput = 4, output = "347")
public class var01 {
    static int m(int a) {
        int i = 1, j = 2, k = 3, l = 4;
        if (a == 0) return i;
        if (a == 1) return j;
        if (a == 2) return k;
        if (a == 3) return l;
        return 347;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
