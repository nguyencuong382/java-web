/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testudp;

import java.io.IOException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TestUDP {
    
    public static void main(String[] args) throws IOException {
        
        Socket socket = new Socket("google.co.uk", 80);
        
        BufferedReader in;
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF8"))) {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            sendMessage(out, new File("C:/Users/Admin/Desktop/www/test.txt"));
            readResponse(in);
        }
        in.close();
    }
    
    private static void sendMessage(BufferedWriter out, File request) throws IOException {
        System.out.println(" * Request");
        
        for (String line : getContents(request)) {
            System.out.println(line);
            out.write(line + "\r\n");
        }
        
        out.write("\r\n");
        out.flush();
    }
    
    private static void readResponse(BufferedReader in) throws IOException {
        System.out.println("\n * Response");
        
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
    
    private static List<String> getContents(File file) throws IOException {
        List<String> contents = new ArrayList<String>();
        
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;
        while ((line = input.readLine()) != null) {
            contents.add(line);
        }
        input.close();
        
        return contents;
    }
}