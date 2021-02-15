import java.util.HashMap;
import java.util.Vector;

public class CA {
    private Vector <Ville> listVilles;
    private HashMap <Ville, Vector<Ville>> listeAdjacent;

    /**
     * Constructeur CA (Communauté d'Agglomérations), initialise ses attributs
     */
    public CA () {
        listVilles = new Vector<Ville>();
        listeAdjacent = new HashMap<Ville, Vector<Ville>>();
    }

    /**
     * Modifie la liste d'adjacence de l'agglomération (liste des routes par villes)
     * 
     * @param v Objet ville qui correspond à la "key" de la HashMap
     */
    public void modificationListeAdjacent(Ville v) {
        listeAdjacent.put(v, v.getRoutes());
    }

    /**
     * Ajoute une ville au sein de l'agglomération
     * 
     * @param nom Correspond au nom de la ville
     */
    public void ajouteVille(String nom) {
        Ville v = new Ville(nom);

        if (trouveVille(nom) == null) {
            listVilles.add(v);
        } else {
            System.out.println("La ville "+nom+" existe déjà dans l'agglomération");
        }
    }

    /**
     * Retire une ville au sein de l'agglomération
     * 
     * @param nom Correspond au nom de la ville
     */
    public void retireVille(String nom) {
        Ville ville = trouveVille(nom);

        if (ville != null) {
            if (!ville.getRoutes().isEmpty()) {
                for (Ville villeVoisine : ville.getRoutes()) {
                    villeVoisine.getRoutes().remove(ville);

                    if (!villeVoisine.getRoutes().isEmpty()) {
                        modificationListeAdjacent(villeVoisine);
                    } else {
                        listeAdjacent.remove(villeVoisine);
                    }
                    
                }
    
                ville.getRoutes().removeAllElements();
                listeAdjacent.remove(ville);
            }
            
            listVilles.remove(ville);
            System.out.println("La ville "+nom+" a été correctement supprimée");
        } else {
            System.out.println("L'agglomération ne contient pas la ville "+nom);
        }
    }

    /**
     * Affiche toutes les villes de l'agglomération
     */
    public void afficheVilles() {
        if (!listVilles.isEmpty()) {
            for (Ville ville : listVilles) {
                System.out.println(ville.getNom());
            }
        } else {
            System.out.println("Pas de villes dans cette agglomération !");
        }
    }

    /**
     * Retrouve une ville à partir de son nom dans l'agglomération
     * 
     * @param nom Nom de la ville
     * @return Retourne la ville trouvée, null sinon
     */
    public Ville trouveVille(String nom) {
        Ville ville = null;

        for (Ville v : listVilles) {
            if (v.getNom().equals(nom)) {
                ville = v;
            }
        }

        return ville;
    }

    /**
     * Getter de la liste de villes de l'agglomération
     * 
     * @return Retourne la liste des villes de l'agglomération
     */
    public Vector<Ville> getListeVilles() {
        return listVilles;
    }

    /**
     * Getter de la liste d'adjacence des villes voisines de chaques villes de l'agglomération
     * 
     * @return Retourne la liste des villes voisine (routes)
     */
    public HashMap <Ville, Vector<Ville>> getListeAdjacent() {
        return listeAdjacent;
    }

    /**
     * Affiche les villes voisines de chaques villes (correspond aux routes)
     */
    public void afficheRoutes() {
        if (!listeAdjacent.isEmpty()) {
            listeAdjacent.entrySet().forEach(ville -> {
                System.out.print("Ville "+ville.getKey().getNom()+" ==> ");

                for (Ville villeFor : ville.getValue()) {
                    System.out.print(villeFor.getNom()+" ");
                }

                System.out.println("");
            });
        } else {
            System.out.println("Il n'existe aucunes routes entre les villes");
        }
        
    }
}
