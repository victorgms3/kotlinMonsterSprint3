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
    //Page 16

}
