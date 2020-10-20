package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 1, output = "332")
public class while02 {
    static int t(int a) {
        boolean z = true;
        while (z) {
            z = false;
        }
        return 332;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
