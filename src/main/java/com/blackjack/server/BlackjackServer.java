package com.blackjack.server;

import static com.blackjack.server.util.ServerProperties.NAME;
import static com.blackjack.server.util.ServerProperties.PORT;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;

import com.blackjack.model.GameStatus;
import com.blackjack.server.exception.EmptyPropertyException;

/**
 * Simple Blackjack socket server
 * @author Timur Berezhnoi
 */
public class BlackjackServer implements Server<Map<String, Object>, GameStatus> { // TODO Think about change to: BlackjackServer<T1, T2> implements Server<T1, T2>

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
	@Override
	public void startUp() throws IOException, EmptyPropertyException {
		if(PORT.getValue().isEmpty()) {
			throw new EmptyPropertyException("The " + PORT + " have note been seted");
		}
		
		server = new ServerSocket(Integer.valueOf(PORT.getValue()));
		waitForConnection();
		setUpStreams();
	
	}

	private void waitForConnection() throws IOException {
		logger.info("The server is waiting for clients");
		socket = server.accept();
		socket.setKeepAlive(true);
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
	@Override
	public GameStatus getDataFromClient() throws ClassNotFoundException, IOException {
		return (GameStatus) in.readObject();
	}

	/**
	 * The method which send data to a client and flush (see 
	 * {@link java.io.ObjectOutputStream#flush()}) the stream
	 * 
	 * @param object the object to be sended to a client.
	 * @throws IOException - if I/O errors occur while writing to the underlying stream.
	 */
	@Override
	public void sendDataToClient(Map<String, Object> object) throws IOException { // Also think abut this: What if i write methods to sed hand, and status?
		out.writeObject(object);
		out.flush();
		out.reset();
	}

	/**
	 * Close the server.
	 * @throws IOException
	 */
	@Override
	public void shutDown() throws IOException {
		server.close();
	}

	/**
	 * Returns the connection state of the socket.
	 * <p>
	 * @return true if the socket was successfuly connected to a server
	 */
	@Override
	public boolean isClientConnected() {
		return socket.isConnected();
	}
	
	@Override
	public String toString() {
		return NAME.getValue();
	}
}