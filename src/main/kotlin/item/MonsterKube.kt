package org.example.item

import org.example.dresseur.Entraineur
import org.example.item.Utilisable
import org.example.monstre.IndividuMonstre
import org.example.joueur

/**
 * Objet consommable permettant de tenter la capture d’un monstre sauvage.
 *
 * La probabilité de capture dépend des PV restants de la cible et du paramètre
 * [chanceCapture]. Implémente [Utilisable] pour être utilisé en combat.
 *
 * @param id Identifiant de l’objet.
 * @param nom Nom affiché de l’objet.
 * @param description Description textuelle.
 * @param chanceCapture Facteur de base influençant la probabilité de capture.
 */
class MonsterKube (
    id: Int,
    nom: String,
    description: String,
    var chanceCapture : Double
): Item(id,nom,description), Utilisable {

    /**
     * Utilise la Monster Kube pour tenter de capturer un monstre. La capture dépend d'un calcul basé sur
     * le ratio des points de vie restants du monstre et un facteur de chance prédéfini.
     *
     * @param cible l'individu monstre cible de la capture.
     * @param joueur l'entraîneur qui tente de capturer le monstre.
     * @return true si le monstre est capturé avec succès, sinon false.
     */

    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("\nVous lancez la Monster Kube !")
        if (cible.entraineur != null) println("\nLe monstre ne peut pas être capturé.")
        else {
            val ratioVie = cible.pv/cible.pvMax
            var chanceEffective  = chanceCapture * (1.5 - ratioVie)
            if (chanceEffective < 5) chanceEffective = 5.0
            val nbAleatoire = (0..100).random()
            if (nbAleatoire < chanceEffective){
                println("\nLe monstre est capture !")
                cible.renommer().toString()
                if (joueur.equipeMonstre.size <= 6) joueur.equipeMonstre.add(cible)
                cible.entraineur = joueur
                return true
            }
            else {
                println("\nPresque ! Le Kube n'a pas pu capturer le monstre !")
            }
        }
        return false
    }
}