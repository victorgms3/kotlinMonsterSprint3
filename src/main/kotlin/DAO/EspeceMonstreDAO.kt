package org.example.DAO

import BDD
import org.example.db
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
        val sql = "SELECT * FROM EspeceMonstre"
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
                val particularite = resultatRequete.getString("particularite")
                val caracteres = resultatRequete.getString("caracteres")
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
                    particularite,
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
            val particularite = resultatRequete.getString("particularite")
            val caracteres = resultatRequete.getString("caracteres")
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
                particularite,
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
                val  particularite = resultatRequete.getString(" particularite")
                val caracteres = resultatRequete.getString("caracteres")
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
                     particularite,
                    caracteres))
            }
        }

        requetePreparer.close()
        return result
    }
    /**
     * Insère ou met à jour une espèce dans la base.
     *
     * @param especeMonstre L'espèce à sauvegarder.
     * @return L'espèce sauvegardée avec son ID mis à jour si insertion.
     */
    fun save(especeMonstre: EspeceMonstre): EspeceMonstre? {
        val requetePreparer: PreparedStatement

        if (especeMonstre.id == 0) {
            // Insertion
            val sql = "INSERT INTO EspeceMonstre (nom, type , baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe , baseDefenseSpe, basePv, modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, description,  particularite, caracteres) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer.setString(1, especeMonstre.nom)
            requetePreparer.setString(2, especeMonstre.type)
            requetePreparer.setInt(3, especeMonstre.baseAttaque)
            requetePreparer.setInt(4, especeMonstre.baseDefense)
            requetePreparer.setInt(5, especeMonstre.baseVitesse)
            requetePreparer.setInt(6, especeMonstre.baseAttaqueSpe)
            requetePreparer.setInt(7, especeMonstre.baseDefenseSpe)
            requetePreparer.setInt(8, especeMonstre.basePv)
            requetePreparer.setDouble(9, especeMonstre.modAttaque)
            requetePreparer.setDouble(10, especeMonstre.modDefense)
            requetePreparer.setDouble(11, especeMonstre.modVitesse)
            requetePreparer.setDouble(12, especeMonstre.modAttaqueSpe)
            requetePreparer.setDouble(13, especeMonstre.modDefenseSpe)
            requetePreparer.setDouble(14, especeMonstre.modPv)
            requetePreparer.setString(15, especeMonstre.description)
            requetePreparer.setString(16, especeMonstre. particularite)
            requetePreparer.setString(17, especeMonstre.caracteres)
        } else {
            // Mise à jour
            val sql = "UPDATE EspeceMonstre SET nom = ?, type = ?, baseAttaque = ?,baseDefense = ?,baseVitesse = ?,baseAttaqueSpe = ?,baseDefenseSpe = ?,basePv = ?,modAttaque = ?,modDefense = ?,modVitesse = ?,modAttaqueSpe = ?,modDefenseSpe = ?,modPv = ?,description = ?, particularite = ?,caracteres = ? WHERE id = ?"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
            requetePreparer.setString(1, especeMonstre.nom)
            requetePreparer.setString(2, especeMonstre.type)
            requetePreparer.setInt(3, especeMonstre.baseAttaque)
            requetePreparer.setInt(4, especeMonstre.baseDefense)
            requetePreparer.setInt(5, especeMonstre.baseVitesse)
            requetePreparer.setInt(6, especeMonstre.baseAttaqueSpe)
            requetePreparer.setInt(7, especeMonstre.baseDefenseSpe)
            requetePreparer.setInt(8, especeMonstre.basePv)
            requetePreparer.setDouble(9, especeMonstre.modAttaque)
            requetePreparer.setDouble(10, especeMonstre.modDefense)
            requetePreparer.setDouble(11, especeMonstre.modVitesse)
            requetePreparer.setDouble(12, especeMonstre.modAttaqueSpe)
            requetePreparer.setDouble(13, especeMonstre.modDefenseSpe)
            requetePreparer.setDouble(14, especeMonstre.modPv)
            requetePreparer.setString(15, especeMonstre.description)
            requetePreparer.setString(16, especeMonstre. particularite)
            requetePreparer.setString(17, especeMonstre.caracteres)
            requetePreparer.setInt(18, especeMonstre.id)
        }

        val nbLigneMaj = requetePreparer.executeUpdate()

        if (nbLigneMaj > 0) {
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                especeMonstre.id = generatedKeys.getInt(1)
            }
            requetePreparer.close()
            return especeMonstre
        }

        requetePreparer.close()
        return null
    }
    /**
     * Supprime une espèce par son identifiant.
     *
     * @param id L'ID de l'espèce à supprimer.
     * @return `true` si la suppression a réussi, sinon `false`.
     */
    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)

        return try {
            val nbLigneMaj = requetePreparer.executeUpdate()
            requetePreparer.close()
            nbLigneMaj > 0
        } catch (erreur: SQLException) {
            println("Erreur lors de la suppression de l'espèce : ${erreur.message}")
            false
        }
    }

    /**
     * Sauvegarde plusieurs espèces dans la base de données.
     *
     * @param espece Liste d'espèces à sauvegarder.
     * @return Liste des espèces sauvegardées.
     */
    fun saveAll(espece: Collection<EspeceMonstre>): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        for (e in espece) {
            val sauvegarde = save(e)
            if (sauvegarde != null) result.add(sauvegarde)
        }
        return result
    }
}