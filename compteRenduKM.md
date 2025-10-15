# Compte Rendu : Projet Kotlin Monsters

## Présentation du projet

Le projet **Kotlin Monsters** consistait à créer un jeu inspiré de Pokémon en utilisant le langage Kotlin. L'objectif principal était de mettre en pratique la programmation orientée objet (POO) et la gestion d'une base de données pour stocker les résultats.

# Todo : faire les differentes section SPRINT 1 /2 /3

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
  


## En plus 
### Prompt chatgpt pour securiser la connexion à la base de donnée en utilisant le token

#### - contexte :

#### Dans la class [BDD.kt](src/main/kotlin/jdbc/BDD.kt), l'id de connexion ainsi que le mdp etaient affiché en clair ce qui pose une faille de securité car n'importe qui pourrait les voir.

**Ma class avant :**
```kotlin
    class BDD( 
        var url: String = "jdbc:mysql://172.16.0.210:3306/db_monsters_vgomessilv", 
        var user: String = "**********", 
        var password: String = "********", )
```
-**Mon prompt chatgpt** :
Salut, Agis en tant qu'expert en cyberSecurité et développer Kotlin. j'ai un projet KotlinMonster qui est un RPG/monster-collector.
J'ai une problématique, j'ai mon identifiant ainsi que mon mot de passe qui est affiché en clair dans ma class BDD, je voudrais les cacher pour que je puisse mettre mon repository en public sur github sans que tout le monde puisse avoir acces a mes id, je pourrais les mettre dans un fichier qui serais dans le gitignore par exemple. Peut-tu m'indiquer comment je peux faire ?

**Ma class après :**
```kotlin 
class BDD(
    var url: String = "",
    var user: String = "",
    var password: String = "",
)
```

##### l'IA ma indiqué de créer un nouveau fichier [config.properties](config.properties) puis d'y mettre ça :

``` properties
db.url=jdbc:mysql://172.16.0.210:3306/db_monsters_vgomessilv
db.user=MonUser
db.password=MyPassword
```
##### Modification de la class BDD :
```kotlin
class BDD(
    var url: String = "",
    var user: String = "",
    var password: String = "",
) {
    init {
        // Chargement des paramètres depuis le fichier config.properties si url, user, password sont vides
        if (url.isEmpty() || user.isEmpty() || password.isEmpty()) {
            val props = java.util.Properties()
            try {
                FileInputStream("config.properties").use { fis ->
                    props.load(fis)
                }
                url = props.getProperty("db.url", "")
                user = props.getProperty("db.user", "")
                password = props.getProperty("db.password", "")
            } catch (e: Exception) {
                println("Erreur lors du chargement du fichier de configuration : ${e.message}")
            }
        }
    }
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

