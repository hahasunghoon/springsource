package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx2 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("�ٵ�","�ٳ���","����","����","����","������");
		
		// "�ٷ� " �����ϴ� ��Ҹ� ���ο� ����Ʈ�� �߰� �� ���
		List<String> list1 = new ArrayList<String>();
		for (String str: list) {
			if(str.startsWith("��")) {
				list1.add(str);
			}
		}
		for (String str : list1) {
			System.out.println(str);
		}
		
		//filter() : ��Ʈ�� ��� �ɷ�����
		list.stream().filter(str -> str.startsWith("��")).forEach(System.out::println);
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("ȫ�浿",99));
		stuList.add(new Student("��浿",89));
		stuList.add(new Student("��浿",79));
		stuList.add(new Student("�ڱ浿",69));
		
		//�̸��� ������ ������ �����ϴ� �л����� �̸� ���
		for (Student student : stuList) {
			if(student.getName().startsWith("��")) {
				System.out.println(student.getName());
			}
		}

		stuList.stream().filter(stu ->stu.getName().startsWith("��"))
		//.forEach(s -> System.out::println(s.getName()));
		.forEach(System.out::println);
		
		//distinct() : �ߺ�����
		List<String> list2 = Arrays.asList("�ٵ�","�ٳ���","����","����","����","�ٵ�");
		list2.stream().distinct().forEach(System.out::println);
		
		// Arrays.asList(...) : array => list
		// Stream.of(....) : array => stream
		Stream<File> stream = Stream.of(new File("e:\\test1.txt"), new File("e:\\test2.txt"), new File("e:\\test3.txt"),
				new File("e:\\test1.java"), new File("e:\\test1.bak"), new File("e:\\test2"));
		
		//���� Ȯ���� ������ �� �ߺ��� �����ϰ� ����ϱ�
		// ���ϸ� ���� => Ȯ���� ���� => Ȯ���ڸ� ���� => �ߺ����� => ���
		stream.map(f -> f.getName())
		    .filter(f ->f.indexOf(".") > -1)
		    .peek(f ->System.out.println("���� ��� �� "+f))
		    .map(f ->f.substring(f.lastIndexOf(".")+1))
		    .distinct()
		    .forEach(System.out::println);
	}

}
