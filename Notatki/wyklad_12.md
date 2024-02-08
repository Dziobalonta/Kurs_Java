# Wykład 12
### Plik .class
### ASM
--------------------------

## Plik .class

Wiadomo, że jak się skompiluje program w javie kompilatorem `javac` to z kodu źródłowego o rozszerzeniu java powstaje nam zwykle jeden plik z rozszerzeniem `.class`. <span style="color:grey">(Czasem może być więcej plików, jeżeli ta klasa zawiera jakieś klasy zagnieżdżone).</span>

Struktura Pliku `.class` w javie:
```java
ClassFile {
 u4 magic;
 u2 minor_version;
 u2 major_version;
 u2 constant_pool_count;
 cp_info constant_pool[constant_pool_count-1];
 u2 access_flags;
 u2 this_class;
 u2 super_class;
 u2 interfaces_count;
 u2 interfaces[interfaces_count];
 u2 fields_count;
 field_info fields[fields_count];
 u2 methods_count;
 method_info methods[methods_count];
 u2 attributes_count;
 attribute_info attributes[attributes_count];
}
```
***u4*** — czterobajtowa sekwencja
***u2*** — dwubajtowa sekwencja

Teraz omówimy co znaczą kolejne bajty w tym pliku:
### Jak skonstruowany jest Plik `.class`?

Przykład dla kodu:
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

Na początku każdego pliku `.class` bajtkodu javy, 4 pierwsze bajty to:
>**CA FE BA BE** — tzw."*magic*", identyfkator formatu pliku

Kolejne 4 określają wersję wirtualnej maszyny javy JVM która będzie potrzebna do uruchomienia programu:
>**<span style="color:red">00 00</span> <span style="color:dodgerblue">00 33</span>** — numer wersji JVM: (<span style="color:dodgerblue">51</span>.<span style="color:red">0</span>)

Bajty od prawej są bardziej znaczące. Zapis w systemie szesnastkowym.

    3 * 16 + 3 = 51

Czyli zgodna wersja to JVM: (<span style="color:dodgerblue">51</span>.<span style="color:red">0</span>) — oznaczna to zgodność z Java 1.7

#### Ogólna Zasada
**<span style="color:green">JVM 1.k (*k*>1) obsługuje klasy od 45.0 do 44+*k*.0 włącznie</span>**

>**<span style="color:yellow">00 22</span>** – ilość deklarowanych elementów <span style="color:grey">(Constant Pool)</span>.


***`Constant Pool`*** — to specjalna sekcja w pliku `.class` Javy, która <u>zawiera stałe potrzebne do wykonania kodu</u> konkretnej klasy.

    2 * 16 + 2 = 34

**<span style="color:red">Stałych jest <u>zawsze o jeden mniej</u> niż *constant_pool_count*!</span>**

Po tej deklaracji występuja następująco kolejne, 33 elementy:

