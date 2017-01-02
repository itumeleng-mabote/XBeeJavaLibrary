/**
 * Copyright (c) 2015-2017 Digi International Inc.,
 * All rights not expressly granted are reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 * =======================================================================
 */
package com.digi.xbee.api.exceptions;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionExceptionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException()}.
	 */
	@Test
	public final void testCreateConnectionExceptionDefault() {
		// Setup the resources for the test.
		
		// Call the method under test.
		ConnectionException e = new ConnectionException();
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(nullValue(String.class)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(nullValue(Throwable.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(Throwable))}.
	 */
	@Test
	public final void testCreateConnectionExceptionCause() {
		// Setup the resources for the test.
		Throwable cause = new Exception();
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(cause.toString())));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(equalTo(cause)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(Throwable)}.
	 */
	@Test
	public final void testCreateConnectionExceptionCauseNull() {
		// Setup the resources for the test.
		Throwable cause = null;
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(nullValue(String.class)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(nullValue(Throwable.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String)}.
	 */
	@Test
	public final void testCreateConnectionExceptionMessage() {
		// Setup the resources for the test.
		String message = "This is the message";
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(nullValue(Throwable.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String)}.
	 */
	@Test
	public final void testCreateConnectionExceptionMessageNull() {
		// Setup the resources for the test.
		String message = null;
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(nullValue(Throwable.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String, Throwable)}.
	 */
	@Test
	public final void testCreateConnectionExceptionMessageAndCause() {
		// Setup the resources for the test.
		String message = "This is the message";
		Throwable cause = new Exception();
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message, cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(equalTo(cause)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String, Throwable)}.
	 */
	@Test
	public final void testConnectionExceptionMessageNullAndCause() {
		// Setup the resources for the test.
		String message = null;
		Throwable cause = new Exception();
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message, cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(equalTo(cause)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String, Throwable)}.
	 */
	@Test
	public final void testCreateConnectionExceptionMessageAndCauseNull() {
		// Setup the resources for the test.
		String message = "This is the message";
		Throwable cause = null;
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message, cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(equalTo(cause)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#ConnectionException(String, Throwable)}.
	 */
	@Test
	public final void testCreateConnectionExceptionMessageAndCauseBothNull() {
		// Setup the resources for the test.
		String message = null;
		Throwable cause = null;
		
		// Call the method under test.
		ConnectionException e = new ConnectionException(message, cause);
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected message", 
				e.getMessage(), is(equalTo(message)));
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				e.getCause(), is(equalTo(cause)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.exceptions.ConnectionException#getCause()}.
	 */
	@Test
	public final void testGetCause() {
		// Setup the resources for the test.
		Throwable cause = new Exception();
		ConnectionException e = new ConnectionException();
		e.initCause(cause);
		
		// Call the method under test.
		Throwable result = e.getCause();
		
		// Verify the result.
		assertThat("Created 'ConnectionException' does not have the expected cause", 
				result, is(equalTo(cause)));
	}
}
