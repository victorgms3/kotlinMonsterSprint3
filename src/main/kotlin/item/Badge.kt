package org.example.item

import org.example.dresseur.Entraineur

/**
 * Représente une récompense obtenue par un entraîneur après avoir accompli un objectif précis.
 *
 * Une instance de cette classe est associée à un identifiant unique, un nom, une description,
 * et est détenue par un entraîneur spécifique.
 *
 * @constructor Crée une instance de Badge avec les informations spécifiées.
 *
 * @property champion L'entraîneur qui possède ce badge.
 */

class Badge(
    id: Int,
    nom: String,
    description: String,
    var champion: Entraineur
)
    : Item(id,nom,description) {

}