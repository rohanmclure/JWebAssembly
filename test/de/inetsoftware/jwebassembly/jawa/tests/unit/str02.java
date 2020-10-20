package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "apple banana", output = "false")
@RunTest(input = "cabbage tomato", output = "false")
@RunTest(input = "hello hello", output = "true")
@RunTest(input = "foo foo", output = "true")
@RunTest(input = "CASE CASe", output = "false")
@RunTest(input = "1234 1234", output = "true")
public class str02 {

    @Export
    public static void main(String[] args) {

        if (args[0].equals(args[1])) {
            puts("true");
        } else {
            puts("false");
        }
    }
}
