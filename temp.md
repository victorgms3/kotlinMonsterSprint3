# 🧾 Compte Rendu — Kotlin Monsters
## 🎮 Sprint 1 : Création du noyau du projet

---

### 🎯 Objectif du sprint
Mettre en place le **noyau du jeu** et les **bases de la programmation orientée objet en Kotlin**.  
L’objectif principal était de construire la structure initiale du projet : configuration, création des classes principales et premiers tests fonctionnels.

---

### ⚙️ Étapes de réalisation

#### 1️⃣ Création et configuration du projet
- Création du projet Kotlin dans **IntelliJ IDEA**.
- Ajout du **proxy** dans le fichier `gradle.properties` pour permettre le téléchargement des dépendances.
- Initialisation du dépôt **Git** et première mise en ligne.

#### 2️⃣ Structure du projet
Organisation en packages pour une meilleure lisibilité du code :
- `dresseur` -> classes liées aux entraîneurs.
- `item` -> gestion des objets (items, badges, kubes).
- `jeu` -> logique de jeu (combat, partie).
- `monde` -> zones, routes et environnements.
- `monstres` -> classes des espèces et des individus.

#### 3️⃣ Fonction utilitaire `changeCouleur()`
Création d’une fonction pour colorer le texte dans la console à l’aide des **codes ANSI**, suivie d’un **test fonctionnel** avec différents scénarios (texte rouge, bleu, couleur inconnue…).

---

### 🧩 Développement des classes principales

#### 👤 Classe [Entraineur.kt](src/main/kotlin/dresseur/Entraineur.kt)
- Propriétés : `id`, `nom`, `argents`, `equipeMonstre`, `boiteMonstre`, `sacAItems`.

- Méthode : `afficheDetail()` pour afficher les informations du dresseur.
```kotlin
fun afficheDetail(){
        println("Dresseur : ${this.nom}")
        println("Argents : ${this.argents}")
    }
```
- Test en **mode Debug** pour comprendre la notion de `this` et le comportement des objets.


- Classe assez simple à créer, j'avais mis en TODO equipeMonstre, boiteMonstre et sacAItems en attendant la création des autres classes.
#### 🐉 Classe [EspeceMonstre.kt](src/main/kotlin/monstre/EspeceMonstre.kt)
- Définition des caractéristiques de base d’une espèce (attaques, défenses, PV…).

- Méthode `afficheArt()` pour afficher l’art ASCII associé au monstre (face ou dos).
```kotlin
fun afficheArt(deFace: Boolean=true): String{
    val nomFichier = if(deFace) "front" else "back";
    val art=  File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
    val safeArt = art.replace("/", "∕")
    return safeArt.replace("\\u001B", "\u001B")
}
```

#### 🌍 Classe [Zone.kt](src/main/kotlin/monde/Zone.kt)
- Représente un lieu de capture avec : `id`, `nom`, `expZone`, `especesMonstres`, `zoneSuivante`, `zonePrecedente`.
- Préparation des méthodes `genereMonstre()` et `rencontreMonstre()`.
```kotlin
fun genereMonstre() : IndividuMonstre {
        val facteur = (80..120).random() / 100.0
        val exp = expZone * facteur
        val especeChoisi = especeMonstre.random()
        return IndividuMonstre(0, especeChoisi.nom, especeChoisi, null, exp)
    }
```

#### ⚔️ Classe [IndividuMonstre.kt](src/main/kotlin/monstre/IndividuMonstre.kt)
- Représente un monstre individuel avec statistiques, niveau, expérience et points de vie.
- Implémentation de :
    - `palierExp()` et `levelUp()` pour gérer la montée de niveau.
    - Getters/setters pour `pv` et `exp`.
    - Méthodes `attaquer()`, `renommer()` et `afficheDetail()`.
- Tests fonctionnels : création de plusieurs monstres, gestion de l’expérience et des PV, vérification du level-up.

#### 🎒 Classes [Item.kt](src/main/kotlin/item/Item.kt) et [Badge.kt](src/main/kotlin/item/Badge.kt)
- `Item` : classe mère (id, nom, description).
- `Badge` : classe héritant de `Item` avec ajout du `champion` (le dresseur à battre).

#### 🧰 Interface [Utilisable.kt](src/main/kotlin/item/Utilisable.kt)
- Définit le contrat d’un objet pouvant être utilisé sur un monstre (méthode `utiliser(cible: IndividuMonstre): Boolean`).

#### 🔴 Classe [MonsterKube.kt](src/main/kotlin/item/MonsterKube.kt)
- Hérite de `Item` et implémente `Utilisable`.
- Ajoute la propriété `chanceCapture` et la méthode `utiliser()` pour tenter de capturer un monstre.

