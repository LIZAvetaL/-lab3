
import java.io.*;//импорт пакета, содержащего классы для
////ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в
////сети Internet

import java.util.LinkedList;
public class Server {
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();
    public static final int PORT = 4004;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("server starting....");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Новое соединение установлено");
                try {
                    serverList.add(new ServerSomthing(socket));
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его при завершении работы:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
          /*  clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established....");
//создание потока ввода
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока
//вывода
            double[][] clientMessageRecieved = (double[][]) sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных
//в виде строки (передано клиентом)

            while (!clientMessageRecieved.equals("quite"))//выполнение цикла: пока
//строка не будет равна «quite»
            {
                Matrix matr = Matrix.from2DArray(clientMessageRecieved);
                System.out.println("message recieved: '" + matr + "'");
                Matrix ma = matr.withInverter(LinearAlgebra.InverterFactory.GAUSS_JORDAN).inverse();
                System.out.println("m '" + ma + "'");
                soos.writeObject(ma.toCSV());//потоку вывода
//присваивается значение строковой переменной (передается клиенту)
            }
        } catch (Exception e) {
        } finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch (Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }
}*/