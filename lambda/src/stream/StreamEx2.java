package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx2 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("바둑","바나나","포도","딸기","바질","강아지");
		
		// "바로 " 시작하는 요소를 새로운 리스트에 추가 후 출력
		List<String> list1 = new ArrayList<String>();
		for (String str: list) {
			if(str.startsWith("바")) {
				list1.add(str);
			}
		}
		for (String str : list1) {
			System.out.println(str);
		}
		
		//filter() : 스트림 요소 걸러내기
		list.stream().filter(str -> str.startsWith("바")).forEach(System.out::println);
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동",99));
		stuList.add(new Student("고길동",89));
		stuList.add(new Student("김길동",79));
		stuList.add(new Student("박길동",69));
		
		//이름의 시작이 김으로 시작하는 학생들의 이름 출력
		for (Student student : stuList) {
			if(student.getName().startsWith("김")) {
				System.out.println(student.getName());
			}
		}

		stuList.stream().filter(stu ->stu.getName().startsWith("김"))
		//.forEach(s -> System.out::println(s.getName()));
		.forEach(System.out::println);
		
		//distinct() : 중복제거
		List<String> list2 = Arrays.asList("바둑","바나나","포도","딸기","바질","바둑");
		list2.stream().distinct().forEach(System.out::println);
		
		// Arrays.asList(...) : array => list
		// Stream.of(....) : array => stream
		Stream<File> stream = Stream.of(new File("e:\\test1.txt"), new File("e:\\test2.txt"), new File("e:\\test3.txt"),
				new File("e:\\test1.java"), new File("e:\\test1.bak"), new File("e:\\test2"));
		
		//파일 확장자 추출한 후 중복을 제거하고 출력하기
		// 파일명 추출 => 확장자 추출 => 확장자만 모음 => 중복제거 => 출력
		stream.map(f -> f.getName())
		    .filter(f ->f.indexOf(".") > -1)
		    .peek(f ->System.out.println("필터 통과 후 "+f))
		    .map(f ->f.substring(f.lastIndexOf(".")+1))
		    .distinct()
		    .forEach(System.out::println);
	}

}
