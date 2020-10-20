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
package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import de.inetsoftware.jwebassembly.jawa.util.*;
import de.inetsoftware.jwebassembly.jawa.util.Math;

@RunTest(intInput = 0, output = "1\n2.71828182846\n1.6487212707\n54.5981500331")
@RunTest(intInput = 1, output = "0\n2.08990511144\n1\n2")
public class math00 {

    @Export
    public static void main(String[] args) {
        switch (args.length) {
            case 0:
                Print.putd(Math.exp(1.0f));
//                Print.putd(Math.exp(0.0f));
//                Print.putd(Math.exp(0.5f));
                Print.putd(Math.exp(4.0f));
                break;
            case 1:
//                Print.putd(Math.log(1.0f));
//                Print.putd(Math.log(123.0f));
//                Print.putd(Math.log(10.0f));
//                Print.putd(Math.log(100.0f));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
    }
}
