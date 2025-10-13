# Compte Rendu : Projet Kotlin Monsters

## Présentation du projet

Le projet **Kotlin Monsters** consistait à créer un jeu inspiré de Pokémon en utilisant le langage Kotlin. L'objectif principal était de mettre en pratique la programmation orientée objet (POO) et la gestion d'une base de données pour stocker les résultats.

## Résultats obtenus

- **Projet réussi** : le jeu fonctionne et répond aux attentes du cahier des charges.
- **Utilisation des requêtes préparées** pour sécuriser les interactions avec la base de données.
- **Affichage personnalisé** : par exemple, l'affichage des techniques d'un monstre avec :
    ```kotlin
    println("\nVoici la liste des techniques de ${nom} :\n" +
            technique.mapIndexed { index, technique -> "${index + 1} : ${technique.nom}" }
            .joinToString("\n") + "\nVotre choix :"
    )
    ```
- **Découverte de nouvelles fonctions** comme `trim()` pour nettoyer les entrées utilisateur :
    ```kotlin
    val input = readln().trim().lowercase()
    ```

## Difficultés rencontrées

- Certaines méthodes n'étaient pas explicites, notamment parce que j'ai terminé le sprint 1 parmi les premiers. Il a fallu adapter et "bidouiller" le code pour que certaines fonctionnalités fonctionnent, comme dans la classe `CombatMonstre` pour la méthode `actionJoueur()`, qui nécessite d'accéder à une variable publique du fichier `main` (`joueur` de type `Entraineur`).
- Comprendre la répartition des variables entre le fichier `main` et les variables publiques a demandé un temps d'adaptation.

## Apprentissage

- Maîtrise de la POO en Kotlin.
- Utilisation des requêtes préparées pour la sécurité.
- Gestion de l'affichage dynamique et personnalisé.
- Nettoyage et gestion des entrées utilisateur.
- Adaptation et résolution de problèmes face à des consignes parfois incomplètes.

## Conclusion

Ce projet m'a permis de progresser en Kotlin, d'améliorer ma compréhension de la POO et de la gestion de base de données, tout en développant ma capacité à résoudre des problèmes techniques de manière autonome.