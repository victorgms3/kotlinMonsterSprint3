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
- `dresseur` → classes liées aux entraîneurs.
- `item` → gestion des objets (items, badges, kubes).
- `jeu` → logique de jeu (combat, partie).
- `monde` → zones, routes et environnements.
- `monstres` → classes des espèces et des individus.

#### 3️⃣ Fonction utilitaire `changeCouleur()`
Création d’une fonction pour colorer le texte dans la console à l’aide des **codes ANSI**, suivie d’un **test fonctionnel** avec différents scénarios (texte rouge, bleu, couleur inconnue…).

---

### 🧩 Développement des classes principales

#### 👤 Classe [Entraineur.kt](src/main/kotlin/dresseur/Entraineur.kt)
- Propriétés : `id`, `nom`, `argents`, `equipeMonstre`, `boiteMonstre`, `sacAItems`.
```kotlin
class Entraineur (
    var id :  Int,
    var nom : String,
    var argents : Int,
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var sacAItems: MutableList<Item> = mutableListOf()
){}
```
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

#### 🌍 Classe [Zone.kt](src/main/kotlin/monde/Zone.kt)
- Représente un lieu de capture avec : `id`, `nom`, `expZone`, `especesMonstres`, `zoneSuivante`, `zonePrecedente`.
- Préparation des méthodes `genereMonstre()` et `rencontreMonstre()` (TODO à compléter).

#### ⚔️ Classe [IndividuMonstre.kt](src/main/kotlin/monstre/IndividuMonstre.kt)
- Représente un monstre individuel avec statistiques, niveau, expérience et points de vie.
- Implémentation de :
    - `palierExp()` et `levelUp()` pour gérer la montée de niveau.
    - Getters/setters pour `pv` et `exp`.
    - Méthodes `attaquer()`, `renommer()` et `afficheDetail()`.
- Tests fonctionnels : création de plusieurs monstres, gestion de l’expérience et des PV, vérification du level-up.

#### 🎒 Classes `Item` et `Badge`
- `Item` : classe de base (id, nom, description).
- `Badge` : sous-classe héritant de `Item` avec ajout du `champion` (le dresseur à battre).

#### 🧰 Interface `Utilisable`
- Définit le contrat d’un objet pouvant être utilisé sur un monstre (méthode `utiliser(cible: IndividuMonstre): Boolean`).

#### 🔴 Classe `MonsterKube`
- Hérite de `Item` et implémente `Utilisable`.
- Ajoute la propriété `chanceCapture` et la méthode `utiliser()` pour tenter de capturer un monstre.

#### ⚔️ Classe `CombatMonstre`
- Gère un combat entre un monstre du joueur et un monstre sauvage.
- Méthodes principales :
    - `gameOver()`
    - `joueurGagne()`
    - `actionAdversaire()` / `actionJoueur()`
    - `afficheCombat()`, `jouer()` et `lancerCombat()`

#### 🧭 Classe `Partie`
- Gère l’état global du jeu (joueur, zone, progression).
- Méthodes principales :
    - `choixStarter()` (choix du monstre de départ).
    - `modifierOrdreEquipe()`
    - `examineEquipe()`
    - `jouer()` pour lancer la boucle de jeu.

#### 🧑‍💻 Fichier `Main.kt`
- Contient l’initialisation des objets : dresseurs, espèces, zones, items.
- Fonction `nouvellePartie()` pour créer une partie et démarrer le jeu.
- Fonction `main()` pour exécuter le programme principal.

---

### ⚗️ Tests fonctionnels
- Test de la fonction `changeCouleur()` avec plusieurs cas.
- Vérification du comportement des classes via le **mode Debug**.
- Test de création et d’évolution des monstres (`levelUp`, `attaquer`, `renommer`).
- Simulation d’un combat complet via `CombatMonstre`.

---

### 🚧 Difficultés rencontrées
- **Utilisation du débogueur** : j’ai eu beaucoup de mal au départ à comprendre comment suivre l’exécution du code, poser des points d’arrêt et analyser les valeurs des variables.  
  Avec la pratique et les explications vues en cours, j’ai fini par bien comprendre son fonctionnement, ce qui m’a beaucoup aidé pour visualiser la logique de mes classes.
