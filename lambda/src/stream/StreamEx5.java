package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

//��������
//count(), max(), min(), average(), findFirst(),sum()
public class StreamEx5 {

	public static void main(String[] args) {
		
		// int -> stream
		IntStream stream1 = Arrays.stream(new int [] {1,3,5,7,9});
		// 2�� ����� �?
		//stream1.filter(i-> i % 2 ==0).forEach(System.out::println);
		System.out.println("2�� ��� ���� : "+ stream1.filter(i-> i % 2 == 0).count());
		
		//stream has already been operated upon or closed
		//��Ʈ���� ��ȸ��(�ѹ� ����ϸ� ������ �ٽ� ��� �Ұ�)
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		System.out.println("2�� ��� �� : "+ stream1.filter(i-> i % 2 == 0).sum());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2�� ��� ��� : "+ stream1.filter(i-> i % 2 == 0).average());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2�� ��� �ִ밪 : "+ stream1.filter(i-> i % 2 == 0).max());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2�� ��� �ּҰ� : "+ stream1.filter(i-> i % 2 == 0).min());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2�� ��� ù��° �� : "+ stream1.filter(i-> i % 2 == 0).findFirst());
		

	}

}
