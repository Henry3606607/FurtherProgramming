package model;

import java.util.Observable;

//@SuppressWarnings("deprecation")
public class ModelImpl extends Observable implements Model
{

   private static Model singletonInstance = null;

   @Override
   public void setData(int i, String data) {

   }

   public static Model getSingletonInstance()
   {
      // lazy instantiation (only when needed)
      if (singletonInstance == null)
         singletonInstance = new ModelImpl();

      return singletonInstance;
   }

   @Override
   public String getData(int i) {
      return null;
   }
}
