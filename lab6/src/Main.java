import pl.edu.uj.javaframe.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String nazwa_pliku = args[0];
        String nazwa_kolumny = args[1];
        int ilosc_watkow = Integer.parseInt(args[2]);
        int indeks = Integer.parseInt(args[3]);


//        DataFrame df1 = new DataFrame(new Class[] {MyDouble.class, Int.class}, new String[] {"double","inty"});
//
//        df1.addRow(new String[]{"12.3","23"});
//        df1.addRow(new String[]{"1244.4","23"});
//        df1.addRow(new String[]{"1244.6","2553"});
//        df1.addRow(new String[]{"12.9","2300"});
//        df1.head();
//        DataFrame test = df1.iloc(1,3);
//        System.out.println();
//        System.out.println("\nTak dziala iloc: ");
//        test.head();
//        System.out.println();
//
//        Factorize faktoryzacja1 = new Factorize();
//        df1.apply(faktoryzacja1,"inty");
//        df1.print();
//        System.out.println();


        DataFrame df = DataFrame.readCSV(nazwa_pliku, new Class[] {Int.class});
        Applayable faktoryzacja = new Factorize();
        df.apply(faktoryzacja,nazwa_kolumny,ilosc_watkow);
        System.out.println(df.getValueAt(nazwa_kolumny,indeks));
        //df.print();

    }
}