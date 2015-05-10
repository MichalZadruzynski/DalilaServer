/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dalilaserver;

import HostData.*;
import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author zadruzyn
 */
public class HostInfoPanel extends JPanel 
{
   public HostInfoPanel(HostBox data){

      JPanel north = new JPanel();
      
      int part = data.getHostData().getPartitions().size();
      JLabel osLabel = new JLabel("System Operacyjny: "+data.getHostData().getOsName()+" "+
              data.getHostData().getOsVer());
      JLabel partLabel = 
              new JLabel("Liczba partycji: "+part);
      north.add(osLabel);
      north.add(partLabel);

      add(north);
      JPanel south = new JPanel();
      south.setLayout(new GridLayout(0,1));

      for(Partition p: data.getHostData().getPartitions()){
         Part tmp = new Part(p);
         tmp.setPreferredSize(new Dimension(50,100));
         south.add(tmp);
         
      }

      JScrollPane southScroll = new JScrollPane(south);
      southScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      southScroll.setPreferredSize(new Dimension(500,400));
      add(southScroll);
   }
   private String capacityToString(long capacity){
      DecimalFormat df = new DecimalFormat("#.##");
      if(capacity>1024*1024) return ""+df.format((double)capacity/(1024*1024))+" GB";
      if(capacity>1024) return ""+df.format((double)capacity/(1024))+" MB";
      return ""+df.format((double)capacity/(1024))+" kB";
   }
   class Part extends JPanel
   {
      JProgressBar bar = new JProgressBar();
      public Part(Partition p){
         this.setLayout(new GridLayout(0,1));
         bar.setValue(100 - (int)p.freeSpacePrecent());
         Label mountP = new Label(p.getMountPoint());
         mountP.setFont(new Font("Arial Bold",Font.TRUETYPE_FONT,20));
         add(mountP);
         add(new Label(p.getLabel()));
         add(new Label("Pojemność: "+capacityToString(p.getTotalSpace())));
         add(new Label("Zajęte miejsce: "+capacityToString(p.getUsedSpace())));
         add(bar,BorderLayout.CENTER);
      }
      
   }  
}

