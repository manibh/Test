import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mhossein on 4/13/2015.
 */
public class JavaServer {
    ServerSocket serverSocket;
    Socket socket;

    public JavaServer() {
        initServer();
    }

    private void initServer(){
        try {
            serverSocket = new ServerSocket(5005);

            while(true){
                socket= serverSocket.accept();
                sendMessage("Hi I am Server",socket);
                System.out.println(readMessage(socket));
                sendMessage("Hi oshkol",socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage(Socket clientSocket){
        InputStreamReader streamReader=null;
        BufferedReader reader=null;
        try {
            streamReader = new InputStreamReader(clientSocket.getInputStream());
            reader = new BufferedReader(streamReader);


            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            if(reader!=null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/
        return null;
    }

    public void sendMessage(String message,Socket clientSocket){
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            writer.println(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            if (writer!=null)
                writer.close();
        }*/
    }

    public static void main(String[] args) {
        JavaServer server= new JavaServer();
    }

}
