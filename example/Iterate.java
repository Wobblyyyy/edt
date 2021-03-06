/*
 * Copyright (c) 2021 Colin Robertson (wobblyyyy@gmail.com)
 *
 * This file is a part of the EDT (Extended Data Types) project. This project
 * is available on GitHub at:
 * https://github.com/Wobblyyyy/edt
 *
 * For more information on the data structures and types included in this
 * library, check out the online documentation for this project - all of which
 * is available via GitHub.
 *
 * All files in this project are licensed under the MIT license, meaning you're
 * free to distribute and modify code included in this project however you
 * see fit. For more information on the licensing behind this project, check
 * out the license file in the root directory of this project.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

import me.wobblyyyy.edt.DynamicArray;
import me.wobblyyyy.edt.DynamicMap;
import me.wobblyyyy.edt.TrioMap;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Example methods for iterating over several data types.
 *
 * @author Colin Robertson
 */
public class Iterate {
    /**
     * Iterate over an {@link me.wobblyyyy.edt.Arrayable} object. For each
     * of the strings added to the array, print the string's value to the
     * console.
     */
    @Test
    public void iterateArray() {
        /*
         * Create the array - very exciting!
         */
        DynamicArray<String> array = new DynamicArray<>(
                "String 1",
                "String 2",
                "String 3"
        );

        /*
         * Iterate over each of the elements in the array.
         */
        array.itr().forEach((Consumer<String>) System.out::println);
    }

    /**
     * Iterate over an entire {@link me.wobblyyyy.edt.Mappable} instance. For
     * each of the key/value pairs in the array, print the key and value
     * to the system's console.
     */
    @Test
    public void iterateMap() {
        /*
         * Create a new map and add some objects.
         */
        DynamicMap<String, String> map = new DynamicMap<>() {{
            put("Key 1", "Value 1");
            put("Key 2", "Value 2");
            put("Key 3", "Value 3");
        }};

        /*
         * For each of the items in the map, print the key and value.
         */
        map.itr().forEach((key, value) -> {
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);
        });
    }

    /**
     * Iterate over an entire {@link TrioMap} instance and do very cool things.
     * Not really - there aren't too many cool things to do here. But saying
     * it's cool makes it cooler, of course.
     */
    @Test
    public void iterateTrio() {
        /*
         * Create a new TrioMap that will be iterated over.
         */
        TrioMap<String, String, String> trio = new TrioMap<>() {{
            add("String 1 A", "String 1 B", "String 1 C");
            add("String 2 A", "String 2 B", "String 2 C");
            add("String 3 A", "String 3 B", "String 3 C");
            add("String 4 A", "String 4 B", "String 4 C");
            add("String 5 A", "String 5 B", "String 5 C");
        }};

        /*
         * Iterate over the newly-created map. Print the A, B, and C values
         * for each of the trio elements stored in the map.
         */
        trio.itr().forEach((a, b, c) -> {
            System.out.println("A: " + a);
            System.out.println("B: " + b);
            System.out.println("C: " + c);
        });
    }
}
