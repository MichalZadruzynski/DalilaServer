/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalilaserver;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zadruzyn
 * @param <T> typ objektu
 */
public class JTableAdapter<T>
{
   private String title;
   private Collection<T> elements;
   private JTable table;
   
   public JTableAdapter(){
      this.elements = null;
      this.table = null;
      this.title = null;
   }
   
   public JTableAdapter(String title, JTable table){
      this.table = table;
      this.title = title;
      this.elements = new ArrayList<>();
   }
   
   public JTableAdapter(String title,Collection<T> elements, JTable table){
      this.elements = elements;
      this.table = table;
      this.title = title;
   }
   
   private T get(int index){
      return (T)elements.toArray()[index];
   }  
   private int getSelectedID(){
      return (int)table.getModel().getValueAt(table.getSelectedRow(), 0);
   }
   
   public T getSelectedItem(){
      return get(getSelectedID());
   }
   
   public void add(T element){
      if(element!=null){
         this.elements.add(element);
         update();
      }
   }
   
   public void addAll(Collection<T> elements){
      if(elements!=null&&!elements.isEmpty()){
         for(T e: elements){
            this.add(e);
         }
         update();
      }
      
   }
   
   private void update(){
      Object columnNames[] = {title};
      Object rowData[][] = new Object[elements.size()][];
      int i =0;
      for(T data: elements) {
         rowData[i++] = 
                 new Object[]{data.toString()};
      }
      
      DefaultTableModel model = new DefaultTableModel(rowData,columnNames){
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      
      table.setModel(model);
      
   }
}
