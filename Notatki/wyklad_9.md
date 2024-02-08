# Wykład 9
### DOM
### SAX
### JAXB, XMLDecoder i XMLEncoder
### ANT

--------------------------

## Czym jest XML?

`XML` — <span style="color:grey">(Extensible Markup Language, rozszerzalny język znaczników)</span> – to format dokumentów przeznaczony do reprezentowania różnych danych w strukturalizowany sposób. Używany do przechowywania, przesyłania i rekonstrukcji dowolnych danych,między różnymi programami. 

>Format zapisu dokumentów, format tekstowy, można ten dokument podejrzeć w dowolnym edytorze tekstowym, zedytować. Ale zajmuje też więcej miejsca. <span style="color:grey">(Json jest trochę bardziej ekonomiczną wersją)</span>.

Jest niezależny od platformy, co umożliwia łatwą wymianę dokumentów pomiędzy różnymi systemami. Jest najpopularniejszym obecnie uniwersalnym językiem przeznaczonym do reprezentowania danych.

### Po co są takie dokumenty?
Przykład ze sklepem internetowym, aktualizacją danych w sektorze księgowym, wystawianiem automatycznie faktur, aktualizowanie stanu magazynu itp. – ogólnie wzajemna komunikacja między różnymi oprogramowaniami, często napisanymi w różnych językach, może być zastąpiona ujednolicowym systemem *XML*.

Przykład pliku *XML*:
```xml
<?xml version="1.0" encoding="UTF-8"?>
    <rss xmlns:dc="http://purl.org/dc/elements/1.1/" version="2.0">
    <channel>
        <title>Aktualności</title>
        <link>http://www.uj.edu.pl/uniwersytet/aktualnosci/...</link>
        <description>Aktualności Uniwersytetu Jagiellońskiego</description>
        <item>
            <title>Lista Pamięci - Stanisław Szczur</title>
            <link>http://www.uj.edu.pl/uniwersytet/aktualnosci/...</link>
            <description />
            <pubDate>Thu, 20 Dec 2012 07:44:00 GMT</pubDate>
            <dc:creator>Jolanta Herian-Ślusarska</dc:creator>
            <dc:date>2012-12-20T07:44:00Z</dc:date>
        </item>
        <item>...</item>
        ...
    </channel>
</rss>
```

<span style="background-color:green"><span style="color:white">XML dokument ustrukturyzowany w dany sposób, <u>nie działa jak struktura w HTML</u>, która odpowiada danemu układowi elementów na stronie.</span></span>

## DOM

Są dwie podstawowe metody przetwarzania dokumentów xml. Jedną z nich jest `DOM`<span style="color:grey">(Document Object Model)</span>.

**Parsery DOM <span style="color:grey">(Document Object Model)</span>** — program który odczytuje dokument *xml-owy* i na podstawie zawartości dokumentu <u>tworzy drzewo</u> reprezentujące dane w nim zawarte. Po zbudowaniu DOM przetwarzanie odbywa się na modelu w pamięci operacyjnej.

Przykład:
```xml
<?xml version="1.0"?>
  <books>
    <book>
        <author>Carson</author>
        <price format="dollar">31.95</price>
        <pubdate>05/01/2001</pubdate>
    </book>
    <pubinfo>
        <publisher>MSPress</publisher>
        <state>WA</state>
    </pubinfo>
  </books>
```
dane *XML* są odczytane w strukturze *DOM*.

![Box](/images/Obraz20.png)

### DOM — Odczyt

