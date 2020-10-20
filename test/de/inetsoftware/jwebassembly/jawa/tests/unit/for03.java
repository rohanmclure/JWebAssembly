package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "334")
public class for03 {
    static int t(int a) {
        for (; a != 0; ) {
            a = 0;
        }
        return 334;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
