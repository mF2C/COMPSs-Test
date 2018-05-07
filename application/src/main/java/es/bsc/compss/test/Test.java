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


public class Test {

    public static void main(String[] args) {
        int iters = Integer.parseInt(args[0]);
        System.out.println("Received a service invocation creating " + iters + " tasks.");
        for (int i = 0; i < iters; i++) {
            test(i + 1);
        }
    }

    public static void test(int timeStep) {
        try {
            Thread.sleep(1000 * timeStep);
        } catch (Exception e) {
        }
        System.out.println("Executing task " + timeStep + " which lasts " + (1000 * timeStep) + " ms.");
    }

}
