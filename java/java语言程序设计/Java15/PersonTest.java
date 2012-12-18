class Person
{
	public String name;
	public static int eyeNum;
}

public class PersonTest
{
	public static void main(String[] args)
	{
		System.out.println("Person 的eyeNum类Field值: " + Person.eyeNum);

		Person p = new Person();
		System.out.println("p变量的name Field值是: " + p.name + " p对象的eyeNum Field值是: " + p.eyeNum);

		p.name = "孙悟空";
		p.eyeNum = 2;

		System.out.println("p变量的name Field值是: " + p.name + "p对象的eyeNum Field值是: " + p.eyeNum);
		System.out.println("Person的eyeNum类Field值是: " + Person.eyeNum);
		
		Person p2 = new Person();
		System.out.println("p2对象的eyeNum类Field值是: " + p2.eyeNum);

	}
}
