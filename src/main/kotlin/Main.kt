package org.example

import BDD
import org.example.item.Badge
import org.example.dresseur.Entraineur
import org.example.item.CapsuleTechnique
import org.example.item.Item
import org.example.item.MonsterKube
import org.example.jeu.CombatMonstre
import org.example.jeu.PalierTechnique
import org.example.jeu.Partie
import org.example.jeu.Technique
import org.example.monstre.EspeceMonstre
import org.example.monde.Zone
import org.example.monstre.Element
import org.example.monstre.IndividuMonstre

val db = BDD()
//Declaration des Entraineurs
var joueur = Entraineur(1, "Sacha", 10, mutableListOf(), mutableListOf(), mutableListOf())
var rival = Entraineur(2, "rival", 10, mutableListOf(), mutableListOf(), mutableListOf())

//Declaration des Elements :
var feu = Element(1, "Feu")
var plante = Element(3, "Plante")
var eau = Element(2, "Eau")
var insecte = Element(4, "Insecte")
var roche = Element(5, "Roche")
var normal = Element(6, "Normal")
var electrique = Element(7, "Electrique")


// ⚪ Normal
val charge = Technique(
    id = 1,
    nom = "Charge",
    precision = 95.0,
    multiplicateurDePuissance = 1.0,
    estSpecial = false,
    estBuff = false,
    estDebuff = false,
    faireDegats = true,
    elementTechnique = normal
)

// 🔥 Feu
val flammeche = Technique(
    id = 2,
    nom = "Flammèche",
    precision = 90.0,
    multiplicateurDePuissance = 1.2,
    estSpecial = true,
    estBuff = false,
    estDebuff = false,
    faireDegats = true,
    elementTechnique = feu
)

//Declaration des EspeceMonstre

val especeSpringleaf = EspeceMonstre(
    1,
    "Springleaf",
    "Graine",
    9,
    11,
    10,
    12,
    14,
    60,
    6.5,
    9.0,
    8.0,
    7.0,
    10.0,
    34.0,
    "Petit monstre espiègle rond comme une graine, adore le soleil.",
    "Sa feuille sur la tête indique son humeur.",
    "Curieux, amical, timide",
    elements = mutableListOf(plante)
)
val especeflamkip = EspeceMonstre(
    4,
    "Flamkip",
    "Animal",
    50,
    12,
    8,
    13,
    16,
    7,
    22.0,
    10.0,
    5.5,
    9.5,
    9.5,
    6.5,
    "Petit animal entouré de flammes, déteste le froid.",
    "Sa flamme change d’intensité selon son énergie",
    "Impulsif, joueur, loyal",
    elements = mutableListOf(feu)
)
val especeaquamy = EspeceMonstre(
    7,
    "Aquamy",
    "Meteo",
    55,
    10,
    11,
    9,
    14,
    14,
    27.0,
    9.0,
    10.0,
    7.5,
    12.0,
    12.0,
    "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s’endormant.",
    "Calme, rêveur, mystérieux",
    elements = mutableListOf(eau)
)
val especelaoumi = EspeceMonstre(
    8,
    "Laoumi",
    "Animal",
    58,
    11,
    10,
    9,
    8,
    11,
    23.0,
    11.0,
    8.0,
    7.0,
    6.0,
    11.5,
    "Petit ourson au pelage soyeux, aime se tenir debout.",
    "Son grognement est mignon mais il protège ses amis.",
    "Affectueux, protecteur, gourmand",
    elements = mutableListOf(normal)
)
val especebugsyface = EspeceMonstre(
    10,
    "Bugsyface",
    "Insecte",
    45,
    10,
    13,
    8,
    7,
    13,
    21.0,
    7.0,
    11.0,
    6.5,
    8.0,
    11.5,
    "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",
    "Sa carapace devient plus dure après chaque mue.",
    "Travailleur, sociable, infatigable",
    elements = mutableListOf(insecte)
)
val especegalum = EspeceMonstre(
    13,
    "Galum",
    "Minéral",
    55,
    12,
    15,
    6,
    8,
    12,
    13.0,
    9.0,
    13.0,
    4.0,
    6.5,
    10.5,
    "Golem ancien de pierre, yeux lumineux en garde.",
    "Peut rester immobile des heures comme une statue.",
    "Sérieux, stoïque, fiable",
    elements = mutableListOf(insecte)
)
val especepikachu = EspeceMonstre(
    25,
    "Pikachu",
    "Électrique",
    55,
    40,
    90,
    50,
    50,
    90,
    6.0,
    6.0,
    6.0,
    4.0,
    3.5,
    4.0,
    "Petit rongeur électrique agile et vif.",
    "Stocke l’électricité dans ses joues, peut électrocuter l’adversaire.",
    "Joueur, curieux, loyal",
    elements = mutableListOf(electrique)
)

// Declaration des PalierTechnique
val paliersFlamkip = mutableListOf<PalierTechnique>(
    PalierTechnique(3, 3, charge),
    PalierTechnique(4, 6, flammeche)
)

//Declaration des Monstres

