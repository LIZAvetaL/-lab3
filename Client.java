/*java.io.*;//импорт пакета, содержащего классы для
// ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для
// работы в сети
import org.la4j.Matrix;
import org.la4j.LinearAlgebra;
public class Client {
   public static void main(String[] arg) {
       try {
           System.out.println("server connecting....");
           Socket clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
           System.out.println("connection established....");
           BufferedReader stdin =
                   new BufferedReader(new InputStreamReader(System.in));//создание
//буферизированного символьного потока ввода
           ObjectOutputStream coos =
                   new ObjectOutputStream(clientSocket.getOutputStream());//создание
//потока вывода
           ObjectInputStream  cois =
                   new ObjectInputStream(clientSocket.getInputStream());//создание
//потока ввода
           System.out.println("Enter any string to send to server \n\t('quite' − programme terminate)");
           double [][] clientMessage =  {{0,1,1}, {2,4,2},{6,3,5}};
           System.out.println("you've entered: "+clientMessage); while(!clientMessage.equals("quite")) {//выполнение цикла, пока строка //не будет равна «quite»
               coos.writeObject(clientMessage);//потоку вывода присваивается //значение строковой переменной (передается серверу)
               System.out.println("~server~: "+cois.readObject());//выводится на //экран содержимое потока ввода (переданное сервером)
               System.out.println("---------------------------");
               //clientMessage = stdin.readLine();//ввод текста с клавиатуры
              // System.out.println("you've entered: "+clientMessage);//вывод в
//консоль строки и значения строковой переменной
           }
           coos.close();//закрытие потока вывода
           cois.close();//закрытие потока ввода
           clientSocket.close();//закрытие сокета
       }catch(Exception e)	{
           e.printStackTrace();//выполнение метода исключения е
       }*/

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                int[][] word = {{0,1,1},{2,4,2},{6,3,5}}; // ждём пока клиент что-нибудь
                // не напишет в консоль
                for (int i=0; i<3;i++){
                    for (int j=0;j<3;j++){
                        out.write(word[i][j]);
                    }
                }
                out.flush();

                String [][] serverWord = new String[3][3];
                for (int i=0; i<3;i++){
                    for (int j=0;j<3;j++){
                        System.out.println(in.readLine());
                    }
                }

                //System.out.println(msg); // получив - выводим на экран
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}

