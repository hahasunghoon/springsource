package com.spring.dependency;

public class TVMain {
//	String str ="String"; // == new

	public static void main(String[] args) {
		
		//�����ڸ� ����� ��� ���� �ʱ�ȭ
//		SonySpeaker speaker = new SonySpeaker();
//		TV tv = new SamsungTV(speaker);
		
//		TV tv = new SamsungTV(new SonySpeaker());
		
		//setter �� ����� ��� ���� �ʱ�ȭ
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
