package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

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
