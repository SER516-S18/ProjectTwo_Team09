package app.client;

import java.util.Observable;

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
