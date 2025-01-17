import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val cli = CliToolsApp()

    if (args.isEmpty()) {
        cli.showHelp()
        return
    }

    cli.handleCommand(args)
}

class CliToolsApp {

    private val helpHandler = HelpHandler()
    private val weatherHandler = WeatherCLI()
    private val appLauncherHandler = AppLauncherCLI()

    fun showHelp() {
        helpHandler.displayHelp()
    }

    fun handleCommand(args: Array<String>) {
        when (args[0]) {
            "--help" -> helpHandler.displayHelp()
            "weather" -> {
                if (args.size > 1) {
                    val city = args[1]
                    runBlocking { weatherHandler.getWeather(city) }
                } else {
                    println("Usage: cli-tools weather <city>")
                }
            }

            "app" -> {
                if (args.size > 1) {
                    val appName = args[1]
                    appLauncherHandler.launchApp(appName)
                } else {
                    println("Usage: cli-tools app <app_name>")
                }
            }
            else -> println("Unknown command: '${args[0]}'. Type '--help' for usage instructions.")
        }
    }
}
