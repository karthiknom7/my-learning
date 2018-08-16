package com.logical.serialization.proxy;

import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Data implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String data;
	
	public Data(String data) {
		this.data =data;
	}
	
	private static class DataProxy implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String dataProxy;
		private static final String PREFIX = "ABC";
		private static final String SUFFIX = "DEFG";
		
		public DataProxy(Data data){
			dataProxy = PREFIX + data.data + SUFFIX;
		}
		
		private Object readResolve() throws InvalidClassException{
			
			if(dataProxy.startsWith(PREFIX) && dataProxy.endsWith(SUFFIX)){
				return new Data(dataProxy.substring(3, dataProxy.length() -4));
			}else{
				throw new InvalidClassException("Data correcpted");
			}
		}
		
	}
	
	private Object writeReplace(){
		return new DataProxy(this);
	}
	
	private void readObject(ObjectInputStream ois) throws InvalidClassException{
		throw new InvalidClassException("Something is fishy");
	}
}
