import org.example.db
import org.example.dresseur.Entraineur

/**
 * DAO (Data Access Object) permettant d'interagir avec la table `Entraineurs`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */
class EntraineurDAO(val bdd: BDD = db) {
    /**
     * Récupère tous les entraîneurs présents dans la base de données.
     *
     * @return Une liste mutable d'entraîneurs trouvés.
     */
    fun findAll(): MutableList<Entraineur> {
        val result = mutableListOf<Entraineur>()
        val sql = "SELECT * FROM Entraineurs"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val argents = resultatRequete.getInt("argents")
                result.add(Entraineur(id, nom, argents))
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche un entraîneur par son identifiant unique.
     *
     * @param id L'identifiant de l'entraîneur.
     * @return L'entraîneur trouvé ou `null` si aucun résultat.
     */
    fun findById(id: Int): Entraineur? {
        var result: Entraineur? = null
        val sql = "SELECT * FROM Entraineurs WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id) // insere la valeur de l'id dans la requete preparer
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            val nom = resultatRequete.getString("nom")
            val argents = resultatRequete.getInt("argents")
            result = Entraineur(id, nom, argents)
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche un entraîneur par son nom.
     *
     * @param nomRechercher Le nom de l'entraîneur à rechercher.
     * @return Une liste d'entraîneurs correspondant au nom donné.
     */
    fun findByNom(nomRechercher: String): MutableList<Entraineur> {
        val result = mutableListOf<Entraineur>()
        val sql = "SELECT * FROM Entraineurs WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val argents = resultatRequete.getInt("argents")
                result.add(Entraineur(id, nom, argents))
            }
        }

        requetePreparer.close()
        return result
    }



}
