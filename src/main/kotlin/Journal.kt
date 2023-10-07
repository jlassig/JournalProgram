import java.time.LocalDate
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import java.io.IOException


class Journal {

    private val today: LocalDate = LocalDate.now()
    private val fileName = "src/main/kotlin/entries.json"
    private val entryFile = File(fileName)

    //this function gets the users mood and what they are thinking about. Then a prompt is created in the prompt
    //picker which is then displayed. the user writes their entry. The date, mood, moodItem, prompt, and entry are
    //all saved.
    fun writeEntry(){
        //getMood
        println("What is your mood today?")
        val moodTracker = MoodTracker()
        val mood = moodTracker.getMood()
        //getMoodItem
        println("You mentioned you feel ${mood}. What is on your mind?")
        val moodItem = moodTracker.getMoodItem()
        val promptInfo = PromptPicker().createAPrompt(mood, moodItem)
        println(promptInfo)

        val entryText = readlnOrNull()
        val newEntry = Entry(today, mood, moodItem, promptInfo, entryText)
        try{
            saveEntry(newEntry)
        }catch (e: IOException){
            println("Error: ${e.message}")
        }
    }

//this function gets a random prompt from the promptPicker and displays it. the user writes their message. the date,
    //prompt and entry are then saved.
    fun writeToPromptEntry(){
        val mood = ""
        val moodItem = ""
        val promptInfo = PromptPicker().getAPrompt()
        println(promptInfo)
        val entryText = readlnOrNull()


        val newEntry = Entry(today, mood, moodItem, promptInfo, entryText)
        try{
            saveEntry(newEntry)
        }catch (e: IOException){
            println("Error: ${e.message}")
        }
    }

    //this goes to read from the file and displays the entries
    fun displayEntries(){
        val entriesList = loadEntryList()
        for(entry in entriesList)
        {
            println("-".repeat(30))
            //add date here:
            println("Date: ${entry.entryDate}")
            println("Prompt: ${entry.promptInfo}")
            println("Entry: ${entry.entryText}")

        }
        println("-".repeat(30))
    }

    //this function saves the entry to the file using the writeFile function. first we get all the exisitng entries
    //by loading them into a list. then we add our new entry, make the list in pretty json(indented and
    //new line, and then write to the file.
    private fun saveEntry(newEntry : Entry?) {

        val existingEntries = loadEntryList()
        if (newEntry != null) {
            existingEntries.add(newEntry)
        }

        //add some indents and carriage returns to JSON:   https://stackoverflow.com/questions/76726117/pretty-print-a-kotlin-json-serializable
        val prettifyJson = Json{prettyPrint = true}

        val updatedEntriesList = prettifyJson.encodeToString(existingEntries)
        writeJsonFile(updatedEntriesList)
    }

    //this loads the list of entries that we can use for displaying
   private fun loadEntryList(): MutableList<Entry>{
        return if(entryFile.exists()){
            try {
                val jsonEntries = entryFile.readText()
                Json.decodeFromString(jsonEntries)
            }catch(e:Exception){
                mutableListOf()
            }
        }
        else{
            mutableListOf()
        }
    }

    //this writes to the file where the entries are stored.
    private fun writeJsonFile( entries: String){
        //https://zetcode.com/kotlin/writefile/
        try {
            entryFile.writeText(entries)
        }catch(e: IOException)
        {
            println("Error writing Json info: ${e.message}")
        }
    }
//gets the user's most frequent mood every month for the last 6 months.
    fun getMoodInsight(){
        val entryList = loadEntryList()
        val moodTracker = MoodTracker()
        moodTracker.getTheInfo(entryList)
    }



}