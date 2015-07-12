package com.software.project.service.adapter;

import java.util.UUID;

public class IdGenerator {
   public static int generateUniqueId() {      
       UUID idOne = UUID.randomUUID();
       String str=""+idOne;        
       int uid=str.hashCode();
       String filterStr=""+uid;
       str=filterStr.replaceAll("-", "");
       return Integer.parseInt(str);
   }

   // XXX: replace with java.util.UUID

   public static void main(String[] args) {
       for (int i = 0; i < 5; i++) {
           System.out.println(generateUniqueId());
           //generateUniqueId();
       }
   }

}