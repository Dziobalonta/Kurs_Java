public class MicroException extends Exception{
    private final String wiadomosc;
    private final int linia;
    public MicroException(String info, int num){
        this.wiadomosc = info;
        this.linia = num;
    }

    public String getMessage(){

        return wiadomosc;
    }
    int getErrorLine(){
        return linia;
    }
}
