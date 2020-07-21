import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {
    static ServerSocket serverSocket;
    static ExecutorService executorService;

    static {
        executorService = Executors.newFixedThreadPool(4);
        try {
            serverSocket = new ServerSocket(7894);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("服务端已启动......");
        while (true){
            Socket accept = serverSocket.accept();
            System.out.println("新用户连接......");
            executorService.execute(new NioServerHandler(accept));
        }
    }
}
