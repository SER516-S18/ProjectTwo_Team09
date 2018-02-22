package app.client;

import java.util.Observable;

/*
 * Class to keep looking for console message 
 * and pass it for printing whenever we get it.
 */

public class ConsoleData extends Observable {
private String messageForConsole;

public String getMessageForConsole() {
	return messageForConsole;
}

public void setMessageForConsole(String messageForConsole) {
	this.messageForConsole = messageForConsole;
	setChanged();
}
 
}
