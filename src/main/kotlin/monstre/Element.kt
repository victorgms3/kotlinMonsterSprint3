package org.example.monstre

/**
 * Représente un élément (type) utilisé pour les affinités entre techniques et espèces.
 *
 * Chaque élément possède des ensembles d’éléments contre lesquels il est fort, faible
 * ou inefficace (immunisé). Ces relations servent au calcul des multiplicateurs de dégâts.
 *
 * @property id Identifiant de l’élément.
 * @property nom Nom de l’élément.
 */
class Element (
    val id : Int,
    val nom : String
){
    var forces : MutableSet<Element> = mutableSetOf()
    var faiblesses : MutableSet<Element> = mutableSetOf()
    var immunises : MutableSet<Element> = mutableSetOf()

    /**
     * Détermine l'efficacité d'un élément courant contre un élément cible.
     *
     * Cette méthode compare l'élément cible avec les forces et faiblesses
     * de l'élément courant et retourne un multiplicateur d'efficacité.
     *
     * @param elementCible L'élément contre lequel l'efficacité est évaluée.
     * @return Un multiplicateur indiquant l'efficacité :
     *  2.0 si l'élément cible est dans les forces.
     *  0.5 si l'élément cible est dans les faiblesses.
     *  0.0 si l'élément cible est dans les éléments immunisés.
     *  1.0 si l'élément cible n'est ni une force ni une faiblesse.
     */

    fun efficaciteContre(elementCible : Element) : Double{
        return when(elementCible){
            in forces -> 2.0
            in faiblesses -> 0.5
            in immunises -> 0.0
            else -> 1.0
        }
    }

}