package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "ban na banana", output = "false")
@RunTest(input = "apple banana tomato", output = "false")
@RunTest(input = "he llo hello", output = "true")
@RunTest(input = "foo bar foobar", output = "true")
@RunTest(input = "CAS E CASe", output = "false")
@RunTest(input = "12 34 1234", output = "true")
public class str03 {

    @Export
    public static void main(String[] args) {

        if (args[2].equals(args[0] + args[1])) {
            puts("true");
        } else {
            puts("false");
        }
    }
}
