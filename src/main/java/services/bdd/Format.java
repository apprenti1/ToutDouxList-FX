package services.bdd;

import java.util.ArrayList;

public class Format {

    public static boolean verif(String text){
        if ((   text.indexOf('"')+
                text.indexOf("'")+
                text.indexOf('\n')+
                text.indexOf('\r')+
                text.indexOf('\t')+
                text.indexOf('(')+
                text.indexOf(')'))==-7 &&  !text.equals("")){
            return true;
        } else{ return false;}}

    public static boolean verif(String[] texts){
        for (String text : texts) {if (!verif(text)) {return false;}}
        return true;
    }
}
