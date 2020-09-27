package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "334")
public class for09 {
    static int t(int a) {
        for (int i = 0; a != 0; i = i + 1) {
            a = 0;
        }
        return 334;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
