package Listeners;

import java.util.concurrent.Semaphore;

public class Lock extends Semaphore{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6405442837430673720L;
	
	private static Lock sInstance;
	
	private Lock(){
		super(1);
	}
	
	public static Lock getLock(){
		if(sInstance == null){
			sInstance = new Lock();
		}
		return sInstance;
	}
}
