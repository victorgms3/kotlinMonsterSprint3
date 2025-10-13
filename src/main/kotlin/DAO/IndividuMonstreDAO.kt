import org.example.db
import org.example.DAO.EspeceMonstreDAO
import org.example.dresseur.Entraineur
import org.example.monstre.IndividuMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

/**
 * Classe de gestion des opérations liées à la table `IndividuMonstre` dans la base de données.
 *
 * Cette classe fournit des méthodes pour exécuter les opérations CRUD (Create, Read, Update, Delete)
 * sur les enregistrements de la table `IndividuMonstre`. Elle permet de manipuler les entités
 * `IndividuMonstre` et de les relier avec leurs entités associées, telles que les espèces
 * ou les entraîneurs.
 *
 * @constructor Initialise une instance de `IndividuMonstreDAO` avec une base de données donnée.
 * @property bdd Instance de la base de données (`BDD`) utilisée pour exécuter les requêtes.
 */
class IndividuMonstreDAO(val bdd: BDD = db) {

    private val entraineurDAO = EntraineurDAO(bdd)
    private val especeDAO = EspeceMonstreDAO(bdd)

    /**
     * Retournes toutes les instances d' `IndividuMonstre` de la bdd.
     *
     * @return Liste mutables qui contient avec tout les elements d'`IndividuMonstre` instances.
     *         si il n'y as pas d'entrer trouvé, une liste vide est return.
     */
    fun findAll(): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM IndividuMonstre"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val especeId = resultatRequete.getInt("espece_id")
                val entraineurId = resultatRequete.getInt("entraineur_id")
                val expInit = resultatRequete.getDouble("expInit")

                val espece = especeDAO.findById(especeId)
                val entraineur = if (entraineurId != 0) entraineurDAO.findById(entraineurId) else null

                if (espece != null) {
                    val monstre = IndividuMonstre(id, nom, espece, entraineur, expInit)
                    result.add(monstre)
                }
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Récupère une instance d'`IndividuMonstre` depuis la base de données à partir de son identifiant unique.
     *
     * @param idRecherche L'identifiant unique de l'`IndividuMonstre` à récupérer.
     * @return L'instance d'`IndividuMonstre` correspondant à l'ID fourni, ou `null` si aucun enregistrement ne correspond.
     */
    fun findById(idRecherche: Int): IndividuMonstre? {
        var result: IndividuMonstre? = null
        val sql = "SELECT * FROM IndividuMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, idRecherche)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            val id = resultatRequete.getInt("id")
            val nom = resultatRequete.getString("nom")
            val especeId = resultatRequete.getInt("espece_id")
            val entraineurId = resultatRequete.getInt("entraineur_id")
            val expInit = resultatRequete.getDouble("expInit")

            val espece = especeDAO.findById(especeId)
            val entraineur = if (entraineurId != 0) entraineurDAO.findById(entraineurId) else null

            if (espece != null) {
                result = IndividuMonstre(id, nom, espece, entraineur, expInit)
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Récupère une liste d'objets `IndividuMonstre` dont le nom correspond au terme recherché.
     *
     * @param nomRechercher Le nom du monstre à rechercher.
     * @return Une liste mutable d'`IndividuMonstre` correspondant au nom fourni.
     *         Si aucune correspondance n'est trouvée, une liste vide est retournée.
     */
    fun findByNom(nomRechercher: String): MutableList<IndividuMonstre> {
        var result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM IndividuMonstre WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val especeId = resultatRequete.getInt("espece_id")
                val entraineurId = resultatRequete.getInt("entraineur_id")
                val expInit = resultatRequete.getDouble("expInit")

                val espece = especeDAO.findById(especeId)
                val entraineur = if (entraineurId != 0) entraineurDAO.findById(entraineurId) else null

                if (espece != null) {
                    result.add(IndividuMonstre(id, nom, espece, entraineur, expInit))
                }
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Enregistre ou met à jour une instance d'IndividuMonstre dans la base de données.
     * Si l'instance possède un ID égal à 0, elle est insérée comme un nouvel enregistrement.
     * Sinon, l'enregistrement existant correspondant à l'ID est mis à jour.
     *
     * @param monstre L'instance d'IndividuMonstre à enregistrer ou mettre à jour.
     * @return L'instance d'IndividuMonstre mise à jour, ou null si l'opération a échoué.
     */
    fun save(monstre: IndividuMonstre): IndividuMonstre? {
        val requetePreparer: PreparedStatement
        val especeId = monstre.espece.id
        val entraineurId = monstre.entraineur?.id

        if (monstre.id == 0) {
            // Insertion
            val sql = "INSERT INTO IndividuMonstre (nom, espece_id, entraineur_id, expInit) VALUES (?, ?, ?, ?)"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer.setString(1, monstre.nom)
            requetePreparer.setInt(2, especeId)
            if (entraineurId != null) {
                requetePreparer.setInt(3, entraineurId)
            } else {
                requetePreparer.setNull(3, java.sql.Types.INTEGER)
            }
            requetePreparer.setDouble(4, monstre.exp)
        } else {
            // Mise à jour
            val sql = "UPDATE IndividuMonstre SET nom = ?, espece_id = ?, entraineur_id = ?, expInit = ? WHERE id = ?"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
            requetePreparer.setString(1, monstre.nom)
            requetePreparer.setInt(2, especeId)
            if (entraineurId != null) {
                requetePreparer.setInt(3, entraineurId)
            } else {
                requetePreparer.setNull(3, java.sql.Types.INTEGER)
            }
            requetePreparer.setDouble(4, monstre.exp)
            requetePreparer.setInt(5, monstre.id)
        }

        val nbLigneMaj = requetePreparer.executeUpdate()

        if (nbLigneMaj > 0) {
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                monstre.id = generatedKeys.getInt(1)
            }
            requetePreparer.close()
            return monstre
        }

        requetePreparer.close()
        return null
    }

    /**
     * Supprime un enregistrement dans la table `IndividuMonstre` correspondant à l'ID fourni.
     *
     * @param id L'identifiant unique de l'enregistrement à supprimer.
     * @return `true` si la suppression s'est déroulée avec succès, `false` sinon.
     */
    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM IndividuMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)

        return try {
            val nbLigneMaj = requetePreparer.executeUpdate()
            requetePreparer.close()
            nbLigneMaj > 0
        } catch (ex: SQLException) {
            println("Erreur lors de la suppression de l'individu : ${ex.message}")
            false
        }
    }
}
