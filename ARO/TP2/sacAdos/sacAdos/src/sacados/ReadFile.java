package sacados;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile {

    /* 
     * Ouvre un fichier à l'emplacement Chemin 
     * et renvoit un string avec le contenu du fichier
     */
    public static String ouvrirFichier(String chemin) throws IOException {
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
    public static String[] decoupeLigne(String strFile) {
        return strFile.split("\n");
    }

    /* 
     * Decoupe un String à chaque espace
     */
    public static String[] decoupeEspace(String strFile) {
        return strFile.split(" ");
    }

    /*
     * Creation du tableau
     */
    public static float[][] createTable(String[] contenu) {
   
        float[][] objetsFinal = new float[contenu.length-1][2]; // on exclu le poid du sac avec le -1
        String[] temporaire;

        /* objetsFinal[0][0] = taille du sac */
        SacAdos.poidSac = Integer.parseInt(contenu[0].trim());
        
        for (int i = 1; i < contenu.length; i++) {
            temporaire = decoupeEspace(contenu[i]);
            objetsFinal[i-1][0] = Float.parseFloat(temporaire[1]);
            objetsFinal[i-1][1] = Float.parseFloat(temporaire[2]);
        }
        return objetsFinal;
    }

    public static float[][] getTable(String chemin) throws IOException {
        String contenu = ReadFile.ouvrirFichier(chemin);
        String[] tableauObjet = ReadFile.decoupeLigne(contenu);
        float[][] objetsFinal = ReadFile.createTable(tableauObjet);
        return objetsFinal;
    }

}
