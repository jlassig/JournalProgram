class Menu {
    private val journal = Journal()

    fun start(){
        println("hi, i work")
        do{
            val menuChoice: String? = displayMenu()
            when(menuChoice){
                "1" -> journal.writeEntry()
                "2" -> journal.writeToPromptEntry()
                "3" -> journal.displayEntries()
                "4" -> println("Thank you. Have a nice day.")
                else -> {
                    println("Please enter a number")
                }
            }
        } while (menuChoice != "4")
    }
    private fun displayMenu(): String?{
        // put all the println for the menu here
        println("1 - Write Entry")
        println("2 - Write To Prompt")
        println("3 - Display Entry")
        println("4 - Quit")
        val menuNumber: String? = readLine()
        //checking to see if the menuNumber is null
        if(menuNumber.isNullOrBlank()){
            println("Please try again. ")
            return displayMenu()
        }
        return menuNumber
    }


}