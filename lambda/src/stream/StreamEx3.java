package stream;

import java.util.Arrays;
import java.util.List;

public class StreamEx3 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("���", "����", "�ٳ���", "���", "��", "����");
		
		//���� �ΰ��� ��Ҵ� �ǳʶٱ�, ����� 3����
		list.stream().skip(2).limit(3).forEach(System.out::println);

	}

}
