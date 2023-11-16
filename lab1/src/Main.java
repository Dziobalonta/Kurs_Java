import pl.edu.uj.java.PESEL;

public class Main
{
    public static void main(String[] args)
    {
        switch(args.length)
        {
            case 0:
                System.out.println("Nie podano zadnego peselu!");
                break;
            case 1:
                int wynik = PESEL.check(args[0]);
                System.out.println(wynik);
                break;
            case 2:
                PESEL pesel1 = new PESEL(args[0]);
                PESEL pesel2 = new PESEL(args[1]);
                int porownaj  = pesel1.compare(pesel2);
                System.out.println(porownaj);
                break;
            default:
                System.out.println("Podano za duzo argumentow!");
        }
    }
}