- **Getters et setters** : cette partie m’a également posé problème au début, surtout pour comprendre le lien entre les propriétés, le mot-clé `field` et les conditions dans les setters.  
  C’est seulement après les cours suivants que j’ai réussi à mieux saisir leur utilité et leur logique.
- Compréhension des relations entre classes (héritage, dépendances).
- Syntaxe Kotlin parfois déroutante, notamment sur la gestion des propriétés.

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

## 🎯 Sprint 2 — Module « Éléments et Techniques »

### Objectif du module
Mettre en place les mécaniques essentielles du gameplay :
1. **Techniques** → attaques, buffs, debuffs, soins.
2. **Éléments** → Feu, Eau, Plante, etc. avec un système de forces et faiblesses.
3. **Paliers d’expérience** → système de progression (level-up).
4. **CT (Capsules Techniques)** → objets permettant d’apprendre des techniques.

### Points clés du développement
- Création de la **classe `Element`** avec gestion des forces/faiblesses/immunités.
- Méthode `efficaciteContre()` pour calculer l’efficacité entre deux éléments.
- Ajout d’une liste d’éléments dans la classe `EspeceMonstre`.
- Création d’objets `Element` (Feu, Eau, Plante, Insecte, Roche, Normal).
- Introduction des **tests unitaires JUnit5** pour vérifier les comportements (méthodes, efficacité, bonus STAB, etc.).
- Création de la **classe `Technique`** (id, nom, précision, multiplicateur, buffs/debuffs, élément associé).
- Implémentation des méthodes :
    - `calculPrecision()`
    - `calculBonusStab()`
    - `effet()` (inflige des dégâts ou effets spéciaux).
- Ajout de la **classe `PalierTechnique`** et intégration des apprentissages automatiques via le level-up.
- Extension de la **classe `IndividuMonstre`** avec gestion de techniques et apprentissage (`apprendreTechnique()`).
- Création de la **classe `CapsuleTechnique`** héritée de `Item` et implémentant `Utilisable` pour enseigner une technique.

---

## 🧩 Sprint 3 — Base de Données & DAO

### Objectif du module
Connecter le projet Kotlin Monsters à une base de données relationnelle (MySQL/MariaDB) afin de :
- Centraliser les données (entraîneurs, monstres, espèces…).
- Automatiser les opérations CRUD (Create, Read, Update, Delete).
- Utiliser des **DAO (Data Access Objects)** pour interagir avec la BDD.

### Étapes principales
1. **Création de la BDD et des tables** (`Entraineurs`, `EspecesMonstre`, `IndividusMonstre`, `Zone`…).
2. **Insertion de données de base** :
    - Entraîneurs (Bob, Alice, Clara).
    - Espèces (Springleaf, Flamkip, Pyrokip, Aquamy, Bugsyface, Galum…).
    - Individus liés aux entraîneurs.
3. **Connexion à la BDD** via une classe `BDD` (JDBC).
    - Gestion de la connexion et des requêtes préparées.
    - Fermeture sécurisée de la connexion.
4. **Tests unitaires de connexion** pour valider la communication Kotlin ↔ SQL.
5. **Création des DAO** (ex. `EntraineurDAO`) avec méthodes :
    - `findAll()`
    - `findById()`
    - `findByNom()`
    - `save()`
    - `saveAll()`
    - `deleteById()`
6. **Intégration dans le projet** :
    - Connexion automatique à la BDD au lancement.
    - Récupération des données via DAO dans `Main.kt`.
7. **Extension du modèle** : création de `EspeceMonstreDAO` et `IndividuMonstreDAO`.
8. **Création personnelle d’une nouvelle espèce de monstre** avec ASCII art et éléments associés.

---

## 🧠 Résumé général

| Sprint | Thème | Objectif principal | Résultat attendu |
|:--|:--|:--|:--|
| **Sprint 2** | Éléments & Techniques | Ajouter les mécaniques de combat et progression des monstres | Monstres capables d’utiliser des attaques élémentaires avec calculs de dégâts et progression |
| **Sprint 3** | Base de Données & DAO | Connecter le jeu à une base MySQL pour gérer les données de façon persistante | Les entraîneurs, espèces et monstres sont chargés dynamiquement depuis la BDD |

---

**Auteur :** _[Ton nom ici]_  
**Date :** _13/10/2025_  
