package com.designpatterns.creational.builder;

public class MyComputer {

	private String ram;
	private String hdd;
	
	// optional
	private boolean isGraphicCardEnabled;
	private boolean  isBluetoothEnabled;
	
	public String getRam() {
		return ram;
	}
	public String getHdd() {
		return hdd;
	}
	public boolean isGraphicCardEnabled() {
		return isGraphicCardEnabled;
	}
	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	private MyComputer(MyComputerBuilder builder){
		this.hdd = builder.hdd;
		this.ram = builder.ram;
		this.isBluetoothEnabled = builder.isBluetoothEnabled;
		this.isGraphicCardEnabled = builder.isGraphicCardEnabled;
	}
	
	public static class MyComputerBuilder{
		private String ram;
		private String hdd;
		
		// optional
		private boolean isGraphicCardEnabled;
		private boolean  isBluetoothEnabled;
		
		public MyComputerBuilder(String ram, String hdd) {
			this.ram = ram;
			this.hdd = hdd;
		}
		
		public MyComputerBuilder setisGraphicCardEnabled(boolean enabled){
			this.isGraphicCardEnabled = enabled;
			return this;
		}
		
		public MyComputerBuilder setisBluetoothEnabled(boolean enabled){
			this.isBluetoothEnabled = enabled;
			
			return this;
		}
		
		public MyComputer build(){
			return new MyComputer(this);
		}
		
	}

	@Override
	public String toString() {
		return "MyComputer [ram=" + ram + ", hdd=" + hdd + ", isGraphicCardEnabled=" + isGraphicCardEnabled
				+ ", isBluetoothEnabled=" + isBluetoothEnabled + "]";
	}
	
	
}
