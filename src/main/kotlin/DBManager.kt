import org.jetbrains.exposed.sql.*
internal object DBManager{
    val db = Database.connect(
            "jdbc:mysql://localhost:3306/test",
            driver = "com.mysql.jdbc.Driver",
            user = "root",
            password = "12345")
}