import java.io.*;
import java.util.Scanner;

public class MicroDVDDelay {

    public MicroDVDDelay(){

    }

    boolean checkFormat(String linia){
        if(linia.charAt(0) == '{')
        {
            int i;
            for(i=1; i < linia.indexOf('}');i++){
                if(!Character.isDigit(linia.charAt(i))) return false;
            }
            if(linia.indexOf('{',i) != i+1) return false;
            i+=2;

            if(linia.indexOf('}',i) == -1) return false;
            for(; i < linia.indexOf('}',i);i++){
                if(!Character.isDigit(linia.charAt(i))) return false;
            }

        } else return false;
        return true;
    }

    void delay(String in, String out,int delay, int fps) throws MicroException, IOException {
        int j = 1;

        try (Scanner Czytnik = new Scanner(new File(in)); PrintWriter Zapisz = new PrintWriter(new FileWriter(out))) {

            while (Czytnik.hasNextLine()) {
                String linia = Czytnik.nextLine();
                int start, stop, start_del, stop_del;

                if (checkFormat(linia)) {
                    start = Integer.parseInt(linia.substring(linia.indexOf("{") + 1, linia.indexOf("}")));
                    stop = Integer.parseInt(linia.substring(linia.indexOf("{", linia.indexOf("}") + 1) + 1, linia.indexOf("}", linia.indexOf("}") + 1)));
                } else {
                    Czytnik.close();
                    Zapisz.close();
                    throw new NotANumberException("W linii nr. " + j + " o tresci:\n" + linia + "\n wystapil wyjatek! Podana klatka nie jest liczba lub brakuje wymaganych nawiasow!", j);
                }

                if (start < 0 || stop < 0) {
                    Czytnik.close();
                    Zapisz.close();
                    throw new WrongFormatException("W linii nr. " + j + " o tresci:\n" + linia + "\n wystapil wyjatek! Nie mozna miec ujemnego klatkarzu oraz poczatkowa klatka nie moze byc wieksza od koncowej!", j);
                } else if (start >= stop) {
                    Czytnik.close();
                    Zapisz.close();
                    throw new WrongFormatException("W linii nr. " + j + " o tresci:\n" + linia + "\n wystapil wyjatek! Nie mozna miec ujemnego klatkarzu oraz poczatkowa klatka nie moze byc wieksza od koncowej!", j);
                }

                start_del = start + delay * fps / 1000;
                stop_del = stop + delay * fps / 1000;

                //System.out.println(start + ", " + stop + ", " + start_del + ", " + stop_del);
                int firstIndex = linia.indexOf('}');
                if (Czytnik.hasNextLine()) {
                    Zapisz.println("{" + start_del + "}{" + stop_del + linia.substring(linia.indexOf('}',firstIndex+1),linia.length()));
                } else {
                    Zapisz.print("{" + start_del + "}{" + stop_del + linia.substring(linia.indexOf('}',firstIndex+1),linia.length()));
                }
                j++;
            }
        } catch (IOException e) {
            throw new IOException("Wystapil IOException przy przetwarzaniu pliku.", e);
        }
    }

}