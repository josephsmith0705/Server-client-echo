package server;
/**
 * @author N0797675
 */
import java.util.*;
import java.net.*;
import java.io.*;
public class Client {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the address: ");
        String hostname = scan.nextLine();
        System.out.println("Please enter the port: ");
        String portString = scan.nextLine();      
        int port = Integer.parseInt(portString);
        System.out.println("Connecting to "+ hostname + ":" + port);
        //Try to connect to the socket with specified hostname and port
        connectClient(hostname, port);

    }
    
    public static void connectClient(String hostname, int port){
        try (Socket server = new Socket(hostname, port)){
            System.out.println("Successfully connected to " + hostname + ":" + port);
            
            //Create Input&Outputstreams for the connection
            InputStream inputToClient = server.getInputStream();
            OutputStream outputFromClient = server.getOutputStream();
            
            Scanner scanConsole = new Scanner(System.in);
            Scanner scanner = new Scanner(inputToClient, "UTF-8");
            PrintWriter clientPrintOut = new PrintWriter(new OutputStreamWriter(outputFromClient, "UTF-8"), true);
            
            //Have the server echo back any input from the server, whilst sending any text typed in the console
            
            boolean done = false;
            while(!done && scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println("Server: " + line);
                String message = scanConsole.nextLine();
                clientPrintOut.println(message);
            }
        }
        catch (UnknownHostException e){
            System.err.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }
}
}


