import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NioServerHandler implements Runnable {

    private Socket socket;

    public NioServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream())){
            String message;
            while ((message = bufferedReader.readLine())!=null){
                System.out.println("服务端接收到消息："+message);
                System.out.println(message);
                printWriter.println("\""+message+"\"\r"+ System.currentTimeMillis());
                printWriter.flush();
//                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
