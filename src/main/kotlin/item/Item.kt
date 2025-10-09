package org.example.item

/**
 * Représente un objet de base avec des propriétés communes utilisé dans différents contextes.
 *
 * Une instance de cette classe contient un identifiant unique, un nom, et une description.
 * Cette classe est conçue pour être étendue par d'autres classes représentant des objets
 * spécifiques dans l'application.
 *
 * @property id L'identifiant unique de l'objet.
 * @property nom Le nom de l'objet.
 * @property description La description détaillée de l'objet.
 */

open class Item (
    val id :  Int,
    val nom : String,
    val description : String
) {
}