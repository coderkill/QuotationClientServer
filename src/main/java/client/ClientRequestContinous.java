package client;

import com.github.javafaker.Faker;
import com.github.javafaker.Stock;
import pojo.Quotation;
import util.ConnectUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRequestContinous {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost(); //FOR Showing local address
        String addressLocal = "0.0.0.0";
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter os = null;
        InputStream inputStream = null;
        ObjectInput objectInput = null;

        try {
            socket = ConnectUtil.keepConnecting(addressLocal, 4445);
            inputStream = socket.getInputStream();
            objectInput = new ObjectInputStream(inputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : " + address);
        System.out.println("Enter Symbol to echo Server ( Enter QUIT to end):");

        try {
            Faker faker = new Faker();
            Stock stock = faker.stock();
            while (true) {
                Thread.sleep(100);
                os.println(stock.nsdqSymbol());
                os.flush();
                Quotation quotation = (Quotation) objectInput.readObject();
                System.out.println("Server Response : " + quotation.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            assert inputStream != null;
            inputStream.close();
            assert os != null;
            os.close();
            assert bufferedReader != null;
            bufferedReader.close();
            socket.close();
            objectInput.close();
            inputStream.close();
            System.out.println("Connection Closed");

        }

    }
}