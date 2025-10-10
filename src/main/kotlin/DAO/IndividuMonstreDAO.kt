package org.example.DAO

import BDD
import org.example.db
import org.example.dresseur.Entraineur
import org.example.monstre.IndividuMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement
/**
 * DAO (Data Access Object) permettant d'interagir avec la table `IndividuMonstre`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */
class IndividuMonstreDAO (val bdd: BDD = db){
    fun findAll(): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM IndividuMonstre"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val argents = resultatRequete.getInt("argents")
                result.add(Entraineur(id, nom, argents))
                /**
                 * a completer au dessus avec les autres champs de la table IndividuMonstre
                 */
            }
        }

        requetePreparer.close()
        return result
    }
}