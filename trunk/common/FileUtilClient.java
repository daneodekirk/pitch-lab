package common;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileUtilClient
{
	private static final String HOST ="128.95.101.226"; //the acoustics lab's g4's IP, (running the pitchlab server)
	private Communicate stub;
	
	public FileUtilClient() 
	{
		
		try 
		{
		    Registry registry = LocateRegistry.getRegistry(HOST);
		    stub = (Communicate) registry.lookup("Communicate");
		   
		    String response = stub.sayHello();
		    System.out.println("response: " + response);
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}

	public FileUtilClient(int mode,String name) 
	{
		
		try 
		{
		    Registry registry = LocateRegistry.getRegistry(HOST);
		    stub = (Communicate) registry.lookup("Communicate");
		    
		    //hand shake (of sorts)
		    String response = stub.sayHello();
		    System.out.println("response: " + response);
		    
		    stub.makeContact( mode, name );
		    
		} 
		catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}

	
    public void appendToLine(String toWrite)
    {
    	try
		{
			stub.appendToLine( toWrite );
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void appendNewLine(String toWrite)
    {
    	try
		{
			stub.appendNewLine( toWrite );
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setAdditionalInfo(String additionalInfo)
    {
    	try
		{
			stub.setAdditionalInfo( additionalInfo );
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setTestSettings(String[] testSettings)
    {
    	try
		{
			stub.setTestSettings( testSettings );
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setClosedTime()
    {
    	try
		{
			stub.setClosedTime();
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean emailResults()
    {
    	try
		{
			return stub.emailResults();
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
    }
	
	
    public static void main(String[] args)
	{
    	new FileUtilClient();
    }	
	
	
}
