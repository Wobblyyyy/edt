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

import me.wobblyyyy.edt.StaticArray;
import me.wobblyyyy.edt.functional.Analyzable;
import me.wobblyyyy.edt.functional.Normalizable;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Example class showing how normalization works.
 *
 * @author Colin Robertson
 */
public class Normalize {
    /**
     * Get velocities. Each of the methods in this array is a functional
     * interface that allows the method to be a supplier.
     */
    public static class Velocity {
        private static final double target = 2000;
        private static double spread = target / 3;

        public static double getRandomInRange() {
            double random = Math.random();

            return target + (random * spread);
        }

        public static double fr() {
            return getRandomInRange();
        }

        public static double fl() {
            return getRandomInRange();
        }

        public static double br() {
            return getRandomInRange();
        }

        public static double bl() {
            return getRandomInRange();
        }

        public static void setVelocitySpread(double spread) {
            Velocity.spread = spread;
        }
    }

    /**
     * Get powers. Each of the methods in this array is a functional
     * interface that allows the method to be a supplier.
     */
    public static class Power {
        private static double power = 1.0;

        public static double fr() {
            return power;
        }

        public static double fl() {
            return power;
        }

        public static double br() {
            return power;
        }

        public static double bl() {
            return power;
        }

        public static void setPower(double power) {
            Power.power = power;
        }
    }

    /**
     * Normalize a set of powers based on velocities. Because this method
     * uses suppliers instead of hard-coded arrays, iteration and repition
     * is incredibly easy.
     *
     * <p>
     * In a real-world situation, this type of supplier model could be used
     * on a separate thread to set power values based on velocities without
     * any user input.
     * </p>
     */
    @Test
    public void normalizePowerBasedOnVelocity() {
        StaticArray<Supplier<Double>> powerSuppliers = new StaticArray<>(
                Power::fr,
                Power::fl,
                Power::br,
                Power::bl
        );
        StaticArray<Supplier<Double>> velocitySuppliers = new StaticArray<>(
                Velocity::fr,
                Velocity::fl,
                Velocity::br,
                Velocity::bl
        );

        for (int i = 0; i < 10; i++) {
            Analyzable<Double> normalized = Normalizable.normalizeSuppliers(
                    velocitySuppliers,
                    powerSuppliers
            );

            normalized.itr().forEach((Consumer<Double>) System.out::println);

            System.out.println("");
        }
    }
}
