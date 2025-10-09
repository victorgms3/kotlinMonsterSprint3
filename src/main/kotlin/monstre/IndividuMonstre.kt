package org.example.monstre

import org.example.dresseur.Entraineur
import org.example.jeu.Technique
import org.example.monstre.EspeceMonstre
import kotlin.math.max
import kotlin.random.Random
import kotlin.math.pow

/**
 * Représente une instance de monstre individuel dans un système de jeu.
 * Chaque monstre est basé sur une espèce donnée, mais possède ses propres
 * caractéristiques, statistiques et attributs qui évoluent avec l'expérience.
 *
 * @property id Identifiant unique du monstre.
 * @property nom Nom de l'IndividuMonstre.
 * @property espece Espèce de base du monstre.
 * @property entraineur Référence optionnelle à l'entraîneur ou possesseur du monstre.
 * @property niveau Niveau actuel du monstre, influençant sa puissance.
 * @property technique Liste des techniques que le monstre peut utiliser.
 * @property attaque Statistique représentant la puissance d'attaque.
 * @property defense Statistique représentant la capacité à réduire les dommages subis.
 * @property vitesse Statistique définissant la rapidité d'action.
 * @property attaqueSpe Statistique pour les attaques spéciales.
 * @property defenseSpe Statistique pour la défense spéciale.
 * @property pvMax Nombre maximum de points de vie.
 * @property potentiel Multiplicateur unique pour déterminer l'évolution des statistiques.
 * @property exp Expérience accumulée par le monstre, permettant de monter de niveau.
 * @property pv Points de vie actuels, ajustés en temps réel.
 */
