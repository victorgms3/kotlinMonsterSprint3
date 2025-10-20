# KotlinMonster — README

Un projet Kotlin simulant un univers de monstres avec combat, espèces, techniques, dresseurs, DAO JDBC (MySQL) et ressources visuelles ASCII. Il inclut une couche d’accès aux données, une logique de jeu (combats, techniques, progression), des entités riches et quelques tests.

## Sommaire
- Présentation
- Fonctionnalités
- Architecture du projet
- Prérequis
- Configuration de la base de données
- Build et exécution
- Tests
- Exemples d’utilisation
- Arborescence
- Diagramme et ressources
- Qualité, style et conventions
- Roadmap
- Contribution
- Licence

## Présentation
KotlinMonster est un mini-projet de type RPG/monster-collector. Il offre:
- Une modélisation d’espèces de monstres (statistiques de base, modificateurs, éléments, descriptions).
- Des individus de monstres (stats variables, niveau, PV, techniques, progression via l’XP).
- Des dresseurs (inventaire, argent, équipe de monstres).
- Des techniques (précision, spécial/physique, multiplicateurs, STAB, affinités élémentaires).
- Un système de combat minimaliste.
- Une couche DAO JDBC (MySQL) pour persister espèces, individus et dresseurs.

Le code est en grande partie commenté en français pour faciliter la compréhension.

## Fonctionnalités
- CRUD sur:
  - EspeceMonstre (EspeceMonstreDAO)
  - IndividuMonstre (IndividuMonstreDAO)
  - Entraineur (EntraineurDAO)
- Connexion JDBC MySQL centralisée via la classe BDD.
- Logique de jeu:
  - Calcul de précision de technique
  - Bonus STAB et efficacité élémentaire
  - Dégâts et progression (level up, paliers d’XP)
  - Paliers d’apprentissage de techniques (PalierTechnique)
- Ressources:
  - ASCII art pour certains monstres (front/back)
  - ERD PlantUML de la base de données (ERD.puml)
  - Script SQL (tables.sql)

## Architecture du projet
- Langage: Kotlin (Gradle Kotlin DSL)
- Modules principaux:
  - `jdbc/BDD.kt` — gestion de la connexion JDBC et exécution de requêtes préparées.
  - `DAO/*.kt` — objets d’accès aux données pour EspeceMonstre, IndividuMonstre, Entraineur.
  - `jeu/*.kt` — logique de combat, techniques, paliers, partie.
  - `monstre/*.kt` — modèles de domaine (Element, EspeceMonstre, IndividuMonstre).
  - `dresseur/Entraineur.kt` — modèle du dresseur.
  - `item/*.kt` — items (badge, CT, MonsterKube, etc.).
  - `monde/Zone.kt` — zones de jeu.
  - `Main.kt` — point d’entrée (exemples d’initialisation et de données).
  - `src/main/resources/` — ERD.puml, ASCII art, tables.sql.
  - `src/test/kotlin/` — tests unitaires (ElementTest, TechniqueTest, CombatMonstreTest, BDDTest, IndividuMonstreTest).

## Prérequis
- Java 17+ (recommandé)
- Kotlin (géré par Gradle)
- Gradle 8.x (wrapper inclus `./gradlew`)
- MySQL 8.x et driver JDBC MySQL
  - Le chargement du driver est fait par `Class.forName("com.mysql.cj.jdbc.Driver")` dans `BDD.kt`.

## Configuration de la base de données
- Script de création: `src/main/resources/tables.sql`
- Diagramme ERD (PlantUML): `src/main/resources/ERD.puml`
- Paramètres de connexion par défaut (à adapter):
  - Fichier: `src/main/kotlin/jdbc/BDD.kt`
  - Champs:
    - `url`: ex. `jdbc:mysql://<host>:<port>/<database>`
    - `user`: utilisateur MySQL
    - `password`: mot de passe MySQL

Important sécurité: évitez de commiter des identifiants de prod. Utilisez des variables d’environnement ou un fichier de configuration ignoré par VCS. Exemple:
- Passer les valeurs via `-Ddb.url`, `-Ddb.user`, `-Ddb.password` ou un `.env` (non fourni).
- Adapter BDD.kt pour lire ces propriétés système si nécessaire.

## Build et exécution
- Build complet:
  - Linux/macOS: `./gradlew build`
  - Windows: `gradlew.bat build`
- Lancer les tests: `./gradlew test`
- Exécuter l’application (si une tâche d’exécution est configurée):
  - `./gradlew run`
  - Sinon, lancer `Main.kt` depuis l’IDE.

Notes:
- Assurez-vous que MySQL est accessible et que les tables sont présentes (exécutez `tables.sql`).
- `Main.kt` crée des exemples d’entités (éléments, espèces, techniques) et interagit avec les DAO.

## Tests
- Localisation: `src/test/kotlin/`
- Exemples de tests: `ElementTest.kt`, `jeu/TechniqueTest.kt`, `jeu/CombatMonstreTest.kt`, `jdbc/BDDTest.kt`, `monstre/IndividuMonstreTest.kt`.
- Lancer: `./gradlew test`

Si aucun test ne s’exécute, vérifiez que les noms respectent les conventions Gradle/JUnit et que le plugin Kotlin/JVM est correctement appliqué dans `build.gradle.kts`.

## Exemples d’utilisation
- DAO
```kotlin
val db = BDD()
val especeDao = EspeceMonstreDAO(db)
val toutes = especeDao.findAll()
val espece = especeDao.findById(1)
```
- Individu et progression
```kotlin
val individu = IndividuMonstre(0, "Flamy", espece!!, entraineur = null, expInit = 0.0)
individu.exp += 150.0 // peut déclencher levelUp()
```
- Technique et dégâts
```kotlin
val charge = Technique(1, "Charge", 95.0, 1.0, false, false, true, false, Element(6, "Normal"))
val degats = charge.effet(attaquant, defenseur)
```

## Arborescence (résumé)
```
src/
  main/
    kotlin/
      DAO/
      dresseur/
      item/
      jdbc/
      jeu/
      monde/
      monstre/
      Main.kt
    resources/
      art/... (ASCII)
      ERD.puml
      tables.sql
  test/
    kotlin/
      ... tests
```

## Diagramme et ressources
- ERD (PlantUML): `src/main/resources/ERD.puml`
- ASCII Art: `src/main/resources/art/*`

Vous pouvez prévisualiser `ERD.puml` avec un plugin PlantUML (IDE) ou via un serveur PlantUML.

## Qualité, style et conventions
- Code commenté en français, KDoc pour les classes/méthodes clés.
- DAO utilisent des requêtes préparées et ferment explicitement les ressources.
- Tests unitaires pour les composants de base du domaine et du jeu.

## Roadmap
- Améliorer la configuration BDD (propriétés système/env, profil dev/test).
- Étendre les tests (couverture DAO, mocks de BDD, tests d’intégration).
- Implémenter buffs/debuffs dans `Technique.effet`.
- Système d’inventaire complet et économie (items, monnaies, récompenses).
- IA de combat et boucles de jeu plus riches.
- Packaging exécutable (shadowJar) et tâche `run` stable.

## Contribution
- Forkez le repo, créez une branche (`feat/xxx`, `fix/yyy`), PR avec description claire.
- Respectez le style de code, ajoutez/mettez à jour les tests quand nécessaire.

## Licence
GOMES SILVA Victor BTS SIO22
