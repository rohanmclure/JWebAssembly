package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "10")
@RunTest(input = "", intInput = 1, output = "20")
@RunTest(input = "", intInput = 2, output = "30")
@RunTest(input = "", intInput = 3, output = "40")
@RunTest(input = "", intInput = 4, output = "343")
public class var03 {
    static int m(int a) {
        int i = 1;
        if (a == 0) return var03.p(i);
        int j = 2;
        if (a == 1) return var03.p(j);
        int k = 3;
        if (a == 2) return var03.p(k);
        int l = 4;
        if (a == 3) return var03.p(l);
        return 343;
    }

    static int p(int a) {
        int i = 10;
        if (a == 1) return i;
        int j = 20;
        if (a == 2) return j;
        int k = 30;
        if (a == 3) return k;
        int l = 40;
        if (a == 4) return l;
        return 349;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
