package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*��Ʈ������
 * - �پ��� ������ �̿��� ������ �������� ������ ó����
 * - ��������
 *    1) �߰����� : �������� ��Ʈ��
 *                            map(), filter(), skip()....
 *    2)�������� : ���� ����� ��Ʈ���� �ƴ� ����          
 *                      forEach() , collect(), reduce(), count(), max(), min().....
 * 
 */
public class StreamEx1 {

	public static void main(String[] args) {
		// ���� ��ü���� �̸��� ���

		List<File> list = new ArrayList<File>();
		
		list.add(new File("e:\\file1.txt"));
		list.add(new File("e:\\file2.txt"));
		list.add(new File("e:\\file3.txt"));
		list.add(new File("e:\\file4.txt"));
		list.add(new File("e:\\file5.txt"));
		
		// �̸��� ������ �� ���
		List<String> fileNames = new ArrayList<String>();
		for (File file : list) {
			fileNames.add(file.getName());			
		}
		for (String string : fileNames) {
			System.out.println(string);
		}
		//Stream���� ó��?
		//Stram ��ȯ (list.stream())-> ����(map()) - > ������
		//map() : ��Ʈ���� ��ҿ� ����� �� �߿��� ���ϴ� �ʵ常 �����ϰų� Ư�����·� ��ȯ�� ���
		//Stream<String> names = list.stream().map(File::getName);
		//names.forEach(f -> System.out.println(f));
		
		list.stream().map(File::getName).forEach(f -> System.out.println(f));
		
		List<String> fruits = Arrays.asList("melon", "apple", "banana", "grape");
		//fruits �빮�ڷ� ������ �� ���ο� ����Ʈ�� ���� �� ���
		List<String> upper = new ArrayList<String>();
		for (String string :  fruits) {
			upper.add(string.toUpperCase());
		}
		for (String string : upper) {
			System.out.println(string);		
		} 
		System.out.println();
		
		 fruits.stream().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		 
		 fruits.stream().map(String::toUpperCase).forEach(System.out::println);
	}

}