Jak zrobić to w Javie?
```java
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
public class DOMExample {
    public static void main(String[] args) throws 
                    ParserConfigurationException,SAXException,IOException {
        URL url = new URL(
        "http://www.uj.edu.pl/uniwersytet/aktualnosci/-/journal/rss/10172/36018?
        doAsGroupId=10172&refererPlid=10181"); // jakis RSS

        // domyslna fabryka “obiektow tworzących dokumenty”
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        // obiekt “tworzący dokumenty”
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // tworzenie modelu DOM na podstawie zrodla XML (parsowanie XML'a)
        Document doc = dBuilder.parse(url.openStream());

        // obiekt doc umozliwia dostep do wszystkich danych zawartych 
        // w dokumencie XML
        System.out.println("Root element :" + 
                                    doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("item");

        for (int i = 0; i < nList.getLength(); i++) {

            // lista “dzieci” i-tego itema
            Node n = nList.item(i);

            // czy “dziecko” jest elementem?
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                System.out.println("Tytul : "
                                        + getTagValue("title", e));
                System.out.println("Link : "
                                        + getTagValue("link", e));
                System.out.println("dodane przez : "
                                        + getTagValue("dc:creator", e));
            }
        }
    }
    // zwraca wartosc zapisana w tagu s wewnatrz elementu e
    private static String getTagValue(String s, Element e) {

        // lista “dzieci” e o nazwie s
        NodeList nl = e.getElementsByTagName(s)
        // pierwszy wpis z tej listy
                                                .item(0)
        // to co on zawiera – jego “dzieci”             pierwsze z dzieci i jedyne
                                                        .getChildNodes();

        // pierwsze z tych dzieci
        Node n = (Node) nl.item(0);
        
        // zawartosc, ktora tam jest
        return n.getNodeValue();
    }
}
```
`parse()` – służy do parsowania, tworzenia *DOMu* na podstawie danych, które zawierają dokument *XML* i zwraca referencję *doc*, za pomocą której mamy dostęp do drzewa. <span style="color:grey">(tutaj: odczytujemy z sieci, ale moglibyśmy też np. z pliku)</span>

`doc.getDocumentElement()` – zwraca korzeń drzewa
`getNodeName()` – zwraca nazwę korzenia <span style="color:grey">(tutaj: rss)</span>

`NodeList` – Struktura, lista węzłów, podobna strukturalnie do tablicy

`doc.getElementsByTagName(„item”)` – bierzemy wszystkie elementy, które mają nazwę "*item*"

### Jak modyfikować takie drzewo?

Możliwa jest także modyfikacja zawartości *DOM* utworzonego w wyniku parsowania dokumentu *XML*. 

Przykładowo:

```java
// pobieramy element title i zmieniamy go
if ("title".equals(node.getNodeName())) {
    node.setTextContent("Nowy tytul");
}
// kasujemy link
if ("link".equals(node.getNodeName())) {
    itemElement.removeChild(node);  // usuwa wszystkie dzieci elementu node
}

// tworzenie nowego dokumentu
Document doc = docBuilder.newDocument();
Element e = doc.createElement("root");
doc.appendChild(e);
```
### DOM — Zapis

>Plik DOM możemy z powrotem zapisać do postaci *XML* lub innej.

Przykład zapisu dokumentu:

```java
// domyslna fabryka transformatorow
TransformerFactory transformerFactory = TransformerFactory.newInstance();

// nowy tranformator
Transformer transformer = transformerFactory.newTransformer();

// wejscie transformatora (skad transformator bierze dane)
DOMSource source = new DOMSource(doc);
// wyjscie transformatora (gdzie “zmienione” dane zostana zapisane)
StreamResult result = new StreamResult(new File("file.xml"));

// uruchomienie transformatora – zapis DOM do pliku w formacie XML
transformer.transform(source, result);
```

Dokument XML to merytoryczna zawartość jakiegoś tam „dokumentu”. Ta zawartość <span style="color:grey">(xml)</span> może zostać zapisana do formatu *.docx* *.txt* *.html* itp. Jeśli chcemy tą zawartość mieć zapisaną w różnych formatach, to musimy nadać odpowiednim tagom w xmlu informację, jak mają zostać wyświetlone w zależności od rozszerzenia pliku w jakim ten xml został zapisany. **I za to odpowiadają transformatory użyte powyżej**.

## SAX

`Parser SAX` <span style="color:grey">(Simple API for XML)</span> — Alternatywa technika przetwarzania dokumentów *xml-owych*. W miarę czytania dokumentu wywołuje zdarzenia związane z parsowaniem. Parsery SAX są szybsze i nie wymagają tak dużej ilości pamięci jak DOM.

Działa w ten sposób, że nie tworzy żadnego drzewa, nie ma prawie żadnego związku z pamięcią operacyjną.

