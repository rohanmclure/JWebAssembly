package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "66")
@RunTest(input = "", intInput = 11, output = "66")
public class var05 {
    static int q(int a) {
        int i = 66;
        if (a < 10) {
            int h = 1;
        } else {
            int h = 2;
        }
        return i;
    }

    @Export
    public static void main(String[] args) {
        puti(q(args.length));
    }
}
