import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NioClient {
    static Socket socket;

    static {
        try {
            socket = new Socket("127.0.0.1",7894);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        new Thread(new NioClientHandler(socket)).start();
        try (PrintWriter printWriter= new PrintWriter(socket.getOutputStream())){
            while (true) {
                System.out.println("请输入发送的消息：");
                printWriter.println(new Scanner(System.in).next());
                printWriter.flush();
            }
        }
    }
}
