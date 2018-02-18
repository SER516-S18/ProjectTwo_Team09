package app.client;

import java.util.Observable;
import java.util.Observer;

public class ConsoleObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		ConsoleData consoleDataObject=(ConsoleData)o;
		System.out.println(consoleDataObject.getMessageForConsole());
	}

}
