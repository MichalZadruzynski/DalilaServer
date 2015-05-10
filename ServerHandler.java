/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaserver;

import HostData.DataSource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zadruzyn
 */
public class ServerHandler implements Runnable
{
   private Socket in;
   private DalilaFrame parent;
   
   public ServerHandler(Socket in, DalilaFrame frame){
      this.in = in;
      parent = frame;
   }
   
   @Override
   public void run()
   {
      ObjectInputStream input;
      try
      {
         input = new ObjectInputStream(in.getInputStream());
         DataSource d = (DataSource) input.readObject();
         parent.addHost(d, in.getInetAddress().getHostAddress(), in);
         
      } catch (IOException ex)
      {
         Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex)
      {
         Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
      } 
      finally
      {
         try
         {
            in.close();
         } catch (IOException ex)
         {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
