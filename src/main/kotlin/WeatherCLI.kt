import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*

class WeatherCLI(private val apiKey: String) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val weatherApiUrl = "https://api.weatherapi.com/v1"

    suspend fun getWeather(city: String): String {
        return try{
            val response: HttpResponse = client.get(weatherApiUrl) {
                parameter("q", city)
                parameter("appid", apiKey)
                parameter("units", "metrics")
            }

            val weatherData: WeatherResponse = response.body()

            "Weather in ${weatherData.name}: ${weatherData.main.temp}°C, ${weatherData.weather[0].description.capitalize()}"
        } catch (e: Exception){
            "Failed to fetch weather data. Please check your city name or API config"
        }
    }

    fun closeClient(){
        client.close()
    }


@Serializable
private data class WeatherResponse(
    val name: String,
    val weather: List<WeatherDetails>,
    val main: MainDetails
)

@Serializable
private data class WeatherDetails(val description: String)

@Serializable
private data class MainDetails(val temp: Float)
}