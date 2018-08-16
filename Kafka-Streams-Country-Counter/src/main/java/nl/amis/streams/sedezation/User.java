package nl.amis.streams.sedezation;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	@Override
	public String toString() {
		return "User(" + name + ", " + age + ")";
	}

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String userStr = mapper.writeValueAsString(new User("Karthik", 27));
		System.out.println(userStr);
		User user = mapper.readValue(userStr.getBytes(), User.class);
		System.out.println(user);
	}
}