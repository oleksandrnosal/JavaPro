package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)){
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();

			Thread th = new Thread(new GetThread(login));
			th.setDaemon(true);
			th.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}

			System.out.println("Please, enter your user login in format: @login. After that enter your message ");

			while (true) {
				String inputText = scanner.nextLine();
				if (inputText.isEmpty())
					break;
				String text = "";
				String to = "";
				if (inputText.equals("/users") || inputText.equals("/online")) {
					new GetOnlineUsers().getPresentUsers();
				} else if (inputText.startsWith("@")) {
					if (inputText.contains(" ")) {
						to = inputText.substring(inputText.indexOf("@") + 1, inputText.indexOf(" "));
						text = inputText.substring(inputText.indexOf(" ") + 1);
					} else {
						text = inputText;
					}
				}
				Message m = new Message(login, to, text);
				int res = m.send(Utils.getURL() + "/add");

				if (res != 200) { // 200 OK
					System.out.println("HTTP error occurred: " + res);
					return;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}