import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
                int randomNum = ThreadLocalRandom.current().nextInt(1024, 65535 + 1);
                serverSocket = new ServerSocket(randomNum); //randomowo wybiera wolny port
                //System.out.println("Server started on port: " + serverSocket.getLocalPort());
                System.out.println(serverSocket.getLocalPort());

        } catch (IOException e) {
            System.out.println("Could not start server.");
            System.exit(-1);
        }

        Socket clientSocket = null;

        while(true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed!");
                System.exit(-1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                double wynik = Calculate(inputLine);
                if(wynik % 1 == 0) out.println((int)wynik);
                else out.println(wynik);
            }
            out.close();
            in.close();
            clientSocket.close();
            //serverSocket.close();
        }

    }

    public static double Calculate(String input) {
        String input_corr;
        if(input.contains("--")) input_corr = input.replace("--","+");
        else if(input.contains("+-")) input_corr = input.replace("+-","-");
        else if(input.contains("-+")) input_corr = input.replace("-+","-");
        else input_corr = input;

        String[] elements,operands;
        if (input_corr.charAt(0) == '-') {
            elements = input_corr.substring(1, input_corr.length()).split("[\\+\\-\\*/]+");
             operands = splitByArray(input_corr, elements);
            elements[0] = "-" + elements[0];
        } else {
            elements = input_corr.split("[\\+\\-\\*/]+");
            operands = splitByArray(input_corr, elements);
        }

//        for (String number : elements) {
//            System.out.println(number);
//        }
//
//        for (String operand : operands) {
//            System.out.println(operand);
//        }

        double result = Double.parseDouble(elements[0]);

        for (int i = 1; i < elements.length; i++) {
            char operator = operands[i].charAt(0);
            double element = Double.parseDouble(elements[i]);

            char sign;

            if(operands[i].length() > 1) {
                sign = operands[i].charAt(1);
                if(sign == '-') element = -1 * element;
            }


            switch (operator) {
                case '+':
                    result += element;
                    break;
                case '-':
                    result -= element;
                    break;
                case '*':
                    result *= element;
                    break;
                case '/':
                    if (element != 0) {
                        result /= element;
                    } else {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        return result;
    }

    public static String[] splitByArray(String input, String[] delimiters) {
        StringBuilder regex = new StringBuilder("(");

        for (int i = 0; i < delimiters.length; i++) {
            regex.append(Pattern.quote(delimiters[i]));
            if (i < delimiters.length - 1) {
                regex.append("|");
            }
        }

        regex.append(")");
        return input.split(regex.toString());
    }
}
