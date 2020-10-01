import org.la4j.Matrix;
import org.la4j.LinearAlgebra;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private ObjectInputStream in; // поток чтения из сокета
    private ObjectOutputStream out; // поток записи в сокет

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        start(); // вызываем run()
    }
    @Override
    public void run() {
        double [][] clientMessageRecieved;
        try {
            while (true) {
                clientMessageRecieved = (double[][]) in.readObject();
                if(clientMessageRecieved.equals("stop")) {
                    break; }
                else {
                    System.out.println(clientMessageRecieved);
                    Matrix matrix=Matrix.from2DArray(clientMessageRecieved);
                    System.out.println(matrix);
                    String arr=matrix.withInverter(LinearAlgebra.InverterFactory.SMART).inverse().toCSV();
                    System.out.println(arr);
                    send(arr);

                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }

    }

    private void send(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException ignored) {}
    }
}