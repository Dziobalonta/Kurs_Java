import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkThread implements Runnable {
    private Socket clientSocket;

    public WorkThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Zaczal prace!");
        try(
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()))
        ){
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                double wynik = Calculate(inputLine);
                out.println(wynik);
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static double Calculate(String input) {
        double result = 0;

        while (maOperand(input)) {
            int operatorIndex = PierwszyOperand(input);
            double num1 = Double.parseDouble(input.substring(0, operatorIndex));

            int nastepnyOperand;
            if(NastepnyOperand(input, operatorIndex+1) == -1){
                nastepnyOperand = input.length();
            } else {
                nastepnyOperand = NastepnyOperand(input, operatorIndex+1);
            }

            double num2 = Double.parseDouble(input.substring(operatorIndex+1, nastepnyOperand));

            char operator = input.charAt(operatorIndex);

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            // Create the new input string by combining the result with any remaining part of the input string
            input = result + (nastepnyOperand == input.length() ? "" : input.substring(nastepnyOperand));
        }

        return Double.parseDouble(input);
    }


    static boolean maOperand(String input) {
        return input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/");
    }

    static int PierwszyOperand(String input) {
        int index = -1;
        if (input.contains("+")) index = input.indexOf("+");
        else if (input.contains("-")) index = input.indexOf("-");
        else if (input.contains("*")) index = input.indexOf("*");
        else if (input.contains("/")) index = input.indexOf("/");
        return index;
    }

    static int NastepnyOperand(String input, int fromIndex) {
        int index = -1;
        if (input.indexOf("+", fromIndex) != -1) index = input.indexOf("+", fromIndex);
        else if (input.indexOf("-", fromIndex) != -1) index = input.indexOf("-", fromIndex);
        else if (input.indexOf("*", fromIndex) != -1) index = input.indexOf("*", fromIndex);
        else if (input.indexOf("/", fromIndex) != -1) index = input.indexOf("/", fromIndex);
        return index;
    }


}
