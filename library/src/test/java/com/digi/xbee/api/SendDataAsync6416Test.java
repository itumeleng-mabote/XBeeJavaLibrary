/**
 * Copyright (c) 2014-2017 Digi International Inc.,
 * All rights not expressly granted are reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 * =======================================================================
 */
package com.digi.xbee.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.digi.xbee.api.connection.serial.SerialPortRxTx;
import com.digi.xbee.api.exceptions.InterfaceNotOpenException;
import com.digi.xbee.api.exceptions.InvalidOperatingModeException;
import com.digi.xbee.api.exceptions.OperationNotSupportedException;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.TransmitException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.common.TransmitPacket;

@RunWith(PowerMockRunner.class)
@PrepareForTest({XBeeDevice.class})
public class SendDataAsync6416Test {
	
	// Constants.
	private static final XBee64BitAddress XBEE_64BIT_ADDRESS = new XBee64BitAddress("0123456789ABCDEF");
	private static final XBee16BitAddress XBEE_16BIT_ADDRESS = new XBee16BitAddress("0123");
	
	private static final String DATA = "data";
	
	// Variables.
	private XBeeDevice xbeeDevice;
	
	private TransmitPacket transmitPacket;
	
	@Before
	public void setup() throws Exception {
		// Instantiate an XBeeDevice object with a mocked interface.
		xbeeDevice = PowerMockito.spy(new XBeeDevice(Mockito.mock(SerialPortRxTx.class)));
		
		// Mock Transmit Request packet.
		transmitPacket = Mockito.mock(TransmitPacket.class);
		
		// Whenever a TransmitPacket class is instantiated, the mocked transmitPacket packet should be returned.
		PowerMockito.whenNew(TransmitPacket.class).withAnyArguments().thenReturn(transmitPacket);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the 64-Bit address is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendDataAsync64BitAddrNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendDataAsync(null, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the 16-Bit address is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendDataAsync16BitAddrNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, null, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the data is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendDataAsyncDataNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, null);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the sender is a remote XBee device.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=OperationNotSupportedException.class)
	public void testSendDataAsyncFromRemoteDevices() throws TimeoutException, XBeeException {
		// Return that the XBee device is remote when asked.
		Mockito.when(xbeeDevice.isRemote()).thenReturn(true);
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the device is not open.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=InterfaceNotOpenException.class)
	public void testSendDataAsyncConnectionClosed() throws TimeoutException, XBeeException {
		// Throw an Interface not open exception when sending and checking any Transmit packet.
		Mockito.doThrow(new InterfaceNotOpenException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if the device has an invalid operating mode.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=InvalidOperatingModeException.class)
	public void testSendDataAsyncInvalidOperatingMode() throws TimeoutException, XBeeException {
		// Throw an invalid operating mode exception when sending and checking any Transmit packet.
		Mockito.doThrow(new InvalidOperatingModeException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if there is a timeout sending and checking the Transmit packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=TimeoutException.class)
	public void testSendDataAsyncTimeout() throws TimeoutException, XBeeException {
		// Throw a timeout exception when sending and checking any Transmit packet.
		Mockito.doThrow(new TimeoutException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if there is a transmit exception when sending and checking the Transmit packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=TransmitException.class)
	public void testSendDataAsyncTransmitException() throws TimeoutException, XBeeException {
		// Throw a transmit exception when sending and checking any Transmit packet.
		Mockito.doThrow(new TransmitException(null)).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data cannot be sent if there is an IO error when sending and checking the Transmit packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=XBeeException.class)
	public void testSendDataAsyncIOException() throws TimeoutException, XBeeException {
		// Throw an XBee exception when sending and checking any Transmit packet.
		Mockito.doThrow(new XBeeException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, XBee16BitAddress, byte[])}.
	 * 
	 * <p>Verify that async. data is sent successfully if there is not any error when sending and checking the Transmit packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendDataAsyncSuccess() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any Transmit packet.
		Mockito.doNothing().when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		xbeeDevice.sendDataAsync(XBEE_64BIT_ADDRESS, XBEE_16BIT_ADDRESS, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(transmitPacket), Mockito.eq(true));
	}
}
