package server;
/**
 * @author N0797675
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        connectToServer();
    }
    
    public static void connectToServer(){
        //Try connect to the server on an unused port. A successful connection will return a socket
        try(ServerSocket serverSocket = new ServerSocket(9991)){
            Socket connectionSocket = serverSocket.accept();
        
            //Create Input&Outputstreams for the connection
            InputStream inputToServer = connectionSocket.getInputStream();
            OutputStream outputFromServer = connectionSocket.getOutputStream();
        
            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
        
            serverPrintOut.println("Hello! Enter quit to exit.");
        
            //Have the server take input from the client and echo it back
            //This should be placed in a loop that listens for terminator text - quit
        
            boolean done = false;
            while(!done && scanner.hasNextLine()){
                String line = scanner.nextLine();
                serverPrintOut.println(line);
                System.out.println("Message from client: " + line);
                if (line.toLowerCase().trim().equals("quit")){
                    System.out.println("Connection closed by client");
                    done = true;
                }
            }
        }
        catch (IOException e){
                e.printStackTrace();
        }    
    }
}
