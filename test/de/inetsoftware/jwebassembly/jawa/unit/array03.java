package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;
import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input="",intInput=4,output="false")
@RunTest(input="",intInput=127,output="true")
public class array03 {
    static boolean m(int x) {
        boolean[] y = new boolean[x];
        y[0] = x > 10;
        return y[0];
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) {
            puts("true");
        } else {
            puts("false");
        }
    }
}