>**Czytają dokument xml i generują zdarzenia z tym czytaniem**. `Parser SAX` dostaje dokument xml i obiekt na którym będzie wywoływał metody związane ze zdarzeniami w tym xmlu.
 

 ```java
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;
import org.xml.sax.*;
public class SAXExample {
    public static void main(String[] args) throws SAXException, 
                                        IOException, ParserConfigurationException{
    URL url = new URL("http://www.uj.edu.pl/...");

    SAXParserFactory f = SAXParserFactory.newInstance();
    SAXParser saxParser = f.newSAXParser();

    DefaultHandler handler = new ExampleSAXHandler();
    saxParser.parse(url.openStream(), handler);
    }
}
 ```

obiekt **handler** jest nasłuchiwaczem zdarzeń <span style="color:grey">(analogia do Swinga)</span>. Reaguje na akcje generowane przez parser SAX.

### DefaultHandler

**`DefaultHandler`** — to klasa, która posiada szereg pustych metod, każda z tych metod odpowiada innemu zdarzeniu które może wywołać parser SAX podczas czytania XML'a.

**Jak używamy?**
Tworzymy własne rozszerzenie klasy w którym nadpisujemy metody zdarzeń na które chcemy w poszczególny sposób reagować

- `void characters(char[] ch, int start, int length)` — wywoływane przy 
odczycie znaku wewnątrz elementu
- `void endDocument()` — wywoływane gdy koniec dokumentu
- `void endElement(String uri, String localName, String qName)` — koniec elementu
- `void error(SAXParseException e)` — błąd <span style="color:grey">(możliwy do naprawienia)</span>
- `void fatalError(SAXParseException e)` — błąd
- `void ignorableWhitespace(char[] ch, int start, int length)` — ignorowany 
pusty znak
- `void startDocument()` — początek dokumentu
- `void startElement(String uri, String localName, String qName, Attributes attributes)` — początek elementu
- `void warning(SAXParseException e)` — ostrzeżenie parsera
...

```java
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
class ExampleSAXHandler extends DefaultHandler {
    public void startElement(String uri, String localName, String qName,
                                Attributes attributes) throws SAXException {
        System.out.println("Element :" + qName);
    }
    public void endElement(String uri, String localName, String qName)
                                                    throws SAXException {
        System.out.println("Konie elementu :" + qName);
    }
    public void characters(char ch[], int start, int length)
                                                throws SAXException {
        System.out.println("zawartosc : "+new String(ch, start, length));
    }
}
```
## Popdsumowanie *DOM* & *SAX*
| Kryterium          | SAX                        | DOM                             |
|--------------------|----------------------------|---------------------------------|
| Model              | Zdarzeniowy                | Drzewiasty                      |
| Przetwarzanie      | Sekwencyjne                | Cały dokument jest ładowany do pamięci |
| Pamięć             | <span style="color:green">Niewielkie zużycie pamięci</span> | <span style="color:red">Pamięciożerne</span>                   |
| Prędkość           | <span style="color:green">Szybkie</span>                    | <span style="color:red">Wolniejsze</span>                      |
| Funkcjonalność     | <span style="color:red">Ograniczona</span>                | <span style="color:green">Uniwersalne</span>
| Zastosowanie       | Duże pliki                 | Małe pliki                      |
| Tworzenie XML      | Nieodpowiednie             | Odpowiednie                     |
| Struktura wewnętrzna | Nie tworzy               | Tworzy                          |


## JAXB

`JAXB` — <span style="color:grey">(Java Architecture for XML Binding)</span>  Służy do **serializacji obiektów** javy do formatu *XML*

>**JAXB jest teraz poza JDK i JRE**. Aby teraz korzystać z JAXB trzeba w programie obsłużyć odpowiednie archiwa jar.

```java
import java.io.File;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
class Person {

    String name;
    int age;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return this.name + " (" + this.age + ")";
    }
}
public class JAXBExample{ 
    public static void main(String[] args) throws JAXBException {
        Person p = new Person();
        p.setName("Barnaba");
        p.setAge(33);

        File f = new File("person.xml");
        JAXBContext ctx = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(p, System.out);
        marshaller.marshal(p, f);
        p = null;
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        p = (Person)unmarshaller.unmarshal(f);
        System.out.println(p);
    }
}
```

