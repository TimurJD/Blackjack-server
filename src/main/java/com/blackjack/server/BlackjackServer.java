package com.blackjack.server;

import static com.blackjack.server.util.ServerProperties.NAME;
import static com.blackjack.server.util.ServerProperties.PORT;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.blackjack.server.exception.EmptyPropertyException;

/**
 * Simple Blackjack socket server
 * @author Timur Berezhnoi
 */
public class BlackjackServer {

	private final Logger logger = Logger.getLogger(this.getClass());

	private Socket socket;
	private ServerSocket server;

	private ObjectInputStream in;
	private ObjectOutputStream out;

	
	/**
	 * The method incapsulates all logic during start up. 
	 * 
	 * @throws IOException
	 * @throws EmptyPropertyException
	 */
	public void startUp() throws IOException, EmptyPropertyException {
		if(PORT.getValue().isEmpty()) {
			throw new EmptyPropertyException("The " + PORT + " have note been seted");
		}
		
		server = new ServerSocket(Integer.valueOf(PORT.getValue()));
		waitForConnection();
		setUpStreams();
	
	}

	private void waitForConnection() throws IOException {
		logger.info("The server is waiting for clients t");
		socket = server.accept();
		logger.info("Connected: " + socket.getInetAddress().getHostName());
	}

	/**
	 * The method set ups the streams.
	 * 
	 * @throws IOException
	 */
	private void setUpStreams() throws IOException {
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		logger.info("OUT_STREAM & INPUT_STREAM initialized!");
	}

	/**
	 * The method receive data from client
	 * 
	 * @return object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object getDataFromClient() throws ClassNotFoundException, IOException {
		return in.readObject();
	}

	/**
	 * The method which send data to a client and flush (see 
	 * {@link java.io.ObjectOutputStream#flush()}) the stream
	 * 
	 * @param object the object to be sended to a client.
	 * @throws IOException - if I/O errors occur while writing to the underlying stream.
	 */
	public void sendDataToClient(Object object) throws IOException {
		out.writeObject(object);
		out.flush();
	}

	/**
	 * Close the server.
	 * @throws IOException
	 */
	public void shutDown() throws IOException {
		server.close();
	}

	/**
	 * Returns the connection state of the socket.
	 * <p>
	 * Note: Closing a socket doesn't clear its connection state, which means
	 * this method will return {@code true} for a closed socket (see
	 * {@link java.net.Socket.isConnected}) if it was successfuly connected
	 * prior to being closed.
	 *
	 * @return true if the socket was successfuly connected to a server
	 */
	public boolean isClientConnected() {
		return socket.isConnected();
	}
	
	@Override
	public String toString() {
		return (String) NAME.getValue();
	}
}