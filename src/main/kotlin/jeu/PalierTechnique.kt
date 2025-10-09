package org.example.jeu

import org.example.monstre.IndividuMonstre

/**
 * Décrit un palier d’apprentissage d’une technique pour une espèce/individu.
 *
 * Chaque palier associe une technique à un niveau requis. Il fournit une
 * méthode utilitaire pour savoir si un individu peut apprendre la technique.
 *
 * @property id Identifiant du palier.
 * @property niveauRequis Niveau à partir duquel la technique devient disponible.
 * @property technique La technique concernée par ce palier.
 */
class PalierTechnique (
    val id : Int,
    val niveauRequis : Int,
    val technique : Technique
){
    /**
     * Vérifie si un individu monstre peut apprendre une technique
     * en fonction de son niveau.
     *
     * @param individu L'individu monstre à vérifier
     * @return true si le niveau de l'individu correspond à exactement le niveau requis,
     *         false sinon
     */
    fun peutApprendre(individu : IndividuMonstre) : Boolean = individu.niveau == niveauRequis
}