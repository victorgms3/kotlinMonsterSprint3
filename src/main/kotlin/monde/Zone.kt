package org.example.monde

import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

/**
 * Représente une zone dans le monde du jeu, avec des caractéristiques associées,
 * des monstres spécifiques et une connexion à une éventuelle zone suivante.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Expérience associée à la zone.
 * @property especeMonstre Liste des espèces de monstres pouvant apparaître dans la zone.
 * @property zoneSuivante Référence à la zone suivante liée à cette zone, ou null si aucune.
 */
class Zone (
    var id : Int,
    var nom : String,
    var expZone: Int,
    var especeMonstre: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {

    /**
     * Génère un monstre aléatoire pour la zone actuelle en utilisant les caractéristiques
     * de la zone, telles que l'expérience associée et les espèces de monstres disponibles.
     *
     * @return Un individu de type monstre généré aléatoirement avec une espèce et une expérience déterminées.
     */
    fun genereMonstre() : IndividuMonstre {
        val facteur = (80..120).random() / 100.0
        val exp = expZone * facteur
        val especeChoisi = especeMonstre.random()
        return IndividuMonstre(0, especeChoisi.nom, especeChoisi, null, exp)
    }

    /**
     * Déclenche une rencontre avec un monstre sauvage dans la zone actuelle et initie un combat.
     * Cette méthode génère un monstre aléatoire en fonction des caractéristiques de la zone,
     * sélectionne le premier membre de l'équipe du joueur avec des points de vie restants,
     * puis lance un combat entre eux.
     */
    fun rencontreMonstre(){
        val monstreSauvage = genereMonstre()
        val premierPokemon = joueur.equipeMonstre.find { it.pv > 0 }!!
        val combatMonstre = CombatMonstre(premierPokemon, monstreSauvage)
        combatMonstre.lancerCombat()
    }
}