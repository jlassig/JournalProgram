class Menu {
    private val journal = Journal()

    //this is our starting point that displays the menu and gets from the user which menu option to run
    fun start(){
        println("*****Journal Program*****")
        do{
            val menuChoice: String? = displayMenu()
            when(menuChoice){
                "1" -> journal.writeEntry()
                "2" -> journal.writeToPromptEntry()
                "3" -> journal.displayEntries()
                "4" -> journal.getMoodInsight()
                "5" -> println("Thank you. Have a nice day.")
                else -> {
                    println("Please enter a number")
                }
            }
        } while (menuChoice != "5")
    }
    private fun displayMenu(): String?{
        //menu choices:
        println("1 - Write Entry")
        println("2 - Write To Prompt")
        println("3 - Display Entries")
        println("4 - Get some Insight")
        println("5 - Quit")
        val menuNumber: String? = readLine()
        //checking to see if the menuNumber is null
        if(menuNumber.isNullOrBlank()){
            println("Please try again. ")
            return displayMenu()
        }
        return menuNumber
    }


}