Klasa, która ma zostać zserializowana <span style="background-color:red"><span style="color:white">musi zawierać odpowiednie adnotacje!</span></span>

>Czym są adnotacje?
to są adnotacje:`@XmlRootElement`, `@XmlElement`

`@XmlRootElement` — zaznacza, że klasa *Person* jest korzeniem drzewa xml, w którym będzie instancja tej klasy
**Adnotacje to komentarze na poziomie JVM**. Komentarze tego typu przechodzą do bajtkodu, ale <u>nie zmieniają działania programu</u>. **Dają informacje**.


Adnotacja `@XmlElement` powoduje, że instancja klasy *Person* będzie miała dwa elementy: *name* i *age* <span style="color:grey"> — Nazwy do elementów bierze z Setterów i Getterów, a nie z nazw zmiennych</span>.
Zostaną one potem wpisane do xml'a i na podstawie xml'a później będzie mozna odtworzyć te wartości.

>Na tym polega **serializacja**, tworzeniu nowego pustego obiektu i on jest wypełniany zawartością tego, co jest w postaci zserializowanej.

`Marshaller` — obiekt odpowiedzialny za serializację, zapis obiektu do xml'a, 
<span style="color:grey">*createMarshaller()* — metoda serializująca</span>

`Unmarshaller` — tworzenie obiektu na bazie danych z xml'a —<span style="color:grey"> za pomocą metody *createUnmarshaller()*</span>

## XMLEncoder i XMLDecoder

Istnieje inny prostszy sposób serializacji i deserializacji xml'owej niektórych rodzajów obiektów <span style="color:grey">(np. komponenty Swingowe)</span>. Możemy wówczas użyć klas `XMLEncoder` i `XMLDecoder`. 

#### `XMLEncoder`:
```java
XMLEncoder e = new XMLEncoder(new FileOutputStream("jbutton.xml"));
e.writeObject(new JButton("Hello world"));
e.close();
```
1. Tworzymy nowy obiekt *e*, do którego zapisujemy zserializowany obiekt.
2. podajemy co chcemy zserializować, wczytujemy obiekt Swing'owy.
3. Zamykamy strumień

#### `XMLDecoder`:
```java
XMLDecoder d = new XMLDecoder(new FileInputStream("jbutton.xml"));
obj = d.readObject();
d.close();
```

## ANT

***ANT* to javowy odpowiednik "make" w C**. Służy do automatyzacji wszelkich procesów potrzebnych do budowania programów.

 Jego podstawowe cechy to:
- konfiguracja zadań zapisana w formacie XML,
- wieloplatformowość – m. in. Linux, Unix (np. Solaris and HP-UX), Windows 9x i NT, OS/2 Warp, Novell Netware 6 oraz MacOS X.
- rozszerzalność w oparciu o klasy napisane w Javie. Ant jest rozwijany w ramach *Apache Software Foundation*. Strona domowa projektu: http://ant.apache.org

Przykładowy plik konfiguracyjny dla anta <span style="color:grey">(domyślnie build.xml)</span>:
```xml
<project name="project" default="default" basedir=".">
    <description>
        jakis opis
    </description>

    <target name="default" depends="depends" description="description">
        <jar destfile="bendmetter.jar" includes="**/*.class"
                                excludes="tests/*.*" basedir="bin"></jar>
    </target>

    <target name="depends">
    </target>
</project>
```
>Aby go “wykonać” wpisujemy w konsoli polecenie *ant*.

**<span style="color:red">default</span>** <span style="color:red">zostanie wykonany dopiero <u>gdy zostanie wykonany najpierw cel depends</u> niżej.</span>

**cel *default:*** tworzy plik *.jar* który zawiera wszystkie pliki z rozszerzeniem *.class*, wchodząć do katalogu *bin* i wykluczając podkatalog *tests*

**ANT jest na dole hierarchii**, są inne analogiczne narzędzia z których korzysta się częściej <span style="color:grey">(*Maven* — javowy, *Gradle* — międzyplatformowy)</span>.