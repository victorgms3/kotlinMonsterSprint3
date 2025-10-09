package org.example.monstre.jeu

import org.example.eau
import org.example.especeSpringleaf
import org.example.especeflamkip
import org.example.especepikachu
import org.example.feu
import org.example.insecte
import org.example.jeu.Technique
import org.example.joueur
import org.example.monstre.IndividuMonstre
import org.example.normal
import org.example.plante
import org.example.roche
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest


class TechniqueTest {
    @BeforeTest
    fun valorisation() {
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
        especeflamkip.elements.add(feu)
        especeSpringleaf.elements.add(plante)
    }
    @Test
    fun calculPrecission() {
        val technique100 = Technique(1, "test", 100.0, 1.0, false, false, false, false,eau)
        val technique0 = Technique(2, "test", 0.0, 1.0, true, false, false, false,eau)
        val technique50 = Technique(3, "test", 50.0, 1.0, false, false, false, false,eau)
        var compteurT50 = 0
        repeat(100) {
            assertTrue(technique100.calculPrecission())
            assertFalse(technique0.calculPrecission())
           if (technique50.calculPrecission()) compteurT50++
        }
        assertTrue(compteurT50 in 21 until 80)
    }
    @Test
    fun calculBonusStab(){
        val techFeu = Technique(1, "feu", 100.0, 1.0, false, false, false, false,feu)
        val monstreFeu = IndividuMonstre(0, "feu", especeflamkip, joueur, 0.0)
        val monstrePlante = IndividuMonstre(0, "plante", especeSpringleaf, joueur, 0.0)
        val bonusFeu = techFeu.calculBonusStab(monstreFeu)
        val bonusPlante = techFeu.calculBonusStab(monstrePlante)
        assertEquals(bonusFeu, 1.15)
        assertEquals(bonusPlante, 0.85)
    }
    @Test
    fun testEffetInfligeDegats() {
        val techPlante = Technique(3, "Technique plante", 100.0, 1.0, false, false, true, false, plante)

        val techPlante1 = Technique(3, "Technique plante", 100.0, 1.0, false, false, true, true, plante)

        val monstreFeu = IndividuMonstre(1, "attaquant", especeflamkip, joueur, 0.0)
        val monstrePlante = IndividuMonstre(2, "defenseur", especeSpringleaf, joueur, 0.0)

        //Pour eviter les variations de score d'attaque
        monstreFeu.attaque = 10
        monstrePlante.attaque = 10
        monstreFeu.attaqueSpe = 15
        monstrePlante.attaqueSpe = 15

        val degats1 = techPlante.effet(monstreFeu, monstrePlante)
        //degats1 = (10 * 0.85) * 1.0 =8.5
        println(degats1)
        assertTrue(degats1 == 8.5)

        val degats2 = techPlante.effet(monstrePlante, monstreFeu)
        //degat2 = (10 * 1.15) * 0.5 =5.75
        println(degats2)
        assertTrue(degats2 == 5.75)

        val degatSpecial = techPlante1.effet(monstrePlante, monstreFeu)
        println(degatSpecial)
        assertTrue(degatSpecial == 8.625)




    }
}