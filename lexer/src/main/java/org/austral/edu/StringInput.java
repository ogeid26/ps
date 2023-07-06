package org.austral.edu;

import org.austral.edu.InputProvider;

public class StringInput implements InputProvider {

     public StringInput(String string){
         this.string = string;
     }
     private String string;

        @Override
        public String getContent(){
            return string;
        }

        public StringInput(){}



     public void setString(String string) {
         this.string = string;
     }
 }
