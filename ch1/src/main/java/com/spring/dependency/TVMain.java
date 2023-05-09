package com.spring.dependency;

public class TVMain {
//	String str ="String"; // == new

	public static void main(String[] args) {
		
		//생성자를 사용한 멤버 변수 초기화
//		SonySpeaker speaker = new SonySpeaker();
//		TV tv = new SamsungTV(speaker);
		
//		TV tv = new SamsungTV(new SonySpeaker());
		
		//setter 를 사용한 멤버 변수 초기화
		SamsungTV tv = new SamsungTV();
		tv.setSpeaker(new SonySpeaker());
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
//		 TVMain obj = new TVMain();
//		 obj.test();
	}
//       public void test() {
//    	   System.out.println(str);
//    	   System.out.println(str.length());
    	   
//       }
}
