package app.client;

public class ConsoleDataTesting {
public static void main(String[] args) {
	ConsoleData consoleObj=new ConsoleData();
	consoleObj.setMessageForConsole("Server started ........");
	ConsoleObserver observerObj=new ConsoleObserver();
	consoleObj.addObserver(observerObj);
	consoleObj.notifyObservers();
}
}
