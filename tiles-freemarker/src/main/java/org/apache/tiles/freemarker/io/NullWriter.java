/*
 * $Id: ServletUtil.java 751886 2009-03-09 22:39:50Z apetrelli $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.tiles.freemarker.io;

import java.io.IOException;
import java.io.Writer;

/**
 * A writer that does not write anything.
 * 
 * @version $Rev: 751886 $ $Date: 2009-03-09 23:39:50 +0100 (lun, 09 mar 2009) $
 * @since 2.2.0
 */
public class NullWriter extends Writer {

    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        // Does nothing
    }

    /** {@inheritDoc} */
    @Override
    public void flush() throws IOException {
        // Does nothing
    }

    /** {@inheritDoc} */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // Does nothing
    }
}