import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cryptographer {

    public static void cryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm) throws IOException {

        FileReader Czytnik = new FileReader(path_to_file_in);
        StringBuilder Strbldr = new StringBuilder();
        String wyjscie;
        int data;
        while ((data = Czytnik.read()) != -1)
        {
            Strbldr.append((char)data);
        }
        Czytnik.close();
        System.out.print(Strbldr);

        wyjscie = algorithm.crypt(Strbldr.toString());

        FileWriter Zapis = new FileWriter(path_to_file_out);
        System.out.println("\n" + wyjscie);
        Zapis.write(wyjscie);
        Zapis.close();

    }

    public static void decryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm) throws IOException {
        FileReader Czytnik = new FileReader(path_to_file_in);
        StringBuilder Strbldr = new StringBuilder();
        String wyjscie;
        int data;
        while ((data = Czytnik.read()) != -1)
        {
            Strbldr.append((char)data);
        }
        Czytnik.close();
        System.out.print(Strbldr);

        wyjscie = algorithm.decrypt(Strbldr.toString());

        FileWriter Zapis = new FileWriter(path_to_file_out);
        System.out.println("\n" + wyjscie);
        Zapis.write(wyjscie);
        Zapis.close();
    }
}

