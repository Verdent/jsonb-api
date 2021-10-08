/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id$
 */

package jakarta.json.bind.tck.customizedmapping.instantiation.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbDefaultValue;
import jakarta.json.bind.annotation.JsonbProperty;

public class OptionalPrimitiveParamContainer {

    private final int paramOne;
    private final Integer paramTwo;

    private OptionalPrimitiveParamContainer(int paramOne, Integer paramTwo) {
        this.paramOne = paramOne;
        this.paramTwo = paramTwo;
    }

    @JsonbCreator
    public OptionalPrimitiveParamContainer create(@JsonbProperty("paramOne") @JsonbDefaultValue("123") int paramOne,
                                                  @JsonbProperty("paramTwo") Integer paramTwo) {
        return new OptionalPrimitiveParamContainer(paramOne, paramTwo);
    }

    public int getParamOne() {
        return paramOne;
    }

    public Integer getParamTwo() {
        return paramTwo;
    }
}
