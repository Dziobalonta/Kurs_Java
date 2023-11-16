Napisz program sprawdzający poprawność numeru PESEL.

  * Program powinien składać się z dwóch klas: jednej klasy o nazwie Main z metodą main i osobnej klasy PESEL implementującej numer PESEL
  * klasa PESEL powinna znajdować się w pakiecie pl.edu.uj.java
  * PESEL powinien być reprezentowany w klasie jako String
  * PESEL ma konstruktor który przyjmuje String pesel jako parametr 
  * PESEL ma funkcję compare, która przyjmuje jako parametr inny obiekt typu PESEL i porównuje czy PESELE są identyczne zwraca boolean 
  * PESEL powinien mieć statyczną metodę check przyjmującą jako paramter obiekt klasy PESEL i  sprawdzającą czy podany pesel jest OK (zwraca boolean)
  * Jak sprawdzić poprawność numeru PESEL?
  * Program ma umożliwiać podawanie PESELi jako parametry programu, np: `java Main 123456`
  * Jako wyjście program zwraca na standardowe wyjście 0 jeśli PESEL jest niepoprawny i 1 jeśli jest poprawny
  * Jeśli program otrzyma dwa parametry, to porownuje pesele: `java Main 123123 123123` W tym przypadku program zwróci 1 jesli pesele są identyczne i 0 jesli są różne. Jeśli którykolwiek z peseli jest niepoprawny program wyświetli 0
