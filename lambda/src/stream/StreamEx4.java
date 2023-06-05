package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//��������
// collect()
public class StreamEx4 {

	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("ȫ�浿",99));
		stuList.add(new Student("��浿",89));
		stuList.add(new Student("��浿",79));
		stuList.add(new Student("�ڱ浿",69));
		
		//���ο� ����Ʈ�� �л����� ������ �����ϰ� ���
		List<Integer> jumsuList = stuList.stream().map(s -> s.getJumsu()).collect(Collectors.toList());
		System.out.println(jumsuList);
		
		//���ο� ����Ʈ��
		List<String> fruits = Arrays.asList("melon", "apple", "banana", "grape");
		//                                                                  .map(String::toUpperCase)
		List<String> uppers = fruits.stream().map(s ->s.toUpperCase()).collect(Collectors.toList());
		System.out.println(uppers);

	}

}