>1. **<span style="color:red">07</span> <span style="color:dodgerblue">00 02</span>** – <span style="color:red">klasa</span> o nazwie w elemencie nr <span style="color:dodgerblue">2</span>
>2. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** – <span style="color:red">string UTF-8</span> o długości <span style="color:dodgerblue">4</span> <span style="color:grey">— następnie jest ten string</span>
**<span style="color:green">4D 61 69 6E</span>** <span style="color:green"> — *Main*</span>
>3. **<span style="color:red">07</span> <span style="color:dodgerblue">00 04</span>** – <span style="color:red">klasa</span> o nazwie w elemencie nr <span style="color:dodgerblue">4</span>
>4. **<span style="color:red">01</span> <span style="color:dodgerblue">00 10</span>** – <span style="color:red">string UTF-8</span> o długości <span style="color:dodgerblue">16</span>
> **<span style="color:green">6A 61 76 61 2F 6C 61 6E 67 2F 4F 62 6A 65 63 74**</span> <span style="color:green">- *java/lang/Object*</span>
>5. **<span style="color:red">01</span> <span style="color:dodgerblue">00 06</span>** <span style="color:green">*<<span>init>*</span> <span style="color:grey">— Już bez pisania tych 16 bajtów</span>
>6. **<span style="color:red">01</span> <span style="color:dodgerblue">00 03</span>** <span style="color:green">*()V*</span>
>7. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** <span style="color:green">*Code*</span>
>8. **<span style="color:red">0A</span> <span style="color:dodgerblue">00 03 00 09</span>**– <span style="color:red">klasa <span style="color:dodgerblue">3</span> posiada metodę <span style="color:dodgerblue">9</span></span>
>9. **<span style="color:red">0C</span> <span style="color:dodgerblue">00 05 00 06</span>** – <span style="color:red">metoda</span> o nazwie <span style="color:dodgerblue">5</span> i sygnaturze <span style="color:dodgerblue">6</span> <span style="color:grey">(brak  argumentów (), zwraca void V)</span>
>10. **<span style="color:red">01</span> <span style="color:dodgerblue">00 0F</span>** *<span style="color:green">LineNumberTable</span>*
>11. **<span style="color:red">01</span> <span style="color:dodgerblue">00 12</span>** *<span style="color:green">LocalVariableTable</span>*
>12. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** *<span style="color:green">this</span>*
>13. **<span style="color:red">01</span> <span style="color:dodgerblue">00 06</span>** *<span style="color:green">LMain;</span>*
>14. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** *<span style="color:green">main</span>*
>15. **<span style="color:red">01</span> <span style="color:dodgerblue">00 16</span>** *<span style="color:green">([Ljava/lang/String;)V</span>*
>16. **<span style="color:red">09</span> <span style="color:dodgerblue">00 11 00 13</span>** – <span style="color:red">klasa</span> <span style="color:dodgerblue">17</span> posiada atrybut <span style="color:dodgerblue">19</span>
>17. **<span style="color:red">07</span> <span style="color:dodgerblue">00 12</span>** – <span style="color:red">klasa</span> o nazwie <span style="color:dodgerblue">18</span>
>18. **<span style="color:red">01</span> <span style="color:dodgerblue">00 10</span>** *<span style="color:green">java/lang/System</span>*
>19. **<span style="color:red">0C</span> <span style="color:dodgerblue">00 14 00 15</span>** – <span style="color:red">atrybut</span> o nazwie <span style="color:dodgerblue">20</span> i typie <span style="color:dodgerblue">21</span>
>20. **<span style="color:red">01</span> <span style="color:dodgerblue">00 03</span>** *<span style="color:green">out</span>*
>21. **<span style="color:red">01</span> <span style="color:dodgerblue">00 15</span>** *<span style="color:green">Ljava/io/PrintStream;</span>*
>22. **<span style="color:red">08</span> <span style="color:dodgerblue">00 17</span>** – <span style="color:dodgerblue">23</span> element to <span style="color:red">stała tekstowa</span>
>23. **<span style="color:red">01</span> <span style="color:dodgerblue">00 0C</span>** *<span style="color:green">Hello world!</span>*
>24. **<span style="color:red">0A</span> <span style="color:dodgerblue">00 19 00 1B</span>** – <span style="color:red">klasa <span style="color:dodgerblue">25</span> posiada metodę <span style="color:dodgerblue">27</span></span>
>25. **<span style="color:red">07</span> <span style="color:dodgerblue">00 1A</span>** – <span style="color:red">klasa</span> o nazwie <span style="color:dodgerblue">26</span>
>26. **<span style="color:red">01</span> <span style="color:dodgerblue">00 13</span>** <span style="color:green">java/io/PrintStream
>27. **<span style="color:red">0C</span> <span style="color:dodgerblue">00 1C 00 1D</span>** – <span style="color:red">metoda</span> <span style="color:dodgerblue">28</span> o sygnaturze <span style="color:dodgerblue">29</span>
>28. **<span style="color:red">01</span> <span style="color:dodgerblue">00 07</span>** *<span style="color:green">println</span>*
>29. **<span style="color:red">01</span> <span style="color:dodgerblue">00 15</span>** *<span style="color:green">(Ljava/lang/String;)V</span>*
>30. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** *<span style="color:green">args</span>*
>31. **<span style="color:red">01</span> <span style="color:dodgerblue">00 13</span>** *<span style="color:green">[Ljava/lang/String;</span>*
>32. **<span style="color:red">01</span> <span style="color:dodgerblue">00 0A</span>** *<span style="color:green">SourceFile</span>*
>33. **<span style="color:red">01</span> <span style="color:dodgerblue">00 09</span>** *<span style="color:green">Main.java</span>*

Po *Constant Pool*-u mamy poniższe bajty:

>**00 21** - modyfkatory dostępu dla klasy: `ACC_PUBLIC (00 01) | ACC_SUPER (00 20)` – ze względu na kompatybilność ze starszymi JVM.

Pierwszy atrybut oznacza, że klasa jest publiczna a ten drugi ze względu na kompatybilność ze starszymi JVM

>**00 01** – numer elementu określający klasę defniowaną w tym pliku

Dla przypomnienia:

1. **<span style="color:red">07</span> <span style="color:dodgerblue">00 02</span>** – <span style="color:red">klasa</span> o nazwie w elemencie nr <span style="color:dodgerblue">2</span>
2. **<span style="color:red">01</span> <span style="color:dodgerblue">00 04</span>** – <span style="color:red">string UTF-8</span> o długości <span style="color:dodgerblue">4</span>
**<span style="color:green">4D 61 69 6E</span>** <span style="color:green"> — *Main*</span>

>**00 03** – numer nadklasy

Dla przypomnienia:

3. **<span style="color:red">07</span> <span style="color:dodgerblue">00 04</span>** – <span style="color:red">klasa</span> o nazwie w elemencie nr <span style="color:dodgerblue">4</span>
4. **<span style="color:red">01</span> <span style="color:dodgerblue">00 10</span>** – <span style="color:red">string UTF-8</span> o długości <span style="color:dodgerblue">16</span>
**<span style="color:green">6A 61 76 61 2F 6C 61 6E 67 2F 4F 62 6A 65 63 74**</span> <span style="color:green">- *java/lang/Object*</span>

>**00 00** – liczba interfejsów

Jeśli byłyby jakieś interfejsy teraz następowałaby tablica wszystkich interfejsów

>**00 00** – liczba pól (atrybutów)

Analogicznie z polami, byłaby tablica

>**00 02** – liczba metod

### Jak wygląda struktura `method_info`?

```java
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

>**00 01** – modyfkatory dostępu: `ACC_PUBLIC (00 01)`
>**00 05** – indeks elementu z nazwą metody <span style="color:grey"> — *<<span>init>*</span></span>
>**00 06** – indeks elementu z sygnaturą metody<span style="color:grey"> — *()V*</span>
>**00 01** – liczba dodatkowych atrybutów

Teraz zaczyna się jednoelementowa tablica:
>**00 07** – indeks elementu z nazwą atrybutu (*Code*)
>**00 00 00 2F** – długość atrybutu
>**00 01 00 01** – rozmiar stosu I rozmiar tablicy zmiennych lokalnych

### Metoda *<<span>init></span>*

- Pisze ją za nas kompliator

Metoda wywołana na obiekcie `object` inicjalizuje <span style="color:grey">(wywołuje konstruktor)</span> nadklasę.

>**00 00 00 05** – długość kodu, wykonywanych instrukcji <span style="color:grey">— bedzie 5 bajtów instrkucji</span>

Poniższe instrukcje mogą być różne, ale takie są dla metody *init*: <span style="color:grey">— działa to trochę jak w *asemblerze*</span>

>**<span style="color:red">2A</span>** — *<span style="color:red">ALOAD_0</span>* <span style="color:grey">— zmienna lokalna o adresie 0 jest wstawiana na stos</span>
**<span style="color:red">B7</span> <span style="color:dodgerblue">00 08</span>** — *<span style="color:red">INVOKESPECIAL</span> <span style="color:dodgerblue">java/lang/Object.<<span>init>()V;</span></span>* <span style="color:grey">— wywołuje metodę void Object.<<span>init>();</span></span>
**<span style="color:red">B1</span>**  — *<span style="color:red">RETURN</span>* <span style="color:grey">— zwraca typ void</span>

**Te instrukcje zawsze znajdują się w bajtkodzie!**

>**00 00** – długość tablicy wyjątków
>**00 02** – liczba dodatkowych atrybutów
>**00 0A** – tablica numerów linii
>**00 00 00 06** – długość atrybutu
>**00 01** – długość tabeli
>**00 00** – indeks instrukcji w tabeli (*Code*)
>**00 01** – nr lini w pliku źródłowym

>**00 0B** – tablica zmiennych lokalnych 
>**00 00 00 0C** – długość atrybutu
>**00 01** – długość tabeli
>**00 00** – początek zmiennej 
>**00 05** – długość zmiennej
>**00 0C** – indeks nazwy zmiennej
>**00 0D** – indeks nazwy typu zmiennej
>**00 00** - indeks zmiennej w tabeli zmiennych lokalnych

###Metoda *main*

>**00 09** – modyfkatory dostępu: `ACC_PUBLIC (00 01) | ACC_STATIC (00 08)`
>**00 0E** – indeks elementu z nazwą metody (*main*)
>**00 0F** – sygnatura *([Ljava/lang/String;)V*

`([Ljava/lang/String;)V` — Przyjmowana tablica Stringów a typ zwracany to void, zgadza się
```java
public static void main(String[] args)
```
>**00 01** – liczba dodatkowych atrybutów
>**00 07** – indeks elementu *Code*
>**00 00 00 37** – długość elementu
>**00 02** – rozmiar stosu
>**00 01** – rozmiar tablicy zmiennych lokalnych 
>**00 00 00 09** – długość kodu

>**<span style="color:red">B2</span> <span style="color:dodgerblue">00 10</span>** — *<span style="color:red">GETSTATIC</span> <span style="color:dodgerblue">java/lang/System.out : Ljava/io/PrintStream;</span>* <span style="color:grey">— inicjalizacja klasy/obiektu *System.out* i odłożenie go na stos</span>
**<span style="color:red">12</span> <span style="color:dodgerblue">16</span>** — *<span style="color:red">LDC</span> <span style="color:dodgerblue">Hello world!</span>* <span style="color:grey">— załadowanie na stos stałej tekstowej</span>
**<span style="color:red">B6</span> <span style="color:dodgerblue">00 18</span>** — *<span style="color:red">INVOKEVIRTUAL</span> <span style="color:dodgerblue">java/io/PrintStream.println(Ljava/lang/String;)V</span>* <span style="color:grey">— wywołuje metodę *System.out.println(String)*</span>
**<span style="color:red">B1</span>** — *<span style="color:red">RETURN</span>* <span style="color:grey">— zwraca typ void</span>


 > **00 00** - długość tablicy wyjątków
 > **00 02** – liczba dodatkowych atrybutów 
 > **00 0A** – tablica numerów linii
 > **00 00 00 0A 00 02 00 00 00 04 00 08 00 05**

 W tej tablicy chodzi o to któremu elementowi kodu odpowiada który numer linii
 > **00 0B** – tablica zmiennych lokalnych
 > **00 00 00 0C 00 01 00 00 00 09 00 1E 00 1F 00 0**
 > **00 01** – liczba atrybutów
 > **00 20** – SourceFile
 > **00 00 00 02** – długość atrybutu
 > **00 21** - Main.java

 ### Warto zapamiętać:

 1. **Kompilator dopisuje nam tą metodę `init`**
 Czyli my możemy mieć w klasie jedną metodę. Natomiast w wyniku kompilowania, w pliku clas będziemy mieć 2 metody.

 2. **Faktyczne instrukcje do wykonania to bardzo mały fragment pliku `.class`**
 Większość z tych informacji to są klocki, które są potrzebne do wykonania instrukcji kodu naszego programu.


 ### Assembler javowy

Tak samo jak w przypadku kodu maszynowego zamiast używać po prostu liczb,wygodniej do odczytania tego używa się asemblera, czyli takiego zapisu słownego. Dla javy też można. **Istnieją rozszerzenia do środowisk programistycznych, które pozwalają te asembler wyświetlić na bazie bajt kodu.**

jest to ten sam kod co napisany wyżej bajt po bajcie.

```assembler
// Compiled from Main.java (version 1.7 : 51.0, super bit)
public class Main {
 
    // Method descriptor #6 ()V
    // Stack: 1, Locals: 1
    public Main();
      0  aload_0 [this]
      1  invokespecial java.lang.Object() [8]
      4  return
        Line numbers:
          [pc: 0, line: 1]
        Local variable table:
          [pc: 0, pc: 5] local: this index: 0 type: Main
    // Method descriptor #15 ([Ljava/lang/String;)V
    // Stack: 2, Locals: 1
    public static void main(java.lang.String[] args);
      0  getstatic java.lang.System.out : java.io.PrintStream [16]
      3  ldc <String "Hello world!"> [22]
      5  invokevirtual java.io.PrintStream.println(java.lang.String) : void
  [24]
      8  return
        Line numbers:
          [pc: 0, line: 4]
          [pc: 8, line: 5]
    Local variable table:
      [pc: 0, pc: 9] local: args index: 0 type: java.lang.String[]
}
```

Jest to ten sam kod co napisany wyżej bajt po bajcie.

## ASM

`ASM` to biblioteka **ułatwiająca manipulację bytecodem Javy**. Daje możliwość niskopoziomowej analizy, tworzenia oraz modyfikacji istniejących klas.

Linijka kompilująca pojniższy kod:

    java -cp asm-4.1.jar:asm-util-4.1.jar:asm-commons-4.1.jar
    org.objectweb.asm.util.ASMifier Main.class

Przykładowy kod generujący klasę Main wygląda następująco:

```java
import org.objectweb.asm.*;

public class MainDump implements Opcodes {
    public static byte[] dump() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "Main", null,
                                                    "java/lang/Object", null);
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");

        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
                                        "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                                                    "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello world!");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", 
                                            "println", "(Ljava/lang/String;)V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cw.visitEnd();

        return cw.toByteArray();
    }
}
```
`public static byte[] dump()` — zwraca bytecode klasy *MainDump*

`ClassWriter` — metoda służąca do pisania klas

`MethodVisitor` — obiekt za pomocą którego piszemy metody

`V1_7` — określa wersję JVM

Przykład użycia:
```java
public class HelloWorldASM extends ClassLoader{
    public static void main(final String args[]) throws Exception {
        HelloWorldASM loader = new HelloWorldASM();
        byte[] code = MainDump.dump();
        Class cl = loader.defineClass("Main", code, 0, code.length);
        cl.getMethods()[0].invoke(null, new Object[] { null });
    }
}
```

### **<span style="color:red">Uwagi:</span>**
1. **Można modyfikować bajtkod, dopóki nie wgramy go do pamięci**
jak już zainicjujemy tą klasę,<span style="color:grey">(wykonamy instrukcję `defineClass()` w *ClassLoaderze* ✨"No to już jest koniec"✨)</span>.
2. **W bajtkodzie mogą znajdować się adnotacje** <span style="color:grey">(mogą być przeczytane przez np. JAXB)</span>