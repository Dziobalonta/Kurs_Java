import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private static final int MaX_THREADS = 2;

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(MaX_THREADS);
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            System.out.println("Serwer na porcie: " + serverSocket.getLocalPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Czeka w kolejce");

                Runnable pracujacy = new WorkThread(clientSocket);
                threadPool.execute(pracujacy);
                System.out.println("Nowy watek zaczal prace!");
            }
        } catch (IOException e) {
            System.out.println("Could not start server.");
            //System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        } finally {
            threadPool.shutdown();
        }
    }
}