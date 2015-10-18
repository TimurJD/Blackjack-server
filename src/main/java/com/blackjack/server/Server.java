package com.blackjack.server;

import java.io.IOException;

import com.blackjack.server.exception.EmptyPropertyException;

/**
 * @author Timur Berezhnoi
 */
public interface Server<T1, T2> {
	void startUp() throws IOException, EmptyPropertyException;
	T2 getDataFromClient() throws ClassNotFoundException, IOException;
	void sendDataToClient(T1 t1) throws IOException;
	void shutDown() throws IOException;
	boolean isClientConnected();
}
