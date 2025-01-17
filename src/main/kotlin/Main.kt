import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole
import java.util.Scanner

fun handleCommand(command: String) {
    when (command.trim().lowercase()) {
        "help" -> println("Help: Use 'task', 'organize', or 'sysinfo' to explore.")
        "task" -> println("Task Manager: Coming Soon!")
        "organise" -> println("Organize: Automatically sort your files.")
        "sysinfo" -> println("System Info: Displaying system stats...")
        else -> println("Unknown command. Type `help` for available options.")
    }
}

fun main() {
    AnsiConsole.systemInstall()

    // Display CLI Banner
    println(Ansi.ansi().fg(Ansi.Color.CYAN).a("""
   .-'''-.    ____       ____     __         .---.  .---.     .-''-.    .---.     .---.       ,-----.     
  / _     \ .'  __ `.    \   \   /  /        |   |  |_ _|   .'_ _   \   | ,_|     | ,_|     .'  .-,  '.   
 (`' )/`--'/   '  \  \    \  _. /  '         |   |  ( ' )  / ( ` )   ',-./  )   ,-./  )    / ,-.|  \ _ \  
(_ o _).   |___|  /  |     _( )_ .'          |   '-(_{;}_). (_ o _)  |\  '_ '`) \  '_ '`) ;  \  '_ /  | : 
 (_,_). '.    _.-`   | ___(_ o _)'           |      (_,_) |  (_,_)___| > (_)  )  > (_)  ) |  _`,/ \ _/  | 
.---.  \  :.'   _    ||   |(_,_)'            | _ _--.   | '  \   .---.(  .  .-' (  .  .-' : (  '\_/ \   ; 
\    `-'  ||  _( )_  ||   `-'  /             |( ' ) |   |  \  `-'    / `-'`-'|___`-'`-'|___\ `"/  \  ) /  
 \       / \ (_ o _) / \      /              (_{;}_)|   |   \       /   |        \|        \'. \_/``".'   
  `-...-'   '.(_,_).'   `-..-'               '(_,_) '---'    `'-..-'    `--------``--------`  '-----'     
                                                                                                          
    """).reset())

    // Display App Name and Description
    println("\u001B[92mWelcome to My CLI!\u001B[0m") // Bright Green
    println(Ansi.ansi().fgBrightBlue().a("Version 1.0.0 - A Kotlin CLI to improve your daily productivity").reset())
    println("-".repeat(50))

    // Display Navigation Help
    println(Ansi.ansi().fgBrightYellow().a("Available Commands:").reset())
    println(Ansi.ansi().fgBrightCyan().a("""
        1. help       - Display detailed help information
        2. task       - Manage your tasks
        3. organize   - Organize your files
        4. sysinfo    - Display system information
    """).reset())

    println("-".repeat(50))
    println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Type `help` to get started or type your command!").reset())

    val scanner = Scanner(System.`in`)
    while (true){
        print("> ")
        val command = scanner.nextLine()
        if (command.trim().equals("exit", ignoreCase = true)) break

        handleCommand(command)
    }

    AnsiConsole.systemUninstall()
}
