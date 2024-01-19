public class ROTXX implements  Algorithm{
    private final String alfabet;
    private int rotacja;

    public ROTXX() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", 11);
    }

    public  ROTXX(String alph,int rot){
        this.alfabet = alph;
        this.rotacja = rot;
    }

    @Override
    public String crypt(String inputWord) {

        StringBuilder wynik = new StringBuilder(inputWord);

        for(int i = 0; i < inputWord.length(); i++) {
            char znak = inputWord.charAt(i);
            if(alfabet.indexOf(znak) != -1) {
                wynik.setCharAt(i, alfabet.charAt((alfabet.indexOf(znak)+rotacja) % alfabet.length()));
            }
        }
        return wynik.toString();
    }

    @Override
    public String decrypt(String inputWord) {

        StringBuilder wynik = new StringBuilder(inputWord);

        for(int i = 0; i < inputWord.length(); i++) {
            char znak = inputWord.charAt(i);
            if(alfabet.indexOf(znak) != -1) {
                if(alfabet.indexOf(znak) >= rotacja) {
                    wynik.setCharAt(i, alfabet.charAt(alfabet.indexOf(znak)-rotacja));
                } else{
                    wynik.setCharAt(i, alfabet.charAt(alfabet.length() - (rotacja - alfabet.indexOf(znak))));
                }
            }
        }
        return wynik.toString();
    }
}
