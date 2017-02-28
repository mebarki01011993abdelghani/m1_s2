package tp.projet.pkg1;
/* Simple +/-/* expression language; parser evaluates constant expressions on the fly*/

import java_cup.*;

public class Demonstrateur {


    public static void main(String[] args) {

        String test = "(f    -> c)&r";
 

        System.out.println("Please type your arithmethic expression:");
        Parser p = new Parser(new scanner());
        p.parse();
    
}

}
