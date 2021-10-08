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

package jakarta.json.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defining default value of the creator parameters.
 * <br>
 * This annotation marks parameter as optional and is usable only on the primitive type parameters,
 * their wrapping types and {@link String}.
 * An exception will be thrown if present on any other parameter type.
 * <br>
 * Value property has to be convertable to the parameter type it is added to.
 * <pre>{@code
 * //Valid
 * @JsonbCreator
 * public SomeClass creator(@JsonbParameter("parameter") @JsonbDefaultValue("1") int parameter){}
 *
 * //Invalid
 * @JsonbCreator
 * public SomeClass creator(@JsonbParameter("parameter") @JsonbDefaultValue("false") int parameter){}
 * }</pre>
 */
@JsonbAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface JsonbDefaultValue {

    /**
     * Default value of the parameter. Value has to be compatible with the parameter type.
     *
     * @return default parameter value
     */
    String value();

}
