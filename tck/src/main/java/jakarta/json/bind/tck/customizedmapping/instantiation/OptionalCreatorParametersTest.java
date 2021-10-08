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
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.OptionalCreatorParamsContainer;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.OptionalStringParamContainer;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.ParameterTypeOptionalContainer;
import jakarta.json.bind.tck.customizedmapping.instantiation.model.RequiredCreatorParamsContainer;

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
    private final Jsonb jsonbOptional = JsonbBuilder.create(new JsonbConfig().withOptionalCreatorParameters(true));

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
    public void testOptionalCreatorParamsSetByConfig() {
        OptionalStringParamContainer unmarshalledObject = jsonbOptional.fromJson("{ }", OptionalStringParamContainer.class);

        if (unmarshalledObject.getParamOne() != null || unmarshalledObject.getParamTwo() != null) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with all optional parameters "
                         + "set by the configuration");
        }
        unmarshalledObject = jsonbOptional.fromJson("{ \"paramOne\" : \"some value\" }", OptionalStringParamContainer.class);

        if (!"some value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != null) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with all optional parameters "
                         + "set by the configuration");
        }
    }

    @Test
    public void testOptionalCreatorParamsSetByCreatorProperty() {
        OptionalCreatorParamsContainer unmarshalledObject = jsonb.fromJson("{ }", OptionalCreatorParamsContainer.class);

        if (unmarshalledObject.getParamOne() != null || unmarshalledObject.getParamTwo() != null) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with all optional parameters "
                         + "set by creator property");
        }
        unmarshalledObject = jsonbOptional.fromJson("{ \"paramOne\" : \"some value\" }", OptionalCreatorParamsContainer.class);

        if (!"some value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != null) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with all optional parameters "
                         + "set by creator property");
        }
    }

    @Test
    public void testOptionalCreatorParametersFromConfigOverrideByAnnotation() {
        try {
            jsonbOptional.fromJson("{ }", RequiredCreatorParamsContainer.class);
            fail("Instantiation of the type should have failed, because required creator parameters were missing");
        } catch (JsonbException ignored) {
            //Everything worked as expected
        }
        try {
            jsonbOptional.fromJson("{ \"paramOne\" : \"some value\" }", RequiredCreatorParamsContainer.class);
            fail("Instantiation of the type should have failed, because required creator parameter was missing");
        } catch (JsonbException ignored) {
            //Everything worked as expected
        }
        RequiredCreatorParamsContainer unmarshalledObject = jsonbOptional.fromJson("{ \"paramOne\" : \"some value\", "
                                                                                           + "\"paramTwo\" : 2 }",
                                                                                   RequiredCreatorParamsContainer.class);

        if (!"some value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != 2) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method");
        }
    }

    @Test
    public void testCreatorParameterWithOptionalType() {
        ParameterTypeOptionalContainer unmarshalledObject = jsonb.fromJson("{ \"paramTwo\" : 1 }",
                                                                           ParameterTypeOptionalContainer.class);

        if (!"no value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != 1) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with parameter of type Optional");
        }

        unmarshalledObject = jsonb.fromJson("{ \"paramOne\" : \"some value\", \"paramTwo\" : 2 }",
                                            ParameterTypeOptionalContainer.class);

        if (!"some value".equals(unmarshalledObject.getParamOne()) || unmarshalledObject.getParamTwo() != 2) {
            fail("Failed to instantiate type using JsonbCreator annotated factory method with parameter of type Optional");
        }
    }

}
