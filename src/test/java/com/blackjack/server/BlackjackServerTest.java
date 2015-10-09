package com.blackjack.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blackjack.server.exception.EmptyPropertyException;

/**
 * @author Timur Berezhnoi
 */
public class BlackjackServerTest {

	private Socket client;
	private ObjectOutputStream clientOut;
	private ObjectInputStream clientIn;

	private BlackjackServer server;

	@Before
	public void setUp() throws UnknownHostException, IOException, InterruptedException {
		server = new BlackjackServer();
		new Thread() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
					client = new Socket("127.0.0.1", 7765);
					clientOut = new ObjectOutputStream(client.getOutputStream());
					clientIn = new ObjectInputStream(client.getInputStream());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		try {
			server.startUp();
		} catch (EmptyPropertyException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testClienConnection() {
		assertTrue(server.isClientConnected());
	}

	@Test
	public void shouldGetMessageFromClient() {
		String expected = "Hello World!";
		try {
			clientOut.writeObject(expected);
			assertEquals(expected, server.getDataFromClient());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldSendMessageToClient() {
		String expected = "Hello World!";
		try {
			server.sendDataToClient(expected);
			assertEquals(expected, clientIn.readObject());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		try {
			server.shutDown();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}