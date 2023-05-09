package com.spring.dependency;

public class SamsungTV implements TV {
	
	private Speaker speaker; // has-a(����) ����
	
	//�Ű������� ���� �ʴ� ������ : default ������
	public SamsungTV() {
		System.out.println("SamsungTV �ν��Ͻ� ���� - default ������");
	}
	
	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker;  //��� ���� �ʱ�ȭ
		System.out.println("SamsungTV �ν��Ͻ� ���� - ���� ������");
	}
	
	//set ��Ʈ�ѽ����̽��� ����Ŀ
	public void setSpeaker(SonySpeaker speaker) {
		this.speaker = speaker;
	}
	

	@Override
	public void powerOn() {
		System.out.println("SamsungTV - �Ŀ� On");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - �Ŀ� Off");
	}
	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV - ���� Up");
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV - ���� Down");
		speaker.volumeDown();
	}

}
