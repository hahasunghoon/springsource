package com.spring.basic;

public class TVMain {

	public static void main(String[] args) {
		
//		LgTV lgTV = new LgTV();
//		lgTV.turnOn();
//		lgTV.soundUp();
//		lgTV.soundDown();
//		lgTV.turnOff();
		
		TV samsungTV = new SamsungTV();
		samsungTV.powerOn();
		samsungTV.volumeUp();
		samsungTV.volumeDown();
		samsungTV.powerOff();

	}

}
