package server;

import pojo.Quotation;
import util.QuotationGenerator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerClass {
    public static void main(String[] args) {


        Socket s;
        ServerSocket ss2 = null;
        System.out.println("Server Listening...");
        try {
            ss2 = new ServerSocket(4445);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("connection Established " + s.toString());
                ServerThread st = new ServerThread(s);
                st.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }

}

class ServerThread extends Thread {

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket s = null;
    OutputStream outputStream;
    ObjectOutput objectOutput;

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            outputStream = s.getOutputStream();
            objectOutput = new ObjectOutputStream(outputStream);
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }


        try {
            line = is.readLine();
            while (line.compareTo("QUIT") != 0) {
                Quotation quotation = QuotationGenerator.returnQuotaion(line);
                System.out.println(quotation);
                objectOutput.writeObject(quotation);
                objectOutput.flush();
//                os.println(line);
                System.out.println("Response to Client  :  " + quotation);
                line = is.readLine();
            }
        } catch (IOException e) {

            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println("Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();
                    System.out.println("Socket Closed");
                }
                if (objectOutput != null) {
                    objectOutput.close();
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}