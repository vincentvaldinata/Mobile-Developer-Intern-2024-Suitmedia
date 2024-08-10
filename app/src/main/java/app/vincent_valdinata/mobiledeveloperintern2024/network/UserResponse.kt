package app.vincent_valdinata.mobiledeveloperintern2024.network
import app.vincent_valdinata.mobiledeveloperintern2024.models.User

data class UserResponse(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<User>
)