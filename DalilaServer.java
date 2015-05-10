/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaserver;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;



/**
 *
 * @author zadruzyn
 */
public class DalilaServer
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args){
      final DalilaFrame window;
      try
      {
         window = new DalilaFrame("Dalila Server");
         EventQueue.invokeLater(new Runnable(){
         @Override
         public void run(){
            window.setVisible(true);
         }
         });
      } catch (IOException ex)
      {
         JOptionPane.showMessageDialog(null, "Nie można postawić serwera");
      }
      
   }
}
