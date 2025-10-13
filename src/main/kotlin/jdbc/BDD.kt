import java.io.FileInputStream
import java.sql.*

/**
 * Cette classe gère la connexion à une base de données JDBC et l'exécution de requêtes SQL préparées.
 *
 * @property url L'URL de la base de données.
 * @property user Le nom d'utilisateur pour la connexion à la base de données.
 * @property password Le mot de passe pour la connexion à la base de données.
 */
class BDD(
    var url: String = "",
    var user: String = "",
    var password: String = "",
) {
    var connectionBDD: Connection? = null

    /**
     * Initialise une instance de la classe BDD et établit une connexion à la base de données.
     */
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

        try {
            // Établir une connexion à la base de données lors de la création de l'objet BDD
            this.connectionBDD = getConnection()
        } catch (erreur: SQLException) {
            // Gérer les exceptions liées à la connexion
            println("Erreur lors de la connexion à la base de données : ${erreur.message}")
        }
    }

    /**
     * Crée et retourne une connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
    fun getConnection(): Connection? {
        //Chargement du driver
        Class.forName("com.mysql.cj.jdbc.Driver")
        // Créer et retourner une connexion à la base de données
        return DriverManager.getConnection(url, user, password)
    }

    /**
     * Exécute une requête SQL préparée et retourne le résultat sous forme de ResultSet.
     *
     * @param preparedStatement La requête SQL préparée à exécuter.
     * @return Le résultat de la requête sous forme de ResultSet, ou null en cas d'erreur.
     */
    fun executePreparedStatement(preparedStatement: PreparedStatement): ResultSet? {
        try {
            // Exécuter la requête SQL préparée
            return preparedStatement.executeQuery()
        } catch (erreur: SQLException) {
            // Gérer les exceptions liées à l'exécution de la requête
            println("Une erreur est survenue lors de l'exécution de la requête :")
            println(preparedStatement.toString()) // Afficher la requête pour le débogage
            println(erreur.message) // Afficher le message d'erreur
            return null // Renvoyer null en cas d'erreur
        }
    }

    /**
     * Finalise l'objet BDD en fermant la connexion à la base de données.
     */
    fun close() {
        // Fermer la connexion à la base de données lors de la finalisation de l'objet BDD
        this.connectionBDD?.close()
    }
}
