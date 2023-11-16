package pl.edu.uj.java;

public class PESEL
{
    private final String pesel;

    public PESEL(String pesel)
    {
        this.pesel = pesel;
    }
    public int compare(PESEL other)
    {
        if(this.pesel.equals(other.pesel))
            return 1;
        else return 0;
    }

    public static int check(String pesel)
    {
        if(pesel.length() != 11)
        {
            //System.out.println("Liczba jest za krotka aby byc numerem pesel!");
            return 0;
        }
        else {
            int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            int suma = 0;
            for (int i = 0; i < 10; i++)
                suma += (pesel.charAt(i) - '0') * wagi[i];
            //jezeli zrobimy MOD 10 na koncu to pokryjemy tez przypadek szczegolny
            int wynik = (10 - (suma % 10)) % 10;
            int cyfra_kontrolna = pesel.charAt(pesel.length() - 1) - '0';
            if (wynik == (cyfra_kontrolna))
                return 1;
            else return 0;
        }
    }
}
