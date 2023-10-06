
import kotlin.random.Random

class PromptPicker {
//this class  picks a random prompt or creates a prompt for the user based on their mood when writing.

    private val promptList = listOf<String>("What were you most grateful for today and why?",
        "Write about an experience you had with your grandma or grandpa.",
        "Describe in detail one of your favorite kinds of foods.",
        "Where do you see yourself in 5 years?",
        "Is there anything you did today that you will do different next time?",
        "Write about a family trip you've taken.",
        "Name a person that you are following on social media and tell why you follow them.",
        "What is the name and cost of your favorite snack food today?",
        "What goals are you currently working on?",
        "Describe a weird dream you've had and then try to give the interpretation.",
        "What was the best part of my day today?",
        "Write about an experience you had with one of your parents.",
        "Write about a favorite trip that you took. Would you ever go back?",
        "What is the top headline today?",
        "Is this where you imagined yourself 5 years ago? Is it better or worse and why?",
        "If you could invent anything, what would it be?",
        "Write about an experience you had with one of your siblings.",
        "Name the song and artist that is at the top of your playlist right now.",
        "What new movie is getting released this week?",
        "If this was your last decade on Earth, what would you do differently? Make a goal about that.",
        "What do you look for in the perfect friend. Why do you choose those attributes?",
        "Which of your family members do you most admire and why?",
        "What is your greatest dream?"
        )

    //this gets a random prompt from the promptList
    fun getAPrompt():String{
        val index = Random.nextInt(promptList.size)
        return (promptList[index])

    }
    //this creates a prompt based on the mood of the user and what is on their mind at the time.
    fun createAPrompt(mood: String?, moodItem: String?): String{
        val moodPrompt =
            if (moodItem == "body") {
            "Why are you feeling ${mood} about your body?"
        } else {
            "Tell me about your ${moodItem} and why you're feeling ${mood}."
        }
            return moodPrompt
    }





}