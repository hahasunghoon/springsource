package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx6 {

	public static void main(String[] args) {
	
         //���ڿ��� �����ϴ� ��� ���� => 1)
		Supplier<String> s = () ->"�ȳ��ϼ���";
		System.out.println(s.get());
		
		// ���ڿ��� �Է¹޾Ƽ� ���ڿ��� ���̰� 0���� �Ǵ� ��� ���� =>3)
		Predicate<String> p =(str) -> str.length() ==0;
		System.out.println(p.test("���ڿ� ����"));
		// ���ڿ��� �Է¹޾Ƽ� ����ϴ� ��� ���� =>2)
		Consumer<String> c = i -> System.out.println(i);
		c.accept("�ݰ����ϴ�");
		//���ڿ��� �Է¹޾Ƽ� ������ ���� ��� ����=>4)
		Function<String, Integer> f = str -> Integer.parseInt(str)+39;
		System.out.println(f.apply("561"));
	}

}
