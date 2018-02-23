package app.server;
/* Entire exception from server is handled here */
public class ServerException {
	
	ServerView view = null;
	

	public void getView() {
		this.view = ServerController.getView();
	}
	ServerException(String exception)
	{
		if(view == null) {
			getView();
		}
		if(exception == "NumberFormatException")
		{
			view.log("Exception: Please enter valid number in the textbox");
		}
		if(exception == "Min higher than Max")
		{
			view.log("Exception:The highest value must be greater than or equal to the lowest value.");
		}
		else
		{
			view.log(exception);
		}
	}

}
