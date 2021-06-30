/*
 * Copyright (c) 2017, 2021 Oracle and/or its affiliates. All rights reserved.
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

package jakarta.json.bind.tck.defaultmapping.specifictypes.model;

import java.util.OptionalInt;

import jakarta.json.bind.tck.TypeContainer;

public class OptionalIntContainer implements TypeContainer<OptionalInt> {
    private OptionalInt instance;

    @Override
    public OptionalInt getInstance() {
        return instance;
    }

    @Override
    public void setInstance(OptionalInt instance) {
        this.instance = instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionalIntContainer)) {
            return false;
        }

        OptionalIntContainer that = (OptionalIntContainer) o;

        return instance != null ? instance.equals(that.instance)
                : that.instance == null;
    }

    @Override
    public int hashCode() {
        return instance != null ? instance.hashCode() : 0;
    }
}
