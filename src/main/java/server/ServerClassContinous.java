package server;

import com.github.javafaker.Faker;
import com.github.javafaker.Stock;
import pojo.Quotation;
import util.QuotationGenerator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerClassContinous {
    public static void main(String[] args) {


        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening......");
        try {
            ss2 = new ServerSocket(4449);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("connection Established " + s.toString());
                ServerThreadContinous st = new ServerThreadContinous(s);
                st.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }

}

class ServerThreadContinous extends Thread {

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket s = null;
    OutputStream outputStream;
    ObjectOutput objectOutput;

    public ServerThreadContinous(Socket s) {
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
            Faker faker = new Faker();
            Stock stock = faker.stock();
            while (true) {
                Thread.sleep(100);
                Quotation quotation = QuotationGenerator.returnQuotaion(stock.nsdqSymbol());
                System.out.println(quotation);
                objectOutput.writeObject(quotation);
                objectOutput.flush();
                System.out.println("Response to Client  :  " + quotation);
            }
        } catch (IOException e) {

            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}