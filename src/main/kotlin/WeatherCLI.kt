import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

class WeatherCLI {

    private val weatherApiKey = "80b14529cdb34dfd87d153832250801"
    private val weatherApiUrl = "http://api.weatherapi.com/v1/current.json"

    suspend fun getWeather(city: String) {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }

        try {
            val response: HttpResponse = client.get(weatherApiUrl) {
                parameter("q", city)
                parameter("appid", weatherApiKey)
                parameter("units", "metric")
            }

            val weatherData: WeatherResponse = response.body()
            println("Weather in ${weatherData.name}: ${weatherData.main.temp}°C, ${weatherData.weather[0].description.capitalize()}")
        } catch (e: Exception) {
            println("Error fetching weather data: ${e.message}")
        } finally {
            client.close()
        }
    }

    @Serializable
    data class WeatherResponse(
        val name: String,
        val weather: List<WeatherDetails>,
        val main: MainDetails
    )

    @Serializable
    data class WeatherDetails(val description: String)

    @Serializable
    data class MainDetails(val temp: Float)
}
