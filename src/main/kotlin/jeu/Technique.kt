package org.example.jeu

import org.example.monstre.Element
import org.example.monstre.IndividuMonstre
import kotlin.math.max


/**
 * Décrit une technique utilisable par un monstre en combat.
 *
 * Une technique peut infliger des dégâts, être spéciale (basée sur l’attaque spéciale)
 * ou appliquer des effets (buff/debuff). Elle possède une précision et un multiplicateur
 * de puissance, ainsi qu’un élément utilisé pour calculer les affinités.
 *
 * @property id Identifiant de la technique.
 * @property nom Nom de la technique.
 * @property precision Pourcentage de réussite de la technique (0..100).
 * @property multiplicateurDePuissance Modificateur appliqué à la stat d’attaque(ou spéciale).
 * @property estBuff Indique si la technique applique un bonus (non encore implémenté).
 * @property estDebuff Indique si la technique applique un malus (non encore implémenté).
 * @property faireDegats Indique si la technique inflige des dégâts.
 * @property estSpecial Si vrai, utilise l’attaque spéciale plutôt que l’attaque.
 * @property elementTechnique Élément associé pour les calculs d’efficacité.
 */
class Technique (
    val id : Int,
    val nom : String,
    val precision : Double,
    var multiplicateurDePuissance : Double,
    val estBuff : Boolean,
    val estDebuff : Boolean,
    val faireDegats : Boolean,
    val estSpecial : Boolean,
    val elementTechnique: Element
){
    /**
     * Permet de calculer si le joueur a bien choisi la technique.
     * @return `true` si le joueur a bien choisi la technique, sinon `false`.
     */

    fun calculPrecission() : Boolean {
      val nb = (1..100).random()
        return if (nb <= precision) true
        else false
    }

    /**
     * Calcule le bonus d'attaque de même type (STAB) pour un monstre donné.
     * Ce bonus est appliqué lorsque l'élément de l'espèce du monstre correspond à l'élément de la technique.
     *
     * @param individuMonstre L'individu monstre pour lequel le bonus STAB est calculé.
     * @return Le bonus STAB sous forme de Double. Retourne 0.15 si les éléments correspondent, sinon -0.15.
     */
    fun calculBonusStab(individu: IndividuMonstre): Double{
        var bonus = -0.15
        if (elementTechnique in individu.espece.elements)bonus = 0.15
        return max((bonus + multiplicateurDePuissance),0.1)
    }
    /**
     * Applique l’effet principal de la technique :
     * - inflige des dégâts
     * - (TODO) applique des buffs/debuffs
     *
     * @param attaquant IndividuMonstre utilisateur de la technique
     * @param defenseur IndividuMonstre cible de la technique
     * @return Les dégâts infligés (0.0 si pas de dégâts)
     */
    fun effet(attaquant: IndividuMonstre, defenseur: IndividuMonstre): Double {
        var Resultat = 0.0
        var degatsBase: Double = attaquant.attaque.toDouble()
        if (estBuff) {
            TODO("Déclencher buff (futur statut)")
        }
        if (estDebuff) {
            TODO("Déclencher debuff (futur statut)")
        }
        if (faireDegats) {
            if (estSpecial) degatsBase = attaquant.attaqueSpe.toDouble()
            var multiplicateur = calculBonusStab(attaquant)
            var multiElement = elementTechnique.efficaciteContre(defenseur.espece.elements[0])
            if (defenseur.espece.elements.size == 2) multiElement *= elementTechnique.efficaciteContre(defenseur.espece.elements[1])
            Resultat = (degatsBase * multiplicateur)* multiElement
        }
        return Resultat
    }
}