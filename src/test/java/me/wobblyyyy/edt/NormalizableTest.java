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

package me.wobblyyyy.edt;

import me.wobblyyyy.edt.functional.Normalizable;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NormalizableTest {
    public double get1() {
        return 1;
    }

    public double get2() {
        return 2;
    }

    public double get3() {
        return 3;
    }

    public double get10() {
        return 10;
    }

    public double get20() {
        return 20;
    }

    public double get30() {
        return 30;
    }

    @Test
    public void testStuff() {
        Normalizable map = new Normalizable() {{
            add(NormalizableTest.this::get10, NormalizableTest.this::get1);
            add(NormalizableTest.this::get20, NormalizableTest.this::get2);
            add(NormalizableTest.this::get30, NormalizableTest.this::get3);
        }};

        DynamicArray<Double> array = new DynamicArray<>(map.normalize());

        array.itr().forEach((Consumer<Double>) System.out::println);
    }

    public double frV() {
        return -1600;
    }

    public double flV() {
        return -1640;
    }

    public double brV() {
        return 1580;
    }

    public double blV() {
        return 1590;
    }

    public double power() {
        return 1;
    }

    @Test
    public void testWithV() {
        Normalizable map = new Normalizable() {{
            add(NormalizableTest.this::frV, NormalizableTest.this::power);
            add(NormalizableTest.this::flV, NormalizableTest.this::power);
            add(NormalizableTest.this::brV, NormalizableTest.this::power);
            add(NormalizableTest.this::blV, NormalizableTest.this::power);
        }};

//        DynamicArray<Double> array = new DynamicArray<>(map.normalize());

        DynamicArray<Supplier<Double>> sKeys = new DynamicArray<>() {{
            add(NormalizableTest.this::frV);
            add(NormalizableTest.this::flV);
            add(NormalizableTest.this::brV);
            add(NormalizableTest.this::blV);
        }};
        DynamicArray<Supplier<Double>> sValues = new DynamicArray<>() {{
            add(NormalizableTest.this::power);
            add(NormalizableTest.this::power);
            add(NormalizableTest.this::power);
            add(NormalizableTest.this::power);
        }};
        DynamicArray<Double> array =
                Normalizable.normalizeSuppliers(sKeys, sValues);

        array.itr().forEach((Consumer<Double>) System.out::println);
    }
}
