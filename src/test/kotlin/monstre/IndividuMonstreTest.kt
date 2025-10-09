package org.example.monstre.monstre

import org.example.eau
import org.example.feu
import org.example.jeu.PalierTechnique
import org.example.jeu.Technique
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import org.example.roche
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IndividuMonstreTest {
    @Test
    fun levelUp() {
            // 1. Crée une technique à apprendre au niveau 2
            val techFeu = Technique(1, "Lance-Flamme", 100.0, 1.0, false, false, false, false, feu)

            // 2. Crée un palier associé à cette technique
            val palier = PalierTechnique(1, 2, techFeu)

            // 3. Crée une espèce avec ce palier
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
            "Sérieux, stoïque, fiable"
        )
        especegalum.palierTechnique.add(palier)

            // 4. Crée un monstre de cette espèce avec assez d'XP pour monter au niveau 2
            val monstre = IndividuMonstre(1, "Testy", especegalum, null, expInit = 100.0)

            // 5. Vérifie qu'il a bien appris la technique
            val nomsTechniques = monstre.technique.map { it.nom }
            assertTrue(nomsTechniques.contains("Lance-Flamme"))
    }

}