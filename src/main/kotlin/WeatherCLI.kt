import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class WeatherCLI {

    private val weatherApiKey = "80b14529cdb34dfd87d153832250801"
    private val weatherApiUrl = "http://api.weatherapi.com/v1/current.json"

    suspend fun getWeather(city: String) {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
        val jsonParser = Json {
            ignoreUnknownKeys = true
        }

        try {
            val response: HttpResponse = client.get(weatherApiUrl) {
                parameter("q", city)
                parameter("key", weatherApiKey)
                parameter("units", "metric")
            }

            val responseBody = response.bodyAsText()

            val weatherData = jsonParser.decodeFromString<WeatherResponse>(responseBody)

            println("${weatherData.location.name} ${weatherData.current.temp_c}°C ${weatherData.current.condition.text}")
        } catch (e: Exception) {
            println("Error fetching weather data: ${e.message}")
        } finally {
            client.close()
        }
    }

    @Serializable
    data class WeatherResponse(
        val location: Location,
        val current: Current
    )

    @Serializable
    data class Location(
        val name: String
    )

    @Serializable
    data class Current(
        val temp_c: Double,
        val condition: Condition
    )

    @Serializable
    data class Condition(
        val text: String
    )
}
