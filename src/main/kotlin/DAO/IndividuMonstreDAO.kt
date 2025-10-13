import org.example.db
import org.example.DAO.EspeceMonstreDAO
import org.example.dresseur.Entraineur
import org.example.monstre.IndividuMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

//en francais
/**
 * Data Access Object (DAO) for managing `IndividuMonstre` entities.
 * This class provides operations to interact with the database, including retrieval, creation, update, and deletion
 * of `IndividuMonstre` records based on specified criteria.
 *
 * @property bdd The database connection object used for executing queries.
 */
class IndividuMonstreDAO(val bdd: BDD = db) {

    private val entraineurDAO = EntraineurDAO(bdd)
    private val especeDAO = EspeceMonstreDAO(bdd)

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
