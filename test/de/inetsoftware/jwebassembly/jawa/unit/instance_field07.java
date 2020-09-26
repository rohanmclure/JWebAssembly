package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", output = "0")
@RunTest(input = "1 2 3 4", output = "4")
public class instance_field07 {

    static class L {
        L prev;
        int val;
    }

    static class M {
        static int m(int a) {
            L l = null;
            while (a > 0) {
                L n = new L();
                n.prev = l;
                n.val = a;
                l = n;
                a = a - 1;
            }
            int s = 0;
            while (l != null) {
                l = l.prev;
                s = s + 1;
            }
            return s;
        }
    }

    @Export
    public static void main(String[] args) {
        puti(M.m(args.length));
    }

}
