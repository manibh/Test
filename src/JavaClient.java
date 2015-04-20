import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mhossein on 4/13/2015.
 */
public class JavaClient {
    Socket clientSocket;
    public static final int PORT;
    static{
        PORT=8080;
    }

    public JavaClient() {
        initConnection();
    }

    private void initConnection() {
        try {
            clientSocket = new Socket("127.0.0.1", 5005);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String readMessage(){
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

    public void sendMessage(String message){
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

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {
        JavaClient jc= new JavaClient();
        System.out.println(jc.readMessage());
        jc.sendMessage("Hi I am Client");
        System.out.println(jc.readMessage());
    }

}
