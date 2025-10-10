package org.example.DAO

import BDD
import org.example.db
import org.example.dresseur.Entraineur
import org.example.monstre.EspeceMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement
/**
 * DAO (Data Access Object) permettant d'interagir avec la table `EspeceMonstres`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */
class EspeceMonstreDAO(val bdd: BDD = db) {
    /**
     * Récupère tous les espècesMonstre présents dans la base de données.
     *
     * @return Une liste mutable des espèces monstres trouvés.
     */
    fun findAll(): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        val sql = "SELECT * FROM EspeceMonstres "
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val type = resultatRequete.getString("type")
                val baseAttaque = resultatRequete.getInt("baseAttaque")
                val baseDefense = resultatRequete.getInt("baseDefense")
                val baseVitesse = resultatRequete.getInt("baseVitesse")
                val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
                val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
                val basePv = resultatRequete.getInt("basePv")
                val modAttaque = resultatRequete.getDouble("modAttaque")
                val modDefense = resultatRequete.getDouble("modDefense")
                val modVitesse = resultatRequete.getDouble("modVitesse")
                val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
                val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
                val modPv = resultatRequete.getDouble("modPv")
                val description = resultatRequete.getString("description")
                val particularites = resultatRequete.getString("particularites")
                val caracteres = resultatRequete.getString("caractere")
                result.add(EspeceMonstre(
                    id,
                    nom,
                    type,
                    baseAttaque,
                    baseDefense,
                    baseVitesse,
                    baseAttaqueSpe,
                    baseDefenseSpe,
                    basePv,
                    modAttaque,
                    modDefense,
                    modVitesse,
                    modAttaqueSpe,
                    modDefenseSpe,
                    modPv,
                    description,
                    particularites,
                    caracteres))
            }
        }

        requetePreparer.close()
        return result
    }
    /**
     * Recherche une espèce par son identifiant unique.
     *
     * @param id L'identifiant de l'espèce.
     * @return Les espèces trouvées ou `null` si aucun résultat.
     */
    fun findById(id: Int): EspeceMonstre? {
        var result: EspeceMonstre? = null
        val sql = "SELECT * FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id) // insere la valeur de l'id dans la requete preparer
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            val nom = resultatRequete.getString("nom")
            val type = resultatRequete.getString("type")
            val baseAttaque = resultatRequete.getInt("baseAttaque")
            val baseDefense = resultatRequete.getInt("baseDefense")
            val baseVitesse = resultatRequete.getInt("baseVitesse")
            val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
            val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
            val basePv = resultatRequete.getInt("basePv")
            val modAttaque = resultatRequete.getDouble("modAttaque")
            val modDefense = resultatRequete.getDouble("modDefense")
            val modVitesse = resultatRequete.getDouble("modVitesse")
            val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
            val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
            val modPv = resultatRequete.getDouble("modPv")
            val description = resultatRequete.getString("description")
            val particularites = resultatRequete.getString("particularites")
            val caracteres = resultatRequete.getString("caractere")
            result = EspeceMonstre(
                id,
                nom,
                type,
                baseAttaque,
                baseDefense,
                baseVitesse,
                baseAttaqueSpe,
                baseDefenseSpe,
                basePv,
                modAttaque,
                modDefense,
                modVitesse,
                modAttaqueSpe,
                modDefenseSpe,
                modPv,
                description,
                particularites,
                caracteres)
        }

        requetePreparer.close()
        return result
    }
    /**
     * Recherche une espèce par son nom.
     *
     * @param nomRechercher Le nom de l'espèce à rechercher.
     * @return Une liste d'espèces correspondant au nom donné.
     */
    fun findByNom(nomRechercher: String): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        val sql = "SELECT * FROM EspeceMonstre WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val type = resultatRequete.getString("type")
                val baseAttaque = resultatRequete.getInt("baseAttaque")
                val baseDefense = resultatRequete.getInt("baseDefense")
                val baseVitesse = resultatRequete.getInt("baseVitesse")
                val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
                val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
                val basePv = resultatRequete.getInt("basePv")
                val modAttaque = resultatRequete.getDouble("modAttaque")
                val modDefense = resultatRequete.getDouble("modDefense")
                val modVitesse = resultatRequete.getDouble("modVitesse")
                val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
                val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
                val modPv = resultatRequete.getDouble("modPv")
                val description = resultatRequete.getString("description")
                val particularites = resultatRequete.getString("particularites")
                val caracteres = resultatRequete.getString("caractere")
                result.add(EspeceMonstre(id,
                    nom,
                    type,
                    baseAttaque,
                    baseDefense,
                    baseVitesse,
                    baseAttaqueSpe,
                    baseDefenseSpe,
                    basePv,
                    modAttaque,
                    modDefense,
                    modVitesse,
                    modAttaqueSpe,
                    modDefenseSpe,
                    modPv,
                    description,
                    particularites,
                    caracteres))
            }
        }

        requetePreparer.close()
        return result
    }


}