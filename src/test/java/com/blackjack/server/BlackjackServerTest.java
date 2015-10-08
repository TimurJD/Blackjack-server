package com.blackjack.server;

import org.junit.Before;

/**
 * @author Timur Berezhnoi
 */
public class BlackjackServerTest {
	
	private BlackjackServer server;
	
	@Before
	public void setUp() {
		server = new BlackjackServer();
	}
	
	@Test
	public void doSmth() {
		
	}
}

//package cscie160.lecture7;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
// 
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
// 
//import org.junit.Before;
//import org.junit.Test;
// 
//public class EchoServerTest {
// 
//    private static final String TEST_HOSTNAME = "localhost";
//    private static final int TEST_PORT = 8192;
//    EchoServer echoServer = null;
// 
//    @Before
//    public void initialize() {
//        echoServer = new EchoServer();
//    }
// 
//    @Test
//    public void testClientConnects() {
//        Socket testSocket = null;
//        try {
//            testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
// 
//            assertTrue(echoServer.clientConnect());
//        } catch (Exception e) {
//            assertTrue(false);
//        }
// 
//        echoServer.serverDisconnect();
//        try {
//            testSocket.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
// 
//    @Test
//    public void testClientDisconnect() {
//        Socket testSocket = null;
//        try {
//            testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
//            echoServer.clientConnect();
//            echoServer.clientDisconnect();
//            Scanner testScanner = new Scanner(testSocket.getInputStream());
//            testScanner.nextLine();
//            assertTrue(false);
//        } catch (Exception e) {
//        }
// 
//        try {
//            testSocket.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
// 
//    @Test
//    public void testServerDisconnect() {
//        Socket testSocket = null;
//        try {
//            testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
//            echoServer.clientConnect();
//            echoServer.serverDisconnect();
//            Scanner testScanner = new Scanner(testSocket.getInputStream());
//            testScanner.nextLine();
//            assertTrue(false);
//        } catch (Exception e) {
//        }
// 
//        try {
//            testSocket.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
// 
//    @Test
//    public void testClientMessage() {
//        Socket testSocket = null;
//        String testMessage = "Test Message";
//        try {
//            testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
//            echoServer.clientConnect();
//            PrintWriter testWriter = new PrintWriter(
//                    testSocket.getOutputStream(), true);
//            testWriter.println(testMessage);
//            String resultString = echoServer.getMessage();
//            assertEquals(testMessage, resultString);
//        } catch (Exception e) {
//            assertTrue(false);
//        }
// 
//        try {
//            testSocket.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
// 
//    @Test
//    public void testClientMessageBye() {
//        Socket testSocket = null;
//        String testMessage = "Bye";
//        try {
//            testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
//            echoServer.clientConnect();
//            PrintWriter testWriter = new PrintWriter(
//                    testSocket.getOutputStream(), true);
//            testWriter.println(testMessage);
//            echoServer.getMessage();
//            Scanner testScanner = new Scanner(testSocket.getInputStream());
//            testScanner.nextLine();
//            assertTrue(false);
//        } catch (Exception e) {
//        }
// 
//        try {
//            testSocket.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}