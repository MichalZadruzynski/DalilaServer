/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaserver;

import HostData.*;
import java.net.Socket;

/**
 * Ta klasa to opakowanie dla DataSource, uzupełnione o ip hosta i wątek połaczenia
 * @author zadruzyn
 */
public class HostBox
{
   private DataSource data;
   private static int id = 0;
   private String ip;
   private int this_id;
   private Socket s;
   
   public HostBox(DataSource data, String ip, Socket s){
      this.data = data;
      this.ip = ip;
      this_id = id++;
      this.s = s;
   }
   
   public DataSource getHostData(){
      return this.data;
   }
   public String getIP(){
      return this.ip;
   }
   public int getID(){
      return this.this_id;
   }
   public Socket getSocket(){
      return this.s;
   }
   public void renewValues(DataSource data){
      this.data = data;
   }
   @Override
   public String toString(){
      return getID()+": "+getIP()+" - "+data.getOsName()+" "+data.getOsVer();
   }
   
}
