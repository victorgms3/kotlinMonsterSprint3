package org.example.jeu

import org.example.charge
import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre
import org.example.especeSpringleaf
import org.example.especeflamkip
import org.example.especeaquamy
import org.example.flammeche

/**
 * Représente une session de jeu en cours pour un joueur dans une zone donnée.
 *
 * Elle orchestre le choix du starter, la navigation entre zones, les rencontres,
 * et la gestion de l’équipe (examen et réorganisation).
 *
 * @property id Identifiant de la partie.
 * @property joueur Le dresseur contrôlé par l’utilisateur.
 * @property zone La zone actuellement visitée.
 */
class Partie (
    val id : Int,
    val joueur : Entraineur,
    var zone: Zone
){
    /**
     * Permet au joueur de choisir un monstre de départ parmi trois options proposées. Chaque option
     * correspond à un monstre possédant ses propres caractéristiques. Le joueur visualise les détails
     * des monstres avant de faire son choix. Une fois qu'un choix valide est effectué, le monstre
     * sélectionné est renommé, ajouté à l'équipe du joueur et assigné au joueur en tant qu'entraîneur.
     *
     * Fonctionnalités principales :
     * - Affiche les détails de trois monstres de départ.
     * - Invite le joueur à choisir un monstre parmi trois options.
     * - Vérifie que le choix est valide (entre 1 et 3).
     * - Permet de renommer le monstre sélectionné.
     * - Ajoute le monstre sélectionné à l'équipe du joueur.
     * - Assigne le joueur comme entraîneur du monstre choisi.
     *
     * Mise en garde :
     * - Le processus de sélection ne se termine qu'après l'entrée d'une option valide.
     */
    fun choixStarter(){
        val monstre1 = IndividuMonstre(1, "springleaf", especeSpringleaf,null,0.0)
        val monstre2 = IndividuMonstre(2, "flamkip", especeflamkip,null,0.0)
        val monstre3 = IndividuMonstre(3, "aquamy", especeaquamy,null,0.0)
        println(monstre1.afficheDetail())
        println(monstre2.afficheDetail())
        println(monstre3.afficheDetail())
        var choix : Int
        do {
            println(
                "=========Faites votres choix :=========\n" +
                        "1. springleaf\n" +
                        "2. flamkip\n" +
                        "3. aquamy\n"
            )
            choix = readln().toInt()
        }while (choix !in 1..3)
        var stater: IndividuMonstre? = null
        when(choix){
            1 -> stater = monstre1
            2 -> stater = monstre2
            3 -> stater = monstre3
        }
        stater?.renommer()
        joueur.equipeMonstre.add(stater!!)
        stater.entraineur = joueur
    }

    /**
     * Permet de modifier l'ordre des monstres dans l'équipe du joueur si cette équipe
     * contient plus d'un monstre. Le joueur peut choisir deux monstres et intervertir
     * leur position dans l'équipe. Les choix sont basés sur les indices affichés, et une
     * annulation est possible à tout moment.
     *
     * Fonctionnalités principales :
     * - Affiche la composition actuelle de l'équipe du joueur.
     * - Demande au joueur de choisir deux monstres pour intervertir leurs positions.
     * - Permet au joueur d'annuler l'opération à tout moment.
     * - Met à jour l'ordre de l'équipe après la permutation.
     *
     * Mise en garde :
     * - L'opération ne peut être effectuée que si l'équipe contient au moins deux monstres.
     */
    fun modifierOrdreEquipe(){
        if (joueur.equipeMonstre.size > 1){
            println("Votre Equipe :\n" + joueur.equipeMonstre.mapIndexed { index, individuMonstre -> "${index + 1} : ${individuMonstre.nom}" }.joinToString("\n"))
            println("\nChangement d'ordre de monstres :")
            // premier choix
            println("Faites votre choix  numero 1: \n" +
                    joueur.equipeMonstre.mapIndexed { index, individuMonstre -> "${index + 1} : ${individuMonstre.nom}" }.joinToString("\n"))
            println("0 : Annuler")
            var choix : Int
            do {
                choix = readln().toInt()
            }while (choix !in 0..joueur.equipeMonstre.size)
            if (choix == 0) return
            //deuxieme choix
            var choix2: Int
            do {
                println(
                    "Faites votre choix  numero 2: \n" +
                        joueur.equipeMonstre.mapIndexed { index, individuMonstre -> if (index + 1 != choix) "${index + 1} : ${individuMonstre.nom}" else null
                        }.filterNotNull().joinToString("\n")
                )
                println("0 : Annuler")
                choix2 = readln().toInt()
            }while (choix == choix2 || choix2 !in 0..joueur.equipeMonstre.size)

            if (choix == 0) return

            val temp = joueur.equipeMonstre[choix - 1]
            joueur.equipeMonstre[choix - 1] = joueur.equipeMonstre[choix2 - 1]
            joueur.equipeMonstre[choix2 - 1] = temp

            println("Votre Equipe de monstres :\n" + joueur.equipeMonstre.mapIndexed { index, individuMonstre -> "${index + 1} : ${individuMonstre.nom}" }.joinToString("\n"))
        }
    }

    /**
     * Permet d'examiner en détail l'équipe actuelle de monstres du joueur et d'interagir avec celle-ci.
     *
     * Fonctionnalités principales :
     * - Affiche une liste des monstres présents dans l'équipe avec leurs informations de base (nom, points
     *   de vie, niveau).
     * - Offre plusieurs possibilités d'interaction :
     *    - Consulter les détails complets d'un monstre en entrant son numéro.
     *    - Modifier l'ordre des monstres dans l'équipe en sélectionnant l'option correspondante.
     *    - Quitter l'affichage de l'équipe pour retourner au menu précédent.
     * - Gère les entrées utilisateur en validant les choix pour éviter les erreurs de saisie.
     *
     * L'interaction se répète jusqu'à ce que l'utilisateur choisisse de quitter.
     */
    fun examineEquipe() {
        while (true) {
            println("=== Équipe actuelle ===")
            joueur.equipeMonstre.forEachIndexed { index, monstre ->
                println("${index + 1} : ${monstre.nom} | PV : ${monstre.pv} | Niveau : ${monstre.niveau}")
            }
            println("\nQue voulez-vous faire ?")
            println("- Tapez le numéro du monstre pour voir ses détails.")
            println("- Tapez 'm' pour modifier l'ordre de l'équipe.")
            println("- Tapez 'q' pour quitter.")
            val input = readln().trim().lowercase() //.trim() supprime les espaces en trop
            when {
                input == "q" -> {
                    println("Retour au menu précédent.")
                    return
                }
                input == "m" -> {
                    modifierOrdreEquipe()
                }
                input.toIntOrNull() != null -> { //.toIntOrNull() transforme le texte en entier si possible
                    val choix = input.toInt()
                    if (choix in 1..joueur.equipeMonstre.size) {
                        println(joueur.equipeMonstre[choix - 1].afficheDetail())
                    } else {
                        println("Numéro invalide.")
                    }
                }
                else -> {
                    println("Entrée invalide. Veuillez réessayer.")
                }
            }
        }
    }

    /**
     * Permet au joueur d'interagir avec le jeu dans la zone actuelle en choisissant parmi
     * différentes actions disponibles. Les options proposées permettent au joueur d'explorer
     * et de progresser dans le jeu ou d'accéder à des fonctionnalités spécifiques.
     *
     * Fonctionnalités principales :
     * - Affiche des informations sur la zone actuelle.
     * - Propose un menu avec les choix suivants :
     *   - Rencontrer un monstre sauvage.
     *   - Examiner l’équipe de monstres.
     *   - Aller à la zone suivante si elle existe.
     *   - Aller à la zone précédente si elle existe.
     *   - Quitter le menu.
     * - Valide les choix du joueur pour détecter et gérer les saisies incorrectes.
     * - Permet le déplacement entre les zones ou l'accès à d'autres parties du jeu en fonction
     *   du choix effectué par le joueur.
     *
     * Les actions accessibles dépendent des états et des propriétés actuelles de la session de jeu.
     */
    fun jouer() {
        while (true) {
            println("\nvous etes dans la Zone :${this.zone.nom}")
            var choix: Char = '1'
            do {
                println(
                    "\nVous avez la possibilite de faire les actions suivantes :\n" +
                            "1 : Rencontrer un monstre sauvage \n" +
                            "2 : Examiner l’équipe de monstres\n" +
                            "3 : Aller a la zone suivante\n" +
                            "4 : Aller a la zone precedente\n" +
                            "5 : Quitter"
                )
                choix = readln().trim().lowercase().first()
                if (choix !in "12345") println("Erreur, veuillez entrer un choix valide")
            } while (choix !in "12345")
            choix.let {
                when (it) {
                    '1' -> {
                        zone.rencontreMonstre()
                    }

                    '2' -> {
                        examineEquipe()
                    }

                    '3' -> {
                        if (zone.zoneSuivante != null) {
                            zone = zone.zoneSuivante!!
                            println("Vous avancez vers la zone : ${zone.nom}")
                        } else {
                            println("Il n'y a pas de zone suivante.")
                        }
                    }

                    '4' -> {
                        if (zone.zonePrecedente != null) {
                            zone = zone.zonePrecedente!!
                            println("Vous retournez à la zone : ${zone.nom}")
                        } else println("Il n'y a pas de zone précédente.")
                    }

                    '5' -> {
                        println("Au revoir !")
                        break
                    }

                    else -> println("Erreur, veuillez entrer un choix valide")
                }
            }

        }
    }

}