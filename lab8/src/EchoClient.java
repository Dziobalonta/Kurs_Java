import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {

        int PortNumber = Integer.parseInt(args[0]);
        String userInput = args[1];

        if(userInput.isEmpty()) userInput = "0";

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", PortNumber);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

//        BufferedReader stdIn = new BufferedReader(
//                new InputStreamReader(System.in));
//        String userInput;
//
//        System.out.println("Type a message: ");
//        while ((userInput = stdIn.readLine()) != null) {
//            out.println(userInput);
//            System.out.println("echo: " + in.readLine());
//        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        out.println(userInput);
        System.out.println("echo: " + in.readLine());



        out.close();
        in.close();
        echoSocket.close();
    }
}