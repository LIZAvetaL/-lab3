import org.la4j.Matrix;
import org.la4j.LinearAlgebra;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private  BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Новое соединение установлено");
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // писать туда же
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        start(); // вызываем run()
    }
    @Override
    public void run() {
        double [][] clientMessageRecieved=new double[3][3];
        try {
            while (true) {
                System.out.println("Новое соединение установлено");
                for (int i=0; i<3;i++){
                    for (int j=0;j<3;j++){
                        clientMessageRecieved[i][j]=in.read();
                    }
                }
                    Matrix matrix=Matrix.from2DArray(clientMessageRecieved);
                    System.out.println(matrix);
                    Matrix arr=matrix.withInverter(LinearAlgebra.InverterFactory.SMART).inverse();
                    System.out.println(arr);
                    String msg="";
                    for (int i=0; i<3;i++){
                        for (int j=0;j<3;j++){
                            msg+=(Double.toString(arr.get(i, j))+" ");

                        }
                        send(msg);
                        msg="";
                    }


                break;
            }

        } catch (IOException e) {
        }

    }

    private void send(String msg) {
        try {
            System.out.println(msg);
            out.write(msg+ "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
}