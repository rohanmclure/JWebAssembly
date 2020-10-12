package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "a", output = "true")
@RunTest(input = "b", output = "false")
@RunTest(input = "aa", output = "false")
@RunTest(input = "a b c", output = "true")
public class str01 {

    @Export
    public static void main(String[] args) {

        if (args[0].equals("a")) {
            puts("true");
        } else {
            puts("false");
        }
    }
}
