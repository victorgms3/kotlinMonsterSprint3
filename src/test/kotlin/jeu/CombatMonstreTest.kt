package jeu

import org.example.charge
import org.example.especeSpringleaf
import org.example.especeflamkip
import org.example.initialiserRelationsElements
import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre1
import org.example.monstre7
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class CombatMonstreTest {
    @BeforeTest
    fun setup() {
        initialiserRelationsElements()
        joueur.equipeMonstre.clear()
        joueur.equipeMonstre.add(monstre7)
        monstre7.technique.add(charge)
        monstre1.technique.add(charge)
    }
    @Test
    fun actionAdversaire() {
        val combat = CombatMonstre(monstre7, monstre1)
        combat.actionAdversaire()
    }

}