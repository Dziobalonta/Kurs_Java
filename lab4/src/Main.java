import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String file_In = args[0], file_Out = args[1], what_to_do = args[2], algo = args[3];

        Algorithm algorytm;
        if(algo.equals("rot")){
            algorytm = new ROTXX("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",11);
        }else{
            algorytm = new Polibiusz("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",5);
        }

        if(what_to_do.equals("crypt")){
            Cryptographer.cryptfile(file_In,file_Out,algorytm);
        } else{
            Cryptographer.decryptfile(file_In,file_Out,algorytm);
        }
//        System.out.println("\nKRYPTOWANIE");
//        Cryptographer test = new Cryptographer();
//
//        test.cryptfile("tocrypt.txt","mycrypt.txt",algorytm);
//
//        System.out.println("DEKRYPTOWANIE");
//        test.decryptfile("mycrypt.txt","mytocrypt.txt",algorytm);
//
        Polibiusz ziom = new Polibiusz("X|[ws,af*Ze#=@~pu:.EMnI${)",9);
        System.out.println(ziom.crypt(">r+vy"));
        ziom.decrypt("14 37 34 27");


    }
}