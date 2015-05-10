/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaserver;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author zadruzyn
 */
public class Server implements Runnable
{
   private int port;
   private ServerSocket serv;
   private Socket incoming;
   private DalilaFrame parent;
   
   public Server(int port, DalilaFrame frame) throws IOException{
      this.port = port;
      this.parent = frame;
      serv = new ServerSocket(this.port);
   }
   @Override
   public void run()
   {
      while(true)
      {
         try
         {
            incoming = serv.accept();
            Thread t = new Thread(new ServerHandler(incoming, parent));
            t.start();

         } catch (IOException ex)
         {
            JOptionPane.showMessageDialog(null, "Błąd w połączeniu.");
         } 
      }
   }
   
}
