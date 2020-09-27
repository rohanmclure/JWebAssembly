package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1210")
@RunTest(input = "", intInput = 5, output = "1650")
public class xc_cont02 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        xc_cont02.L = a;
        for (int i = xc_cont02.xc_cont02(); i < xc_cont02.C(); i = i + xc_cont02.U()) {
            if (true) continue;
            xc_cont02.B();
        }
        return 1000 * xc_cont02.ci + 100 * xc_cont02.cc + 10 * xc_cont02.cu + xc_cont02.cb;
    }

    static int xc_cont02() {
        xc_cont02.ci = xc_cont02.ci + 1;
        return 0;
    }

    static int C() {
        xc_cont02.cc = xc_cont02.cc + 1;
        return xc_cont02.L;
    }

    static int U() {
        xc_cont02.cu = xc_cont02.cu + 1;
        return 1;
    }

    static void B() {
        xc_cont02.cb = xc_cont02.cb + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
