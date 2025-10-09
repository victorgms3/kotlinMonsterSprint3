package org.example.item

import org.example.jeu.Technique
import org.example.monstre.IndividuMonstre

class CapsuleTechnique(
    id: Int,
    nom: String,
    description: String,
    val techniqueEnCapsule: Technique
):
    Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        cible.apprendreTechnique(techniqueEnCapsule)
        return true
    }
}