#### ⚔️ Classe [CombatMonstre.kt](src/main/kotlin/jeu/CombatMonstre.kt)
- Gère un combat entre un monstre du joueur et un monstre sauvage.
- Méthodes principales :
    - `gameOver()`
    - `joueurGagne()`
    - `actionAdversaire()` / `actionJoueur()`
    - `afficheCombat()`, `jouer()` et `lancerCombat()`

#### 🧭 Classe [Partie.kt](src/main/kotlin/jeu/Partie.kt)
- Gère l’état global du jeu (joueur, zone, progression).
- Méthodes principales :
    - `choixStarter()` (choix du monstre de départ).
    - `modifierOrdreEquipe()`
    - `examineEquipe()`
    - `jouer()` pour lancer la boucle de jeu.

#### 🧑‍💻 Fichier [Main.kt](src/main/kotlin/Main.kt)
- Contient l’initialisation des objets : dresseurs, espèces, zones, items (Changé du à l'avancement dans le projet avec la bdd).
- Fonction `nouvellePartie()` pour créer une partie et démarrer le jeu.
- Fonction `initialiserRelationsElements` pour initialiser les differents éléments aux especes créer.

---

### ⚗️ Tests fonctionnels
- Vérification du comportement des classes via le **mode Debug**.
- Test de création et d’évolution des monstres (`levelUp`, `attaquer`, `renommer`).
- Simulation d’un combat complet via `CombatMonstre` en boostant les statistiques de certain monstre (pour les bien faits des differents test).

---

### 🚧 Difficultés rencontrées
- **Utilisation du débogueur** : j’ai eu beaucoup de mal au départ à comprendre comment suivre l’exécution du code, poser des points d’arrêt et analyser les valeurs des variables.  
  Avec la pratique et les explications vues en cours, j’ai fini par bien comprendre son fonctionnement, ce qui m’a beaucoup aidé pour visualiser la logique de mes classes.
- **Getters et setters** : cette partie m’a également posé problème au début, surtout pour comprendre le lien entre les propriétés, le mot-clé `field` et les conditions dans les setters.  
  C’est seulement après les cours suivants que j’ai réussi à mieux saisir leur utilité et leur logique.
- Compréhension des relations entre classes (héritage, dépendances).
- Syntaxe Kotlin parfois déroutante, notamment sur la gestion des propriétés.
- 

---

### 📚 Compétences acquises
- Structuration d’un projet Kotlin orienté objet.
- Documentation du code avec **KDoc**.
- Utilisation du **debugger** pour analyser le comportement du programme.
- Gestion d’objets, d’héritage et d’interfaces.
- Création de tests fonctionnels simples pour valider les comportements.

---

### ✅ Bilan du Sprint 1
Le noyau du jeu est **entièrement fonctionnel** :
- Les classes principales sont créées et interconnectées.
- Le jeu peut se lancer avec une partie complète, des combats et des interactions de base.
- Les fondations sont prêtes pour les Sprints 2 et 3 (éléments, techniques, DAO, base de données).
---

## 🎮 Sprint 2 : Éléments et Techniques

---

### 🎯 Objectif du sprint
Implémenter les mécaniques essentielles du combat et de la progression : éléments, techniques, paliers d’expérience et capsules techniques.

---

### ⚙️ Étapes de réalisation

#### 1️⃣ Modèle des éléments
- Création de la classe `Element` avec gestion des forces, faiblesses et immunités.
- Méthode `efficaciteContre()` pour calculer l’efficacité entre deux éléments.
- Définition des éléments de base : Feu, Eau, Plante, Insecte, Roche, Normal.

#### 2️⃣ Modèle des techniques
- Création de la classe `Technique` (id, nom, précision, multiplicateur, effets, élément associé).
- Implémentation des méthodes :
  - `calculPrecision()`
  - `calculBonusStab()` (bonus de même type)
  - `effet()` (dégâts, buffs, debuffs, soins…)

#### 3️⃣ Progression des monstres
- Ajout de la classe `PalierTechnique` pour les apprentissages automatiques.
- Extension d’`IndividuMonstre` avec gestion de la liste de techniques et apprentissage (`apprendreTechnique()`).

#### 4️⃣ Objets liés au gameplay
- Création de `CapsuleTechnique` héritant de `Item` et implémentant `Utilisable` pour enseigner une technique.

---

### 🧩 Développement des classes principales
- `Element` -> relations d’efficacité et API simple pour les calculs.
- `Technique` -> encapsulation des paramètres d’attaque et d’effets.
- `PalierTechnique` -> mapping niveau -> technique acquise.
- `IndividuMonstre` -> intégration des techniques, prise en compte du STAB et de l’élément.
- `CapsuleTechnique` -> logique d’enseignement conditionnel (compatibilité, doublons…).

---

### ⚗️ Tests unitaires
- Mise en place de tests JUnit5 pour vérifier :
  - l’efficacité élémentaire (`efficaciteContre()`),
  - le bonus STAB (`calculBonusStab()`),
  - la précision et l’application des effets (`calculPrecision()`, `effet()`),
  - l’apprentissage automatique via `PalierTechnique` et manuel via `CapsuleTechnique`.

---

### 🚧 Difficultés rencontrées
- Déterminer une table d’efficacité cohérente et la rendre extensible.
- Gérer les arrondis et priorités entre précision, STAB et multiplicateurs.
- Éviter les effets cumulés indésirables (buff/debuff en boucle).

---

### 📚 Compétences acquises
- Modélisation d’un système d’éléments et d’attaques.
- Écriture de tests unitaires ciblés sur des règles métier.
- Conception d’API orientées domaine (combat, progression).

---

### ✅ Bilan du Sprint 2
Les mécaniques de combat sont opérationnelles avec un système d’éléments robuste et des techniques testées. Les monstres progressent par paliers et peuvent apprendre de nouvelles attaques via des capsules.

---

## 🎮 Sprint 3 : Base de données & DAO

---

### 🎯 Objectif du sprint
Connecter le projet à une base relationnelle (MySQL/MariaDB) et structurer l’accès aux données via des DAO.

---

### ⚙️ Étapes de réalisation

#### 1️⃣ Schéma et données
- Conception de la BDD et création des tables : `Entraineurs`, `EspecesMonstre`, `IndividusMonstre`, `Zone`, etc.
- Insertion de données initiales (entraîneurs, espèces, individus…).

#### 2️⃣ Accès à la base
- Classe `BDD` (JDBC) pour gérer la connexion, les requêtes préparées et la fermeture sécurisée.
- Tests unitaires de connexion pour valider la communication Kotlin ↔ SQL.

#### 3️⃣ Couche DAO
- `EntraineurDAO` avec : `findAll()`, `findById()`, `findByNom()`, `save()`, `saveAll()`, `deleteById()`.
- Extension avec `EspeceMonstreDAO` et `IndividuMonstreDAO`.

#### 4️⃣ Intégration applicative
- Connexion automatique au lancement de l’app.
- Récupération et hydratation des modèles dans `Main.kt` via les DAO.
- Synchronisation basique des modifications (save/update/delete).

#### 5️⃣ Contenu additionnel
- Création d’une nouvelle espèce personnelle avec ASCII art et éléments associés.

---

### 🧩 Développement des classes principales
- `BDD` -> gestion centralisée des connexions JDBC.
- DAO par entité (`EntraineurDAO`, `EspeceMonstreDAO`, `IndividuMonstreDAO`).
- Adaptations mineures des modèles pour l’hydratation depuis la BDD.

---

### ⚗️ Tests unitaires
- Vérification de la connexion et des opérations CRUD de chaque DAO.
- Jeux de données de test pour garantir l’isolation des cas.

---

### 🚧 Difficultés rencontrées
- Gestion des ressources JDBC (fuites de connexions, fermetures tardives).
- Mapping objet-relationnel sans ORM, en gardant le code lisible.
- Cohérence référentielle lors des insertions multiples (ordre, clés étrangères).

---

### 📚 Compétences acquises
- Manipulation de JDBC et requêtes préparées.
- Conception d’une couche DAO propre et testable.
- Intégration d’une BDD dans une application Kotlin existante.

---

### ✅ Bilan du Sprint 3
L’application charge et persiste les données via une BDD relationnelle. Les DAO encapsulent proprement les accès, et l’intégration dans le flux de jeu est opérationnelle.

---

## 🧠 Résumé général

| Sprint       | Thème | Objectif principal | Résultat attendu |
|:-------------|:--|:--|:--|
| **Sprint 1** | Création du noyau du projet | Mettre en place les bases du jeu et les classes principales | Le noyau du jeu est fonctionnel : les classes (dresseur, monstre, zone, item, combat…) sont créées et interconnectées |
| **Sprint 2** | Éléments & Techniques | Ajouter les mécaniques de combat et progression des monstres | Monstres capables d’utiliser des attaques élémentaires avec calculs de dégâts et progression |
| **Sprint 3** | Base de Données & DAO | Connecter le jeu à une base MySQL pour gérer les données de façon persistante | Les entraîneurs, espèces et monstres sont chargés dynamiquement depuis la BDD |

---

**Auteur :** _GOMES SILVA Victor_  
**Date :** _13/10/2025_  
