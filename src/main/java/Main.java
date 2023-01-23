import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("server started");
        int port = 8950;


        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.format("New connection accepted. Port: %d%n", clientSocket.getPort());
                    out.println("what is your name?");
                    String name = in.readLine();
                    System.out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                    String[] options1 = {"big", "tired", "square"};
                    String[] options2 = {"small", "fresh", "circle"};
                    String[] questions = {String.format("%s, choose you: %s or %s", name, options1[0], options2[0]),
                            String.format("%s, choose you: %s or %s", name, options1[1], options2[1]),
                            String.format("%s, choose you: %s or %s", name, options1[2], options2[2])};
                    String[] answers = new String[3];
                    String answer;
                    for (int i = 0; i < questions.length; i++) {
                        System.out.println(questions[i]);
                        out.println(questions[i]);
                        answer = in.readLine();
                        answers[i] = answer;
                        System.out.println(answer);
                    }
                    out.println(String.format("Congratulations! You %s, %s, %s!", answers[0], answers[1], answers[2]));
                    System.out.println("Survey completed");
                }
            }
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }


    }
}