val monstre1 = IndividuMonstre(1, "springleaf", especeSpringleaf,joueur,1500.0)
val monstre2 = IndividuMonstre(2, "flamkip", especeflamkip,null,1500.0)
val monstre3 = IndividuMonstre(3, "aquamy", especeaquamy,null,1500.0)
val monstre4 = IndividuMonstre(4, "laoumi", especelaoumi, null, 1500.0)
val monstre5 = IndividuMonstre(5, "bugsyface", especebugsyface, null, 1500.0)
val monstre6 = IndividuMonstre(6, "galum", especegalum, null, 0.0)
val monstre7 = IndividuMonstre(7, "pikachu", especepikachu, joueur, 150000.0)

val route1 = Zone(1,
    "Zone1",
    100,
    mutableListOf(especeSpringleaf, especeaquamy)
)
val route2 = Zone(2,
    "Zone2",
    800,
    mutableListOf(especebugsyface, especegalum)
)
val route3 = Zone(3,
    "Zone3",
    1000,
    mutableListOf(especepikachu)
)


var kube1 = MonsterKube(1, "Kube", "Kube pour capturer un monstre", 50.0)
var capsuleTest = CapsuleTechnique(1, "test" , "test", charge)


//Creation d'un objet monstre
fun main() {
    /**
     * Lancement de la partie.
     */
    //------------------
    /**
    *route1.zoneSuivante = route2
    *route2.zonePrecedente = route1
    *joueur.sacAItems.add(kube1)
    *val partie = nouvellePartie()
    *partie.choixStarter()
    *partie.jouer()
    */
    //================================
    /**
     * Tests de la classe CombatMonstre.
     *     val tech3 = Technique(3, "test2", 100.0, 1.0, false, false, false, false, eau)
     *     val tech2 = Technique(2, "test", 100.0, 1.0, false, false, false, false, eau)
     *     val technique1 = Technique(1, "test1", 100.0, 1.0, false, false, false, false, eau)
     *     println(monstre1.apprendreTechnique(technique1))
     *     println(monstre1.apprendreTechnique(tech2))
     *     println(monstre1.apprendreTechnique(tech3))
     *     println(monstre1.apprendreTechnique(technique1))
     */
    joueur.sacAItems.addAll(listOf(kube1, capsuleTest))
    initialiserRelationsElements()
    especeflamkip.palierTechnique.addAll(paliersFlamkip)
    especeflamkip.basePv = 500
    db.close()
    val testJouer = nouvellePartie()
    testJouer.choixStarter()
    testJouer.jouer()
}


/**
 * Démarre une nouvelle partie avec des paramètres de jeu par défaut. L'utilisateur peut choisir
 * de modifier le nom du joueur avant le début de la partie.
 *
 * @return Une instance de la classe Partie initialisée avec les paramètres définis.
 */
fun nouvellePartie() : Partie{
    println("Bienvenue dans la partie de monstre !")
    var choix : String
    do {
        println("Voulez vous changez votre nom ? o/n").toString()
        choix = readln().uppercase()
    } while (choix != "O" && choix != "N")
    if (choix == "O") {
        println("Entrez votre nouveau nom : ")
        val nom = readln().toString()
        joueur.nom = nom
    }
    return Partie(1, joueur, route1)
}


/**
 * Initialise les relations entre les différents types d'éléments et leurs forces et faiblesses respectives.
 *
 * Cette méthode configure les interactions entre les types en remplissant leurs collections `forces` et `faiblesses`.
 * Chaque type obtient une liste de types contre lesquels il est fort (`forces`) et une liste de types contre
 * lesquels il est faible (`faiblesses`). Ces relations définissent les avantages et désavantages lors des combats
 * entre les différents types.
 *
 * Relations définies :
 * - Feu : Fort contre Plante et Insecte ; Faible contre Eau, Roche et Feu.
 * - Plante : Fort contre Eau et Roche ; Faible contre Feu, Insecte et Plante.
 * - Eau : Fort contre Feu et Roche ; Faible contre Plante et Eau.
 * - Insecte : Fort contre Plante ; Faible contre Feu, Roche et Insecte.
 * - Roche : Fort contre Feu et Insecte ; Faible contre Eau, Plante et Roche.
 * - Normal : Faible contre Roche.
 */
fun initialiserRelationsElements() {

    // 🔥 Feu
    feu.forces.addAll(listOf(plante, insecte))
    feu.faiblesses.addAll(listOf(eau, roche,feu))

    // 🌱 Plante
    plante.forces.addAll(listOf(eau, roche))
    plante.faiblesses.addAll(listOf(feu, insecte))

    // 💧 Eau
    eau.forces.addAll(listOf(feu, roche))
    eau.faiblesses.addAll(listOf(plante))

    // 🐞 Insecte
    insecte.forces.addAll(listOf(plante))
    insecte.faiblesses.addAll(listOf(feu, roche))

    // 🪨 Roche
    roche.forces.addAll(listOf(feu, insecte))
    roche.faiblesses.addAll(listOf(eau, plante))

    // ⚪ Normal
    normal.faiblesses.add(roche)

    // Electrique
    electrique.forces.add(eau)
    electrique.faiblesses.addAll(listOf(plante, electrique))

    especeSpringleaf.elements.add(plante)
    especeflamkip.elements.add(feu)
    especeaquamy.elements.add(eau)
    especelaoumi.elements.add(normal)
    especebugsyface.elements.add(insecte)
    especegalum.elements.add(roche)
    especepikachu.elements.add(electrique)


}