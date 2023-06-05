package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

//최종연산
//count(), max(), min(), average(), findFirst(),sum()
public class StreamEx5 {

	public static void main(String[] args) {
		
		// int -> stream
		IntStream stream1 = Arrays.stream(new int [] {1,3,5,7,9});
		// 2의 배수는 몇개?
		//stream1.filter(i-> i % 2 ==0).forEach(System.out::println);
		System.out.println("2의 배수 개수 : "+ stream1.filter(i-> i % 2 == 0).count());
		
		//stream has already been operated upon or closed
		//스트림은 일회용(한번 사용하면 닫혀서 다시 사용 불가)
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		System.out.println("2의 배수 합 : "+ stream1.filter(i-> i % 2 == 0).sum());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2의 배수 평균 : "+ stream1.filter(i-> i % 2 == 0).average());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2의 배수 최대값 : "+ stream1.filter(i-> i % 2 == 0).max());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2의 배수 최소값 : "+ stream1.filter(i-> i % 2 == 0).min());
		
		stream1 = Arrays.stream(new int [] {1,4,6,8,9});
		// OptionaDouble
		System.out.println("2의 배수 첫번째 값 : "+ stream1.filter(i-> i % 2 == 0).findFirst());
		

	}

}
