package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "10")
@RunTest(input = "", intInput = 1, output = "20")
@RunTest(input = "", intInput = 2, output = "30")
@RunTest(input = "", intInput = 3, output = "40")
@RunTest(input = "", intInput = 4, output = "346")
public class var02 {
    static int m(int a) {
        int i = 1, j = 2, k = 3, l = 4;
        if (a == 0) return var02.p(i);
        if (a == 1) return var02.p(j);
        if (a == 2) return var02.p(k);
        if (a == 3) return var02.p(l);
        return 346;
    }

    static int p(int a) {
        int i = 10, j = 20, k = 30, l = 40;
        if (a == 1) return i;
        if (a == 2) return j;
        if (a == 3) return k;
        if (a == 4) return l;
        return 349;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
