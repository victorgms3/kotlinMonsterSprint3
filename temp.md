# 🧾 Compte Rendu — Kotlin Monsters
## Sprint 2 & Sprint 3

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
