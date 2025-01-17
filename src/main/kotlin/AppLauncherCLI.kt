class AppLauncherCLI {

    private val appMappings = mapOf(
        "chrome" to "open -a Google\\Chrome",
        "safari" to "open -a Safari",
        "firefox" to "open -a Firefox",
        "vscode" to "open -a Visual\\Studio\\Code",
        "terminal" to "open -a Terminal",
        "textedit" to "open -a TextEdit",
        "spotify" to "open -a Spotify",
        "notes" to "open -a Notes",
        "finder" to "open -a Finder",
        "mail" to "open -a Mail",
        "calendar" to "open -a Calendar",
        "photos" to "open -a Photos",
        "preview" to "open -a Preview",
        "messages" to "open -a Messages",
        "reminders" to "open -a Reminders",
        "intellij" to "open -a Intellij\\ IDEA \\ CE",
    )

    fun launchApp(appName: String){
        val command = appMappings[appName.lowercase()]
        if (command != null) {
            try {
                val processBuilder = ProcessBuilder(command.split(" "))
                processBuilder.inheritIO() //Pass input/output to the parent terminal
                val process = processBuilder.start()
                process.waitFor() //Wait for the app to open or terminate
            } catch (e:InterruptedException){
                println("App launching interrupted: ${e.message}")
            }
        } else {
            println("App '$appName' not found. ")
        }
    }
}