import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MicroDVDDelay test = new MicroDVDDelay();


        try{

            test.delay(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]));

        } catch (NotANumberException | WrongFormatException e) {
            System.out.println(e.getErrorLine());
            //System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println("Wystapil IOException:\n");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Wystapil nieznany wyjatek:\n");
            e.printStackTrace();

        }
    }
}