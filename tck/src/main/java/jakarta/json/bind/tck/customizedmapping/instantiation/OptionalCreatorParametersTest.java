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

package jakarta.json.bind.tck.customizedmapping.instantiation;

import java.lang.invoke.MethodHandles;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.OptionalAllPrimitivesContainer;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.OptionalPrimitiveParamContainer;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.OptionalStringParamContainer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class OptionalCreatorParametersTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, MethodHandles.lookup().lookupClass().getPackage().getName());
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void testCreatorMethodWithOptionalParameter() {
        OptionalStringParamContainer unmarshalledObject = jsonb.fromJson("{ \"paramTwo\" : 1 }",
                                                                         OptionalStringParamContainer.class);

        if (unmarshalledObject.getParamOne() != null || unmarshalledObject.getParamTwo() != 1) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with optional parameter");
        }
        unmarshalledObject = jsonb.fromJson("{ \"paramOne\" : \"some value\", \"paramTwo\" : 2 }",
                                            OptionalStringParamContainer.class);

        if (!"some value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != 2) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with optional parameter");
        }
    }

    @Test
    public void testCreatorMethodWithMissingRequiredParameter() {
        try {
            jsonb.fromJson("{ \"paramOne\" : \"some value\" }", OptionalStringParamContainer.class);
            fail("Instantiation of the type should have failed, because required creator parameter was missing");
        } catch (JsonbException ignored) {
            //Everything worked as expected
        }
    }

    @Test
    public void testCreatorWithOptionalPrimitiveParameter() {
        OptionalPrimitiveParamContainer unmarshalledObject = jsonb.fromJson("{ \"paramTwo\" : 456 }",
                                                                            OptionalPrimitiveParamContainer.class);

        if (unmarshalledObject.getParamOne() != 123 || unmarshalledObject.getParamTwo() != 456) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with "
                         + "optional primitive type parameter");
        }
        unmarshalledObject = jsonb.fromJson("{ \"paramOne\" : 789, \"paramTwo\" : 456 }",
                                            OptionalPrimitiveParamContainer.class);

        if (unmarshalledObject.getParamOne() != 789 || unmarshalledObject.getParamTwo() != 456) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with "
                         + "optional primitive type parameter");
        }
    }

    @Test
    public void testOptionalPrimitiveTypes() {
        OptionalAllPrimitivesContainer unmarshalledObject = jsonb.fromJson("{ }", OptionalAllPrimitivesContainer.class);
        if (unmarshalledObject.getByteValue() != (byte) 1
                || unmarshalledObject.getShortValue() != (short) 2
                || unmarshalledObject.getIntValue() != 3
                || unmarshalledObject.getLongValue() != 4L
                || unmarshalledObject.getFloatValue() != 5.0F
                || unmarshalledObject.getDoubleValue() != 6.0
                || !unmarshalledObject.getBooleanValue()
                || unmarshalledObject.getCharValue() != 'c') {
            fail("Failed to instantiate type using JsonbCreator annotated constructor and with all "
                         + "optional primitive type parameters");
        }
    }

}
