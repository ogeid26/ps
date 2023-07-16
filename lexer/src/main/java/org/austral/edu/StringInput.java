package org.austral.edu;
public class StringInput implements InputProvider {
    private String string;


    // TODO Cómo hacer si se pasan uno por uno los caracteres

    public StringInput(String string){
         this.string = string;
     }
    public void setString(String string) {
         this.string = string;
    }
    @Override
    public String getContent(){
        return string;
    }

    public StringInput(){}

 }
