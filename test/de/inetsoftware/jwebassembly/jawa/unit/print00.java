/*
 * Copyright 2017 - 2019 Volker Berlin (i-net software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;
import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(intInput = 0, output = "9223372036854775807")
@RunTest(intInput = 1, output = "-9223372036854775808")
@RunTest(intInput = 2, output = "123.012299") // Due to precision
@RunTest(intInput = 3, output = "0.0001")
@RunTest(intInput = 4, output = "9999.0")
@RunTest(intInput = 5, output = "-9223372036854775807")
@RunTest(intInput = 20, output = "123.099999")
public class print00 {

    public static void printd(double i) {
        long l = (long) i;
        print(l);

        i = (i - l) * 1_000_000;

        l = (long) (i < 0 ? -i : i);
        puts(".");

        if (l == 0) {
            puti(0);
            return;
        }

        int len = 0;
        for (long test = l; test != 0; test = test / 10) {
            len++;
        }
        while (len != 6) {
            puti(0);
            len++;
        }

        while (l % 10 == 0)
            l = l / 10;

        print(l);
    }


    public static void printd(double i, int precision) {
        long l = (long) i;
        print(l);

        i = (i - l) * (Math.pow(10,precision));

        l = (long) (i < 0 ? -i : i);

        puts(".");
        print(l);
    }

    public static void print(long i) {
        if (i == Long.MIN_VALUE) {
            puts("-9223372036854775808");
            return;
        }
        if (i < 0) {
            puts("-");
            i = -i;
        }
        if (i / 10 != 0)
            print(i/10);
        puti((int) (i % 10));
    }

    @Export
    public static void main(String[] args) {
        switch (args.length) {
            case 0:
                print(Long.MAX_VALUE);
                break;
            case 1:
                print(Long.MIN_VALUE);
                break;
            case 2:
                printd(123.0123);
                break;
            case 3:
                printd(0.0001);
                break;
            case 4:
                printd(9999.0);
                break;
            case 5:
                print(Long.MIN_VALUE + 1);
                break;
            default:
                printd(123.100);
                break;
        }
    }
}
