package org.example.monstre


/**
 * importation de Junit pour que ça marche
 */
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.example.feu
import org.example.plante
import org.example.insecte
import org.example.eau
import org.example.roche
import org.example.normal
import kotlin.test.BeforeTest

class ElementTest {
    /**
     * @beforeTest remplace par @BeforeEach, demandé a chatgpt "ajouter elements aux monstres"
     */
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
    }
    @Test
    fun efficaciteContre() {
        assertEquals(  1.0,feu.efficaciteContre(normal))
        assertEquals(  2.0,feu.efficaciteContre(plante))
        assertEquals( 0.5,feu.efficaciteContre(feu)) //changement expected 1.0 en 0.5/ le feu contre le feu est 2x moins efficace donc le bon test est 0.5.
        assertEquals(2.0, insecte.efficaciteContre(plante) )
        //lignes ajoutées
        assertEquals(0.5,feu.efficaciteContre(eau))
        assertEquals(0.5, insecte.efficaciteContre(feu))
        assertEquals(2.0, feu.efficaciteContre(insecte))
        assertEquals(1.0, roche.efficaciteContre(eau))//erreur générer, le programme attend 0.5.
    }

}
