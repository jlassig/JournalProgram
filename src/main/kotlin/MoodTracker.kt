import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoodTracker {

    //getting the user's mood. They can either use one of the suggested or use other and type in their mood.
    fun getMood(): String{

        var mood  = ""
        val instructions = "Enter your mood (max 15 characters): "

        do{
            var isNumber = true
            val moodChoice: String = displayMoods()
            when(moodChoice){
                "1" -> mood = "happy"
                "2" -> mood = "sad"
                "3" -> mood = "anxious"
                "4" -> mood = "angry"
                "5" -> mood = "motivated"
                "6" -> mood = "unmotivated"
                "7" -> mood = checkStringLength(instructions)
                else -> {
                    isNumber = false
                    println("Please enter a number")
                }
            }
        } while (!isNumber)
        return mood
    }

    //this gets what is on the user's mind when they wrote about their mood. I don't want the user to say that
    //the moodItem CAUSES their mood, but this item would be the thing on their mind when they say they are sad or happy, etc.
    fun getMoodItem():String{

        var moodItem = ""
        val instructions = "What is on your mind(max 15 characters): "

        do{
            var isNumber = true
            val moodItemChoice: String = displayMoodItems()
            when(moodItemChoice){
                "1" -> moodItem = "work"
                "2" -> moodItem = "school"
                "3" -> moodItem = "family"
                "4" -> moodItem = "friends"
                "5" -> moodItem = "church"
                "6" -> moodItem = "body"
                "7" -> moodItem = checkStringLength(instructions)
                else -> {
                    isNumber = false
                    println("Please enter a number")
                }
            }
        } while (!isNumber)
        return moodItem

    }

    //checker to make sure the user doesn't put in a crazy long string for their mood or moodItem, or nothing at all
    private fun checkStringLength(instructions: String):String{
        var response = ""

        do{
            println(instructions)
            response = readln()
            if(response.isEmpty()){
                println("Invalid response. Try again.")
            }
            else if (response.length > 15){
                println("Your answer is too long. Try again.")}
        }while(response.isEmpty() || response.length > 15)
        return response
    }

    private fun displayMoods(): String{

        println("1 - happy")
        println("2 - sad")
        println("3 - anxious")
        println("4 - angry")
        println("5 - motivated")
        println("6 - unmotivated")
        println("7 - other")
        val moodNumber: String? = readlnOrNull()
        //checking to see if the moodNumber is null
        if(moodNumber.isNullOrBlank()){
            println("Please try again. ")
            return displayMoods()
        }
        return moodNumber
    }

    private fun displayMoodItems(): String{

        println("1 - work")
        println("2 - school")
        println("3 - family")
        println("4 - friends")
        println("5 - church")
        println("6 - body")
        println("7 - other")
        val moodItemNum: String? = readlnOrNull()
        //checking to see if the moodItemNum is null
        if(moodItemNum.isNullOrBlank()){
            println("Please try again. ")
            return displayMoodItems()
        }
        return moodItemNum
    }

    //for lists and how to add to them:
    //https://kotlinlang.org/docs/collections-overview.html#collection-types
    //this function creates a  new list of the entries that are in the last x amount of months
    private fun getInfoByMonths(entryList: List<Entry>, numOfMonths: Int): List<Entry>{
        val newEntryList: MutableList<Entry> = mutableListOf()
        val targetMonth = getTargetMonth(numOfMonths)

        for(entry in entryList){
            val entryDate = entry.entryDate
            val entryMonth = entryDate.monthValue
            if (entryMonth == targetMonth && entry.mood != "")
            {
                newEntryList.add(entry)
            }
        }
        return newEntryList
    }


    //grouping: https://www.geeksforgeeks.org/kotlin-grouping/
    //this function takes the strings from the getMostFrequent function, where we see by month for the last 6 months what the user
    //was feeling and what they were thinking about, and then puts the strings into a nice table.
    fun getTheInfo(entryList: List<Entry>){

        println("|   Last 6 months   |   Most Frequent Mood   | associated with")
        println("|-------------------|------------------------|------------------")
     ////   https://www.programiz.com/kotlin-programming/for-loop
        for(i in 6 downTo 1) {
            val monthList = getInfoByMonths(entryList, i)
            getMostFrequent(monthList, i)
        }

        println()
        println()

    }

    //this function pulls back the last 6 months of data. Month's name, the user's most frequent mood and what they were most
    //often thinking about when they recorded the mood (moodItem). It returns a nice string per month.
    fun getMostFrequent(entryList: List<Entry>, numOfMonths: Int){
        //getting the most frequent mood for each month in the last 6 months
        val groupByMood = entryList.groupBy { it.mood }
        val mostFrequentMood = groupByMood.maxByOrNull { it.value.size }?.key?:"--No entries--"
        //getting the thing the person was thinking about the most when they had their most frequent mood:
        val groupByMoodItem = groupByMood[mostFrequentMood]?.groupBy{it.moodItem}
        val mostFrequentMoodItem = groupByMoodItem?.maxByOrNull { it.value.size }?.key ?:"--No entries--"

        val moodColumnSize = 20
        val moodLength = mostFrequentMood.length

        val modifiedFrequentMood = if(moodLength < moodColumnSize){
            val addSpace = moodColumnSize - moodLength
            mostFrequentMood + " ".repeat(addSpace)

        } else if  (moodLength > moodColumnSize){
            val removeLetters = moodColumnSize - moodLength
            mostFrequentMood.substring(0, moodLength+removeLetters)

        } else{
            mostFrequentMood
        }

        val targetMonth = getTargetMonth((numOfMonths))

        val monthName= when(targetMonth) {
            1->"January"
            2->"February"
            3->"March"
            4->"April"
            5->"May"
            6->"June"
            7->"July"
            8->"August"
            9->"September"
            10->"October"
            11->"November"
            12->"December"
            else-> "Not a month of year"
        }

        val monthColumnSize = 14
        val monthNameLength = monthName.length

        val modifiedMonth =
         if(monthNameLength < monthColumnSize){
            val addSpace = monthColumnSize - monthNameLength
            monthName + " ".repeat(addSpace)

        }else{
            monthName
        }
        println("| $modifiedMonth\t| $modifiedFrequentMood\t | $mostFrequentMoodItem")

    }

//this function gets the target month. so if the program is looking at 6 months away, 6 gets entered as an argument for monthInt
    fun getTargetMonth(monthInt : Int):Int{

        //for testing of other dates:
        /*
        val todayString = "2024-01-05"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.parse(todayString, formatter)
         */
        val today = LocalDate.now()
        var targetMonth = today.minusMonths(monthInt.toLong()).monthValue
        if (targetMonth < 0)
        {
            targetMonth += 12
        }
        return targetMonth

    }
}