class IndividuMonstre (
    var id : Int,
    var nom : String,
    val espece : EspeceMonstre,
    var entraineur : Entraineur? = null,
    expInit : Double
){
//    companion object {
//       var nbId =1
//    }
    var niveau: Int = 1
    val technique : MutableList<Technique> = mutableListOf<Technique>()
    var attaque: Int = this.espece.baseAttaque + (-2..2).random()
    var defense: Int = this.espece.baseDefense + (-2..2).random()
    var vitesse: Int = this.espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = this.espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = this.espece.baseDefenseSpe + (-2..2).random()

    var pvMax: Int = this.espece.basePv + (-5..5).random()
    var potentiel: Double = Random.nextDouble(0.5, 2.1)

    var exp: Double = 0.0
        get() = field
        set(value){
            field = value

            // Vérifier si on est au niveau 1
            val estNiveau1 = (niveau == 1)

            // Boucle tant que l'expérience dépasse le palier pour monter de niveau
            while (field >= palierExp(niveau)) {
                levelUp()
                // Si on est plus au niveau 1, afficher le message
                if (!estNiveau1) {
                    println("Le monstre $nom est maintenant niveau $niveau !")
                }
            }
        }
    init {
        this.exp = expInit
//        this.id= nbId
//            nbId++
    }

    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv
            if (field < 0) field = 0
            if (field > pvMax) field = pvMax
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */

    fun palierExp(niveau : Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /**
     * Augmente le niveau du monstre en modifiant ses statistiques et ses points de vie.
     *
     * Cette méthode effectue plusieurs actions :
     * 1. Incrémente le niveau du monstre.
     * 2. Calcule l'augmentation de chaque statistique en fonction des modificateurs attribués à l'espèce,
     *    du potentiel individuel du monstre, et d'un facteur de variation aléatoire.
     * 3. Met à jour les points de vie maximum (pvMax) en appliquant une logique similaire.
     * 4. Ajuste les points de vie actuels pour refléter le gain en pvMax, tout en s'assurant qu'ils
     *    ne dépassent pas la nouvelle valeur de pvMax.
     */
    fun levelUp() {
        // 1. Incrémenter le niveau
        niveau++
        for (palier in espece.palierTechnique) {
            if (palier.peutApprendre(this)) apprendreTechnique(palier.technique)
        }

        // 2. Sauvegarder l'ancien pvMax pour calcul du gain
        val ancienPvMax = pvMax

        // 3. Mise à jour des stats
        attaque += (espece.modAttaque * potentiel).toInt() + (-2..2).random()
        defense += (espece.modDefense * potentiel).toInt() + (-2..2).random()
        vitesse += (espece.modVitesse * potentiel).toInt() + (-2..2).random()
        attaqueSpe += (espece.modAttaqueSpe * potentiel).toInt() + (-2..2).random()
        defenseSpe += (espece.modDefenseSpe * potentiel).toInt() + (-2..2).random()
        pvMax += (espece.modPv * potentiel).toInt() + (-5..5).random()

        // 4. Augmenter les PV actuels du gain de pvMax
        val gainPv = pvMax - ancienPvMax
        pv += gainPv
        if (pv > pvMax) pv = pvMax // sécurité
    }

    /**
     * Effectue une attaque sur une cible de type `IndividuMonstre`. Cette méthode peut utiliser une attaque
     * basique ou une technique spéciale en fonction de la disponibilité des techniques.
     *
     * Si aucune technique n'est utilisée, la méthode calcule et applique des dégâts bruts en fonction des
     * statistiques d'attaque de l'attaquant et de défense de la cible. Si une technique est utilisée, elle
     * applique les effets de celle-ci, incluant des dégâts et/ou d'autres modifications d'état.
     *
     * @param cible L'instance de `IndividuMonstre` qui subit l'attaque.
     * @return Ne retourne aucune valeur.
     */
    fun attaque(cible: IndividuMonstre) : Unit {
        if (this.technique.isEmpty()){
            val degatBrut = this.attaque
            var degatTotal = degatBrut - (cible.defense / 2)
            degatTotal = max(degatTotal, 1)
            val pvAvant = cible.pv
            cible.pv -= degatTotal
            val pvApres = cible.pv
            println("${nom} inflige ${pvAvant-pvApres} degats à ${cible.nom}")
        }
        else {
            var choix : Int
            do {
                println(
                    "\nVoici la liste des techniques de ${nom} :\n" +
                            technique.mapIndexed { index, technique -> "${index + 1} : ${technique.nom}" }
                                .joinToString("\n") +
                            "\nVotre choix :"
                )
                choix = readln().toInt()
            }while (choix !in 0..technique.size)
            val techniqueChoisie = technique[choix - 1]
            if (!techniqueChoisie.calculPrecission()){
                println("Attaque echoue")
            }
            else {
                var degats = techniqueChoisie.effet(this, cible)
                if (techniqueChoisie.faireDegats){
                    degats = max(degats -(cible.defense / 2), 1.0)
                    val pvAvant = cible.pv
                    cible.pv -= degats.toInt()
                    val pvApres = cible.pv
                    println("${nom} inflige ${pvAvant-pvApres} degats à ${cible.nom} avec ${techniqueChoisie.nom}")
                }
            }
        }


    }

    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */

    fun renommer() : Unit {
        var choix : String
        do {
            print("Renommer le monstre $nom ? (O/N) : ")
            choix = readln().toString().uppercase()
            if (choix != "O" && choix != "N") {
                println("Choix incorrect, veuillez entrer O ou N")
            }
        }while (choix.uppercase() != "O" && choix.uppercase() != "N")
        if (choix == "O") {
            println("Entrez un nouveau nom : ")
            this.nom = readln()
        }
    }

    /**
     * Affiche les détails complets d'un monstre sous une forme textuelle et artistique.
     *
     * Cette méthode combine la représentation artistique ASCII du monstre, issue de l'espèce associée,
     * avec les informations détaillées telles que le nom, le niveau, les points d'expérience, les
     * points de vie, et les statistiques du monstre. Les deux parties sont alignées et affichées côte-à-côte.
     *
     * L'affichage inclut :
     * - Une séparation visuelle entre les sections.
     * - Les informations clés du monstre : nom, niveau, expérience accumulée et nécessaire pour le
     *   prochain palier, points de vie actuels et maximum.
     * - Les statistiques du monstre : attaque, défense, vitesse, attaque spéciale, et défense spéciale.
     *
     * La méthode utilise les données propres à l'instance du monstre et les enrichit des valeurs de son
     * espèce pour produire un résultat clair et lisible.
     */
    fun afficheDetail(){
        val art = espece.afficheArt()
        val artLines = art.lines()
        val details = listOf("     ==========================",
            "Nom : $nom     Lvl  : $niveau",
            "Exp : $exp / ${palierExp(niveau)}",
            "PV : $pv / $pvMax","==========================",
            "Atq : $attaque  Def : $defense  Vitesse : $vitesse",
            "AtqSpe : $attaqueSpe",
            "DefSpe : $defenseSpe",
            "=========================="
        )
        val maxArtWidth = artLines.maxOf { it.length }
        val maxLines = max(artLines.size, details.size)
        for (i in 0 until maxLines) {
            val artLine = if (i < artLines.size) artLines[i] else ""
            val detailLine = if (i < details.size) details[i] else ""
            val paddedArt = artLine.padEnd(maxArtWidth + 4)
            println(paddedArt + detailLine)
        }
    }
    /**
     * Permet à un monstre de maîtriser une nouvelle technique. Si le monstre a déjà
     * atteint sa capacité maximale de techniques, il devra en oublier une avant d'apprendre
     * la nouvelle.
     *
     * @param nouvelleTechnique La nouvelle technique que le monstre souhaite apprendre.
     */
    fun apprendreTechnique(nouvelleTechnique: Technique){
        if (technique.size <= 4 && !technique.contains(nouvelleTechnique)) {
            technique.add(nouvelleTechnique)
            println("$nom a appris la technique ${nouvelleTechnique.nom}")
        }
        else {
            var indexTechnique : Int
            do {
                println("Quelle technique souhaitez-vous oublier ?")
                technique.forEachIndexed { index, tech ->
                    println("${index + 1} : ${tech.nom}")
                }
                println("0 : Annuler")
                indexTechnique = readln().toInt()
            }while (indexTechnique !in 0..3)
            if (indexTechnique == 0) {
                println("Annulation de l'apprentissage de la technique ${nouvelleTechnique.nom}.")
            } else {
                println("$nom a oublier la technique ${technique[indexTechnique - 1].nom}")
                technique[indexTechnique - 1] = nouvelleTechnique
                println("$nom a appris la technique ${nouvelleTechnique.nom}")
            }
        }
    }
}