package app.client;

import java.util.Observable;
import java.util.Observer;

public class ConsoleObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// casting the observable object to consoledata obj
		ConsoleData consoleDataObject=(ConsoleData)o;
		System.out.println(consoleDataObject.getMessageForConsole());
	}

}
