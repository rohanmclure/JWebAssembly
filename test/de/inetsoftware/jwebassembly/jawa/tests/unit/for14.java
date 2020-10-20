package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "110")
@RunTest(input = "", intInput = 1, output = "121")
@RunTest(input = "", intInput = 5, output = "165")
public class for14 {
    static int L;
    static int ci;
    static int cc;
    static int cu;

    static int m(int a) {
        for14.L = a;
        for (int i = for14.for14(); i < for14.C(); i = i + for14.U()) {
        }
        return 100 * for14.ci + 10 * for14.cc + for14.cu;
    }

    static int for14() {
        for14.ci = for14.ci + 1;
        return 0;
    }

    static int C() {
        for14.cc = for14.cc + 1;
        return for14.L;
    }

    static int U() {
        for14.cu = for14.cu + 1;
        return 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
