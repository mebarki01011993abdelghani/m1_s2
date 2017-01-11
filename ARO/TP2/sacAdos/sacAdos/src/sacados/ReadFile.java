
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile {

    /* 
     * Ouvre un fichier à l'emplacement Chemin 
     * et renvoit un string avec le contenu du fichier
     */
    public String ouvrirFichier(String chemin) throws IOException {
        String line;
        String contenu = "";
        try (BufferedReader in = new BufferedReader(new FileReader(chemin))) {
            while ((line = in.readLine()) != null) {
                contenu = contenu + " " + line + "\n";
            }
        }
        return contenu;
    }

    /* 
     * Decoupe un String à chaque retour à la ligne 
     */
    private String[] decoupeLigne(String strFile) {
        return strFile.split("\n");
    }

    /* 
     * Decoupe un String à chaque retour à la ligne 
     */
    private String[] decoupeEspace(String strFile) {
        return strFile.split(" ");
    }

    /*
     * Creation du tableau
     */
    public String[][] createTable(String[] contenu) {
        String[][] objetsFinal = new String[contenu.length][2];
        String[] temp;
        
        /* objetsFinal[0][0] = taille du sac */
        objetsFinal[0][0] = contenu[0];
        for (int i = 1; i < contenu.length; i++) {
            temp = decoupeEspace(contenu[i]);
            objetsFinal[i][0] = temp[0];
            objetsFinal[i][1] = temp[1];
        }
        return objetsFinal;
    }

    public static void main(String[] args) throws IOException {
      
        /* Initialisation */
        ReadFile file = new ReadFile();
        String chemin = "sac1.txt";
        String contenu = file.ouvrirFichier(chemin);
        String[] tableauObjet = file.decoupeLigne(contenu);
        String[][] objetsFinal = file.createTable(tableauObjet);
        System.out.println(objetsFinal[15][1]);
    }
}
