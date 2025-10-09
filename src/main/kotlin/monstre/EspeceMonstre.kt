package org.example.monstre

import org.example.jeu.PalierTechnique
import java.io.File

/**
 * Représente une espèce de monstre avec diverses caractéristiques, statistiques
 * et informations supplémentaires comme ses particularités, éléments et techniques accessibles.
 *
 * @property id Identifiant unique de l'espèce du monstre.
 * @property nom Nom de l'espèce du monstre.
 * @property type Type principal de l'espèce du monstre.
 * @property baseAttaque Statistique d'attaque de base de l'espèce.
 * @property baseDefense Statistique de défense de base de l'espèce.
 * @property baseVitesse Statistique de vitesse de base de l'espèce.
 * @property baseAttaqueSpe Statistique d'attaque spéciale de base de l'espèce.
 * @property baseDefenseSpe Statistique de défense spéciale de base de l'espèce.
 * @property basePv Statistique de points de vie de base de l'espèce.
 * @property modAttaque Modificateur pour la statistique d'attaque.
 * @property modDefense Modificateur pour la statistique de défense.
 * @property modVitesse Modificateur pour la statistique de vitesse.
 * @property modAttaqueSpe Modificateur pour la statistique d'attaque spéciale.
 * @property modDefenseSpe Modificateur pour la statistique de défense spéciale.
 * @property modPv Modificateur pour les points de vie.
 * @property description Description textuelle de l'espèce.
 * @property particularites Particularités ou traits distinctifs de l'espèce.
 * @property caractères Traits généraux liés à l'espèce.
 * @property elements Liste des éléments associés
 * @property PalierTechnique Liste les paliers de la technique.
 */
class EspeceMonstre (
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    var basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
    val elements: MutableList<Element> = mutableListOf<Element>(),
    var palierTechnique : MutableList<PalierTechnique> = mutableListOf<PalierTechnique>()
)
{
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art=  File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }

}