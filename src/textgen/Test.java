package textgen;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
		String[] words = input.split("[\\s]+");
		for (String word : words) {
			System.out.println(word);
		}

	}

}
