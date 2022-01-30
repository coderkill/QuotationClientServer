package client;

import pojo.Quotation;
import util.ConnectUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientClass {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost(); //FOR Showing local address
        String addressLocal = "0.0.0.0";
        Socket socket = null;
        String line = null;
        BufferedReader bufferedReader = null;
        BufferedReader is = null;
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
            line = bufferedReader.readLine();
            while (line.compareTo("QUIT") != 0) {
                os.println(line);
                os.flush();
                Quotation quotation = (Quotation) objectInput.readObject();
                System.out.println("Server Response : " + quotation.toString());
                System.out.println("Enter Symbol to echo Server ( Enter QUIT to end):");
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            inputStream.close();
            os.close();
            bufferedReader.close();
            socket.close();
            objectInput.close();
            inputStream.close();
            System.out.println("Connection Closed");

        }
    }
}