import pl.edu.uj.javaframe.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("IMAGINARY INT & IMAGINARY INT");
        System.out.printf("%n12i4 + 10i2 = ");
        new ImaginaryInt().create("12i4").add(new ImaginaryInt().create("10i2")).print();
        System.out.printf("%n12i4 - 10i3 = ");
        new ImaginaryInt().create("12i4").sub(new ImaginaryInt().create("10i3")).print();
        System.out.printf("%n12i4 * 10i3 = ");
        new ImaginaryInt().create("12i4").mul(new ImaginaryInt().create("10i3")).print();
        System.out.printf("%n12i4 / 10i3 = ");
        new ImaginaryInt().create("12i4").div(new ImaginaryInt().create("10i3")).print();
        boolean result;
        result = new ImaginaryInt().create("12i4").neq(new ImaginaryInt().create("10i3"));
        System.out.printf("%n12i4 != 10i3 = %b",result);
        result = new ImaginaryInt().create("10i3").eq(new ImaginaryInt().create("10i3"));
        System.out.printf("%n10i3 == 10i3 = %b",result);
        result = new ImaginaryInt().create("10i3").equals(new ImaginaryInt().create("10i3"));
        System.out.printf("%n10i3 same type as 10i3 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("IMAGINARY DOUBLE & IMAGINARY DOUBLE");
        System.out.printf("%n12i4 + 10i2 = ");
        new ImaginaryDouble().create("12i4").add(new ImaginaryDouble().create("10i2")).print();
        System.out.printf("%n12i4 - 10i3 = ");
        new ImaginaryDouble().create("12i4").sub(new ImaginaryDouble().create("10i3")).print();
        System.out.printf("%n12i4 * 10i3 = ");
        new ImaginaryDouble().create("12i4").mul(new ImaginaryDouble().create("10i3")).print();
        System.out.printf("%n12i4 / 10i3 = ");
        new ImaginaryDouble().create("12i4").div(new ImaginaryDouble().create("10i3")).print();
        result = new ImaginaryDouble().create("12i4").neq(new ImaginaryDouble().create("10i3"));
        System.out.printf("%n12i4 != 10i3 = %b",result);
        result = new ImaginaryDouble().create("10i3").eq(new ImaginaryDouble().create("10i3"));
        System.out.printf("%n10i3 == 10i3 = %b",result);
        result = new ImaginaryDouble().create("10i3").equals(new ImaginaryInt().create("10i3"));
        System.out.printf("%n10i3 same type as 10i3 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("BOBOT TESTING");
        result = new MyDouble().create("74.49").neq(new Int().create("63"));
        System.out.printf("%n74.49 != 63 = %b",result);
        System.out.printf("%n56.29i79.81 + 21.02i79.98 = ");
        new ImaginaryDouble().create("56.29i79.81").add(new ImaginaryDouble().create("21.02i79.98")).print();
        System.out.printf("%n99.08i13.21 * 58.21i97.83 = ");
        new ImaginaryDouble().create("99.08i13.21").mul(new ImaginaryDouble().create("58.21i97.83")).print();
        System.out.printf("%n10.49i77.46 / 79.59i31.59 = ");
        new ImaginaryDouble().create("10.49i77.46").div(new ImaginaryDouble().create("79.59i31.59")).print();
        System.out.printf("%n14i36 / 80i45 = ");
        new ImaginaryInt().create("14i36").div(new ImaginaryInt().create("80i45")).print();
        result = new ImaginaryInt().create("77i51").neq(new ImaginaryInt().create("60i51"));
        System.out.printf("%n77i51 != 60i51 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("IMAGINARY INT & DOUBLE");
        System.out.printf("%n12i4 + 10.5 = ");
        new ImaginaryInt().create("12i4").add(new MyDouble().create("10.5")).print();
        System.out.printf("%n12i4 - 10.5 = ");
        new ImaginaryInt().create("12i4").sub(new MyDouble().create("10.5")).print();
        System.out.printf("%n12i4 * 10.5 = ");
        new ImaginaryInt().create("12i4").mul(new MyDouble().create("10.5")).print();
        System.out.printf("%n12i4 / 10.5 = ");
        new ImaginaryInt().create("12i4").div(new MyDouble().create("10.5")).print();
        result = new ImaginaryInt().create("12i4").neq(new MyDouble().create("10.5"));
        System.out.printf("%n12i4 != 10.5 = %b",result);
        result = new ImaginaryInt().create("10i3").eq(new MyDouble().create("10.5"));
        System.out.printf("%n10i3 == 10.5 = %b",result);
        result = new ImaginaryInt().create("10i4").equals(new MyDouble().create("10.5"));
        System.out.printf("%n10i4 same type as 10.5 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("IMAGINARY DOUBLE & INT");
        System.out.printf("%n12.5i4.5 + 10 = ");
        new ImaginaryDouble().create("12.5i4.5").add(new Int().create("10")).print();
        System.out.printf("%n12.5i4.5 - 10 = ");
        new ImaginaryDouble().create("12.5i4.5").sub(new Int().create("10")).print();
        System.out.printf("%n12.5i4.5 * 10 = ");
        new ImaginaryDouble().create("12.5i4.5").mul(new Int().create("10")).print();
        System.out.printf("%n12.5i4.5 / 10 = ");
        new ImaginaryDouble().create("12.5i4.5").div(new Int().create("10")).print();
        result = new ImaginaryDouble().create("12.5i4.5").neq(new Int().create("10"));
        System.out.printf("%n12.5i4.5 != 10 = %b",result);
        result = new ImaginaryDouble().create("10.5i3.5").eq(new Int().create("10"));
        System.out.printf("%n10.5i3.5 == 10 = %b",result);
        result = new ImaginaryDouble().create("10.5i4.5").equals(new Int().create("10"));
        System.out.printf("%n10.5i4.5 same type as 10 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("IMAGINARY INT & IMAGINARY DOUBLE");
        System.out.printf("%n12i4 + 10.5i3.5 = ");
        new ImaginaryInt().create("12i4").add(new ImaginaryDouble().create("10.5i3.5")).print();
        System.out.printf("%n12i4 - 10.5i3.5 = ");
        new ImaginaryInt().create("12i4").sub(new ImaginaryDouble().create("10.5i3.5")).print();
        System.out.printf("%n12i4 * 10.5i3.5 = ");
        new ImaginaryInt().create("12i4").mul(new ImaginaryDouble().create("10.5i3.5")).print();
        System.out.printf("%n12i4 / 10.5i3.5 = ");
        new ImaginaryInt().create("12i4").div(new ImaginaryDouble().create("10.5i3.5")).print();
        result = new ImaginaryInt().create("12i4").neq(new ImaginaryDouble().create("10.5i3.5"));
        System.out.printf("%n12i4 != 10.5i3.5 = %b",result);
        result = new ImaginaryInt().create("10i3").eq(new ImaginaryDouble().create("10.5i3.5"));
        System.out.printf("%n10i3 == 10.5i3.5 = %b",result);
        result = new ImaginaryInt().create("10i4").equals(new ImaginaryDouble().create("10.5i3.5"));
        System.out.printf("%n10i4 same type as 10.5i3.5 = %b",result);

        System.out.println();
        System.out.println("------------------------------");

        System.out.println("IMAGINARY DOUBLE & IMAGINARY INT");
        System.out.printf("%n12.5i4.5 + 10i5 = ");
        new ImaginaryDouble().create("12.5i4.5").add(new ImaginaryInt().create("10i5")).print();
        System.out.printf("%n12.5i4.5 - 10i5 = ");
        new ImaginaryDouble().create("12.5i4.5").sub(new ImaginaryInt().create("10i5")).print();
        System.out.printf("%n12.5i4.5 * 10i5 = ");
        new ImaginaryDouble().create("12.5i4.5").mul(new ImaginaryInt().create("10i5")).print();
        System.out.printf("%n12.5i4.5 / 10i5 = ");
        new ImaginaryDouble().create("12.5i4.5").div(new ImaginaryInt().create("10i5")).print();
        result = new ImaginaryDouble().create("12.5i4.5").neq(new ImaginaryInt().create("10i5"));
        System.out.printf("%n12.5i4.5 != 10i5 = %b",result);
        result = new ImaginaryDouble().create("10.5i3.5").eq(new ImaginaryInt().create("10i5"));
        System.out.printf("%n10.5i3.5 == 10i5 = %b",result);
        result = new ImaginaryDouble().create("10.5i4.5").equals(new ImaginaryInt().create("10i5"));
        System.out.printf("%n10.5i4.5 same type as 10i5 = %b",result);

    }
}