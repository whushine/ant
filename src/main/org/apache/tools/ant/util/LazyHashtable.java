/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Ant", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.tools.ant.util;

import java.util.Hashtable;
import java.util.Enumeration;

/** Hashtable implementation that allows delayed construction
 * of expensive objects
 *
 * All operations that need access to the full list of objects
 * will call initAll() first. Get and put are cheap.
 */
public class LazyHashtable extends Hashtable {
    protected boolean initAllDone=false;

    public LazyHashtable() {
        super();
    }

    /** Used to be part of init. It must be done once - but
     * we delay it until we do need _all_ tasks. Otherwise we
     * just get the tasks that we need, and avoid costly init.
     */
    protected void initAll( ) {
        if( initAllDone ) return;
        initAllDone=true;
    }


    public Enumeration elements() {
        initAll();
        return super.elements();
    }

    public boolean isEmpty() {
        initAll();
        return super.isEmpty();
    }

    public int size() {
        initAll();
        return super.size();
    }

    public boolean contains( Object value ) {
        initAll();
        return super.contains(value );
    }

    public boolean containsValue( Object value ) {
        initAll();
        return super.containsValue( value );
    }

    public Enumeration keys() {
        initAll();
        return super.keys();
    }

    // XXX Unfortunately JDK1.2 adds entrySet(), keySet(), values() -
    // implementing this requires a small hack, we can add it later.
}
