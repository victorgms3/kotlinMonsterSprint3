package org.example.DAO

import BDD
import org.example.db
import org.example.monstre.EspeceMonstre
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
class IndividuMonstreDAO {
}