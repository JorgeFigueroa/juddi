/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
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
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "jUDDI" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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
package org.apache.juddi.proxy;

import junit.framework.*;

/**
 * @author Steve Viens (sviens@users.sourceforge.net)
 */
public class PublishTest extends TestCase
{
  public PublishTest(String testName)
  {
      super(testName);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite();

//    suite.addTest(AddPublisherAssertionsTest.suite());
//    suite.addTest(DeleteBindingTest.suite());
//    suite.addTest(DeleteBusinessTest.suite());
//    suite.addTest(DeletePublisherAssertionTest.suite());
//    suite.addTest(DeleteServiceTest.suite());
//    suite.addTest(DeleteTModelTest.suite());
//    suite.addTest(DiscardAuthTokenTest.suite());
//    suite.addTest(GetAssertionStatusReport.suite());
//    suite.addTest(GetAuthTokenTest.suite());
//    suite.addTest(GetPublisherAssertionsTest.suite());
//    suite.addTest(GetRegisteredInfoTest.suite());
//    suite.addTest(SaveBindingTest.suite());
//    suite.addTest(SaveBusinessTest.suite());
//    suite.addTest(SaveServiceTest.suite());
//    suite.addTest(SaveTModelTest.suite());
//    suite.addTest(SetPublisherAssertionsTest.suite());

    return suite;
  }

  public static void main(String args[])
  {
    String[] testCaseName = { PublishTest.class.getName() };
    junit.textui.TestRunner.main(testCaseName);
  }
}