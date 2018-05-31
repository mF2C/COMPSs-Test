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
import es.bsc.compss.types.annotations.Constraints;
import es.bsc.compss.types.annotations.Parameter;
import es.bsc.compss.types.annotations.parameter.Direction;
import es.bsc.compss.types.annotations.parameter.Type;
import es.bsc.compss.types.annotations.task.Method;


public interface TestItf {

    @Constraints(computingUnits = "1")
    @Method(declaringClass = "es.bsc.compss.test.Test")
    void printValue(
            @Parameter(type = Type.OBJECT, direction = Direction.IN) Data value
    );

    @Constraints(computingUnits = "1")
    @Method(declaringClass = "es.bsc.compss.test.Test")
    void increment(
            @Parameter(type = Type.INT, direction = Direction.IN) int timeStep,
            @Parameter(type = Type.OBJECT, direction = Direction.INOUT) Data value
    );

    @Constraints(computingUnits = "1")
    @Method(declaringClass = "es.bsc.compss.test.Test")
    Data genValue(
            @Parameter(type = Type.INT, direction = Direction.IN) int value,
            @Parameter(type = Type.STRING, direction = Direction.IN) String alias
    );

}
