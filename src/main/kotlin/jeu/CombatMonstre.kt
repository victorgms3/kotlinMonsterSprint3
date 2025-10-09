package org.example.jeu
import org.example.joueur

import org.example.dresseur.Entraineur
import org.example.item.CapsuleTechnique
import org.example.item.Utilisable
import org.example.monstre.IndividuMonstre
import kotlin.math.max

/**
 * Gère un combat entre le monstre du joueur et un monstre sauvage.
 *
 * Cette classe orchestre les tours, l'ordre d'action selon la vitesse,
 * l'affichage de l'état du combat et les conditions de fin (victoire/défaite/capture).
 *
 * @property monstreJoueur Le monstre actuellement envoyé par le joueur.
 * @property monstreSauvage Le monstre rencontré dans la zone (adversaire).
 */
class CombatMonstre (
    var monstreJoueur : IndividuMonstre,
    val monstreSauvage : IndividuMonstre
) {
    var round : Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */

    fun gameOver() : Boolean{
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) return false
        }
        return true
    }

    /**
     * Vérifie si le joueur a gagné le combat.
     *
     * Conditions de victoire :
     * - Le monstre sauvage adverse n'a plus de PV.
     * - Le monstre sauvage a été capturé par le joueur.
     *
     * @return `true` si le joueur a gagné, sinon `false`.
     */
    fun joueurGagne() : Boolean{
        if (monstreSauvage.pv <= 0){
            println("\n${joueur.nom} a gagne !")
            val gainExp = monstreSauvage.exp * 0.2
            monstreJoueur.exp+=gainExp
            println("${monstreJoueur.nom} gagne ${gainExp.toInt()} exp.")
            return true
        }
        else {
            if (monstreSauvage.entraineur == joueur ){
                println("\n${monstreSauvage.nom} a ete capturer !")
                return true
            }
            else return false
        }
    }

    /**
     * Lance l'attaque de l'adversaire.
     */

    fun actionAdversaire(){
        if (monstreSauvage.pv > 0) {
            if (monstreSauvage.technique.isEmpty()) {
                monstreSauvage.attaque(monstreJoueur)
            }
            else {
                val techSauvage = monstreSauvage.technique.random()
                val pvAvant = monstreJoueur.pv
                // Calcul de l'effet principal de la technique
                var degats = techSauvage.effet(monstreSauvage, monstreJoueur)
                // Application de la réduction par la défense si la technique inflige des dégâts
                if (techSauvage.faireDegats) {
                    degats = max(degats - (monstreJoueur.defense / 2), 1.0)
                    monstreJoueur.pv -= degats.toInt()
                }
                val pvApres = monstreJoueur.pv
                println("${monstreSauvage.nom} attaque ${monstreJoueur.nom} avec ${techSauvage.nom} : ${pvAvant - pvApres} PV")
            }
            if (!gameOver() && monstreJoueur.pv <= 0 ){
                monstreJoueur = joueur.equipeMonstre.find { it.pv > 0 }!!
            }
        }
    }


    fun actionJoueur(): Boolean {
        var choixValide = false
        do {
            choixValide = true
            if (gameOver()) return false
            println(
                "\n Entrez votre action :\n" +
                        "1. Attaquer le monstre adverse\n" +
                        "2. Utiliser un item\n" +
                        "3. Changer de monstre\n"
            )
            val action = readln().toInt()

            when (action) {
                1 -> {
                    monstreJoueur.attaque(monstreSauvage)
                }

                2 -> {
                    println("Faites votre choix :\n" +
                            joueur.sacAItems.mapIndexed { index, item -> "${index + 1} : ${item.nom}" }.joinToString("\n"))
                    println("0 : Annuler")
                    val indexChoix = readln().toInt() - 1
                    if (indexChoix == -1) {
                        println("Annulation de l'action.")
                        choixValide = false
                    } else {
                        val objetChoisi = joueur.sacAItems[indexChoix]
                        if (objetChoisi is Utilisable) {
                            if (objetChoisi is CapsuleTechnique){
                                objetChoisi.utiliser(monstreJoueur)
                                return true
                            }
                            val captureReussie = objetChoisi.utiliser(monstreSauvage)
                            if (captureReussie) return false
                        } else {
                            println("L'objet choisi n'est pas utilisable.")
                            choixValide = false
                        }
                    }
                }

                3 -> {
                    val monstresVivants = joueur.equipeMonstre.filter { it.pv > 0 && it != monstreJoueur }
                    if (monstresVivants.isEmpty()) {
                        println("Aucun autre monstre disponible pour le changement.")
                        choixValide = false
                    } else {
                        println("Faites votre choix :\n" +
                                monstresVivants.mapIndexed { index, monstre -> "${index + 1} : ${monstre.nom}" }.joinToString("\n"))
                        println("0 : Annuler")
                        val indexChoix = readln().toInt() - 1
                        if (indexChoix == -1) {
                            println("Annulation du changement de monstre.")
                            choixValide = false
                        } else {
                            val choixMonstre = monstresVivants[indexChoix]
                            println("${choixMonstre.nom} remplace ${monstreJoueur.nom}")
                            println("Affichage de l'art de dos de ${choixMonstre.nom}:")
                            println(choixMonstre.espece.afficheArt(false))
                            monstreJoueur = choixMonstre
                        }
                    }
                }

                else -> {
                    println("Action invalide.")
                    choixValide = false
                }
            }
        } while (!choixValide)
        return true
    }


    /**
     * Affiche les informations relatives à l'état actuel du combat entre le joueur et le monstre sauvage.
     *
     * Les informations affichées comprennent :
     * - Le numéro du round en cours.
     * - Le niveau et le pourcentage de PV restant du monstre sauvage.
     * - La représentation artistique ASCII des deux monstres (de face et de dos pour le monstre sauvage).
     * - Le niveau et le pourcentage de PV restant du monstre du joueur.
     *
     * Cette méthode est utilisée pour mettre à jour et afficher l'état du combat de manière visuelle et informative.
     */

    fun afficheCombat(){
        println("\n===== Debut du round $round =====")
        println("Niveau : ${monstreSauvage.niveau} \n PV : ${monstreSauvage.pv} / ${monstreSauvage.pvMax.toDouble()}")
        println(monstreSauvage.espece.afficheArt(true))
        println(monstreJoueur.espece.afficheArt(false))
        println("Niveau : ${monstreJoueur.niveau} \n PV : ${monstreJoueur.pv} / ${monstreJoueur.pvMax.toDouble()}")
        println("===================\n")
    }

    /**
     * Gère l'exécution du tour de combat entre le joueur et le monstre adverse.
     *
     * La méthode détermine l'ordre d'action des participants en fonction de leur vitesse,
     * affiche l'état du combat, effectue les actions de chaque participant,
     * et interrompt le déroulement si une condition de fin de combat est remplie.
     *
     * Le joueur et le monstre adverse agissent tour à tour, en fonction de leur vitesse respective :
     * - Si le monstre du joueur a une vitesse égale ou supérieure à celle du monstre adverse,
     *   le joueur agit en premier, suivi par l'adversaire.
     * - Sinon, l'adversaire agit en premier, suivi par le joueur.
     *
     * Le combat se poursuit tant que les conditions de victoire ou de défaite ne sont pas remplies.
     *
     * @return Rien (`Unit`)
     */
    fun jouer() : Unit{
        var continuer : Boolean
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            afficheCombat()
            continuer = this.actionJoueur()
            if (continuer == false) return
            this.actionAdversaire()
        }
        else {
            this.actionAdversaire()
            afficheCombat()
            if (gameOver() == false) {
                continuer = this.actionJoueur()
                if (continuer == false) continuer = actionJoueur()
            }
            else return
        }
    }

    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la
    défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les
    PV
     * de tous ses monstres.
     */

    fun lancerCombat(){
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("\n======== Fin du Round : $round ========\n")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }
}