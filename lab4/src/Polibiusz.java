public class Polibiusz implements Algorithm{

    private final String alfabet;
    char[][] szachownica;
    private final int rozmiar;

    public Polibiusz(){
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",5);
    }

    public Polibiusz(String alf, int size){
        //tworzyć szachownicę 5x5 wypełniając ją kolejnymi znakami
        // z podanego jako drugi parametr String alfabetem (
        // analogicznym jak w przypadku ROT)
        this.rozmiar = size;
        this.alfabet = alf;
        szachownica = new char[rozmiar][rozmiar];
        int k =0;
        for(int i = 0; i < rozmiar; i++){
            for(int j = 0; j < rozmiar; j++){
                if(k >= alfabet.length()){
                    szachownica[j][i] = 255;
                } else {
                    szachownica[j][i] = alfabet.charAt(k);
                    //System.out.print(szachownica[j][i] + " ");
                }
                k++;
            }
            //System.out.println();
        }
    }

    @Override
    public String crypt(String inputWord) {

        StringBuilder wynik = new StringBuilder();
        for(int k = 0; k < inputWord.length(); k++){
            boolean znaleziony = false;
            for(int i = 0; i < rozmiar; i++) {
                if(znaleziony) break;
                for(int j = 0; j < rozmiar; j++)
                {
                    char znak = inputWord.charAt(k);
                    if(znak == ' '){
                        wynik.append("  ");
                        znaleziony = true;
                    }else if(znak == szachownica[i][j]){
                        wynik.append(String.valueOf(j+1) + String.valueOf(i+1) + " ");
                        znaleziony = true;
                    }
                    if(znaleziony) break;
                }
            }
        }
        if(wynik.length() != 0) wynik.deleteCharAt(wynik.length()-1);
        //System.out.println(wynik);
        return wynik.toString();
    }

    @Override
    public String decrypt(String inputWord) {

        StringBuilder wynik = new StringBuilder();
        for(int k = 0; k < inputWord.length(); k++){
            boolean znaleziony = false;
            for(int i = 0; i < rozmiar; i++) {
                if(znaleziony) break;
                for(int j = 0; j < rozmiar; j++)
                {
                    if( String.valueOf(inputWord.charAt(k)).equals(" ") )
                    {
                       wynik.append(" ");
                       k+=2;
                    } else {
                        int liczba = Integer.parseInt(inputWord.substring(k, k + 2));

                        if (j == (liczba / 10)-1 && i == (liczba % 10)-1) {
                            wynik.append(szachownica[i][j]);
                            znaleziony = true;
                            k+=2;
                        }
                        if(znaleziony) break;
                    }
                }
            }
        }
        //System.out.println(wynik);
        return wynik.toString();
    }
}
