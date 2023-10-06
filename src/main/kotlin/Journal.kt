import java.time.LocalDate
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.*
import kotlinx.serialization.json.*

import java.io.File
import java.io.IOException


class Journal {
    //this is going to have a date object, entry that the user fills in
    //mood that the user fills in, and a "title".

    val today = LocalDate.now()
    val fileName = "src/main/kotlin/entries.json"
    val entryFile = File(fileName)

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

        //get the info from the user then save to file.
        //the user needs to enter mood, entry,
        //date and title are autofilled in
        // title for this is "freestyle"

        val entryText = readLine()


        val newEntry = Entry(today, mood, moodItem, promptInfo, entryText)
        try{
            saveEntry(newEntry)
        }catch (e: IOException){
            println("Error: ${e.message}")
        }
    }

    fun writeToPromptEntry(){

        //get a prompt for the user to write to, then save to the file
        //title for this is the prompt.
        val mood = ""
        val moodItem = ""
        val promptInfo = PromptPicker().getAPrompt()
        println(promptInfo)
        val entryText = readLine()


        val newEntry = Entry(today, mood, moodItem, promptInfo, entryText)
        try{
            saveEntry(newEntry)
        }catch (e: IOException){
            println("Error: ${e.message}")
        }
    }

    fun displayEntries(){
        //this goes to read from the file and displays the entries
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

    private fun saveEntry(newEntry : Entry?) {
        //this function writes to a file
        val existingEntries = loadEntryList()


        if (newEntry != null) {
            existingEntries.add(newEntry)
        }

        //add some indents and carriage returns to JSON:   https://stackoverflow.com/questions/76726117/pretty-print-a-kotlin-json-serializable
        val prettifyJson = Json{prettyPrint = true}

        val updatedEntriesList = prettifyJson.encodeToString(existingEntries)
        writeJsonFile(updatedEntriesList)
    }

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

    private fun writeJsonFile( entries: String){
        try {
            entryFile.writeText(entries)
        }catch(e: IOException)
        {
            println("Error writing Json info: ${e.message}")
        }
    }

    fun getMoodInsight(){
        val entryList = loadEntryList()
        val moodTracker = MoodTracker()
        moodTracker.getTheInfo(entryList)
    }



}