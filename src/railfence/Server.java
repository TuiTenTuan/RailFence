/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railfence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import railfence.*;

/**
 *
 * @author minht
 */
public class Server {
    
    public static void main(String[] args) 
    {
        //khai báo socket và gắn cổng cho server
        ServerSocket server;
        
        try 
        {
            server = new ServerSocket(8080);
            System.out.println("Server đang chạy!");
            
            while (true) 
            {  
                //tạo socker client để thực hiện kết nối với socket server 
                Socket client = server.accept();
                System.out.println("Server đang kết nối client!");

                //khai báo đối tượng in, out để lấy và đưa dữ liệu từ stream
                DataInputStream din = new DataInputStream(client.getInputStream());
                DataOutputStream dout = new DataOutputStream(client.getOutputStream());
                
                String functionRevice = din.readUTF();
                String dataRevice = din.readUTF();
                int key = din.readInt();
                
                if("Encryption".equals(functionRevice))
                {
                    dout.writeUTF(RailFence.Encryption(dataRevice, key));
                }
                else
                {
                    String result = RailFence.Decryption(dataRevice, key);
                    
                    System.out.println(result);
                    
                    String secondMostCharacter = SecondPopular.SecondPopular(result);

                    System.out.println(secondMostCharacter);                    
                    
                    dout.writeUTF(result + "~" + secondMostCharacter);
                }
                
                client.close();
            } 
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
