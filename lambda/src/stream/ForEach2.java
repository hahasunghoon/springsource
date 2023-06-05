package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForEach2 {

	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("A","B","C","D","E");
		
		//list.stream().forEach(null);
		list.forEach(item -> System.out.println(item));
		list.forEach(System.out::println);

		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동",99));
		stuList.add(new Student("고길동",89));
		stuList.add(new Student("김길동",79));
		stuList.add(new Student("박길동",69));
		
		stuList.forEach((student) ->{});
	}

}
