/*         
 *  Copyright 2002-2018 Barcelona Supercomputing Center (www.bsc.es)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package es.bsc.compss.test;

import datamodel.Data;


public class Test {

    public static void registerValue(String alias, int value) {
        System.out.println("Registering Data " + value + " with alias " + alias);
        try {
            Data d = new Data(value);
            d.makePersistent(alias);
            System.out.println("registered data with ID: " + d.getID());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(int iters, Data value) {
        System.out.println("Received a service invocation creating " + iters + " tasks with initial value " + value.getValue());

        for (int i = 0; i < iters; i++) {
            increment(i + 1, value);
        }

        System.out.println("Final value of the variable: " + value.getValue());

    }

    public static void wholeTest(int iters, int value) {
        Data d = genValue(value, "data" + System.currentTimeMillis());

        for (int i = 0; i < iters; i++) {
            for (int j = 0; j < 4; j++) {
                printValue(d);
            }
            increment(i + 1, d);
        }

        printValue(d);

    }

    public static Data genValue(int value, String alias) {
        Data d = new Data(value);
        d.makePersistent(alias);
        return d;
    }

    public static void persist(int value, String alias, Data result) {
        Data d = new Data(value);
        d.makePersistent(alias);
    }

    public static void printValue(Data value) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("Printing Data value:" + value.getValue());
    }

    public static void increment(int timeStep, Data value) {
        try {
            value.setValue(value.getValue() + 1);
            Thread.sleep(1000 * timeStep);
        } catch (Exception e) {
        }
        System.out.println("Executed task " + timeStep + " which lasts " + (1000 * timeStep) + " ms.");
    }

}
