import java.util.HashSet;

import java.io.IOException;
import java.util.function.Consumer;
import pl.edu.uj.javaframe.*;

public class Main {

    public static void main(String[] args) {

//      INSTRUKCJE DO ĆWICZEŃ I
//
//      Dopisz metodę print w klasie DataFrame wypisującą zawartość dataframe
//      df.print();
//
//      Dodaj klasę ImaginaryDouble oraz ImaginaryInt, które dziedziczą po odpowiednio klasach Int i Double . Powinne one być tworzone ze Stringów postaci "<CzęśćRzeczywissta>i<CzęśćUrojona>", np "12i3".
//
//      Nadpisz metody create i add w klasach ImaginaryDoubgle oraz ImaginaryInt. Pamiętaj, że do liczb urojonych możemy też dodawać inne typy (np. Int, Double).
//      Przetestuj wyniki operacji:
         new ImaginaryInt().create("12i4").add(new Int().create("10")).print();
         System.out.println();
         new Int().create("10").add(new ImaginaryInt().create("12i4")).print();
         System.out.println();
         new ImaginaryInt().create("12i4").add(new ImaginaryInt().create("10i10")).print();
         System.out.println();
         System.out.println("------------------------------");

         new ImaginaryDouble().create("5.5i4").add(new MyDouble().create("5.5")).print();
         System.out.println();
         new MyDouble().create("5.5").add(new ImaginaryDouble().create("5.5i4")).print();
         System.out.println();
         new ImaginaryDouble().create("19i2.2").add(new ImaginaryDouble().create("13i1.5")).print();
         System.out.println();
         System.out.println("------------------------------");
//         ImaginaryDouble im_1 = (ImaginaryDouble) new ImaginaryDouble().create("5.5i4");
//         MyDouble int_2 = (MyDouble) new MyDouble().create("5.5");
//         ImaginaryDouble im_3 = (ImaginaryDouble) new ImaginaryDouble().create("6.6i4.2");
//         ImaginaryDouble im_4 = (ImaginaryDouble) new ImaginaryDouble().create("6.6i10.6");
//
//         im_1.print();
//         System.out.println();
//         int_2.print();
//         System.out.println();
//         im_3.print();
//         System.out.println();
//         System.out.println("------------------------------");
//         im_1.add(int_2).print();
//         System.out.println();
//         int_2.add(im_1).print();
//         System.out.println();
//         im_3.add(im_4).print();
//         System.out.println();
//         System.out.println("------------------------------");
//
//      Nadpisz metodę public String toString() w kasach ImaginaryDouble i ImaginaryInt, tak aby poprawnie wyświetlała liczby urojone do postaci Stringa w formacie wskazanym wcześniej.
//
//      Poniżej Przykładowy kod, któ©y powinien działać po dopisaniu odpowiednich klas:


         MyDataFrame df = new MyDataFrame(new Class[] {ImaginaryDouble.class, Int.class}, new String[] {"kol1","kol2"});

         df.addRow(new String[]{"12i3","23"});
         df.addRow(new String[]{"1244i4","23"});
         df.addRow(new String[]{"1244i6","2553"});
         df.addRow(new String[]{"12i9","2300"});

         ImaginaryDouble im1 = (ImaginaryDouble) new ImaginaryDouble().create("12i3");
         ImaginaryDouble im2 = (ImaginaryDouble) new ImaginaryDouble().create("5i3");
         ImaginaryDouble im3 = (ImaginaryDouble) new ImaginaryDouble().create("5");
         MyDouble re = (MyDouble) new MyDouble().create("5");

         im1.print();
         System.out.println();
         im2.print();
         System.out.println();
         re.add(im2).print();
         System.out.println();
         System.out.println("------------------------------");
         df.print();



    }


}
