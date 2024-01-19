package pl.edu.uj.javaframe;

import java.util.ArrayList;

public class Factorize implements Applayable {
    @Override
    public void apply(Series v) {
        ArrayList<Integer> wynik = new ArrayList<>();

        for(Value value: v.values){
            double doubleValue = Double.parseDouble(String.valueOf(value));
            ArrayList<Integer> zfaktoryzowane = faktoryzuj((int)doubleValue);
            int sum = zfaktoryzowane.stream().mapToInt(Integer::intValue).sum();

            wynik.add(sum);
        }
        //System.out.println("\n");
        v.values.clear();
        for(Integer i : wynik) {
            v.values.add(new Int().create(i.toString()));
        }
    }

    private ArrayList<Integer> faktoryzuj(int number) {
        ArrayList<Integer> wynik = new ArrayList<>();

        //System.out.println("\nLiczby pierwsze " + number + " to:");
        for (int i = 2; i <= number; i++) {

            if (number % i == 0) {
                wynik.add(i);
                number /= i;
                //System.out.print(i + ", ");
            }
        }
        wynik.add(number);
        //System.out.print(number);
        return wynik;
    }
}
