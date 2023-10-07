# Overview
This software, written in Kotlin, is used to help a user to 
write in their journal. There are two options for entries. 
One where the program picks a random prompt and the user then
writes to this prompt. 
Another is where the user enters their mood and what they were
thinking about. The user then writes about that information. 

The program has the ability to save the entries to a json file, 
display the entries, and to get some insight. This last one
shows the user a table of the most frequent mood they had each
month for the last 6 months and the thing that was most on their
mind when they were feeling that.

My purpose for writing this program was two-fold. I wanted to learn
Kotlin because I want to eventually make this into an Android app.
I also struggle keeping a journal. With the random prompts and the
mood tracker this app can help me in my personal life to be more
enthused about journaling and gain some insights into my moods.

[Software Demo video](https://youtu.be/qmdP5Ol7G4k)
# Development Environment
- Kotlin
- IntelliJ
- JDK
- java.time
- kotlinx.serialization
- kotlin.random

# Useful websites
- [kotlinlang](https://kotlinlang.org/docs/home.html)
- [baeldung.com](https://www.baeldung.com/kotlin/)
- [zetcode.com](https://zetcode.com/kotlin/writefile/)
- [stackoverflow.com](https://stackoverflow.com/questions/77002137/serializer-has-not-been-found-for-type-localdate)
- [digitalocean.com](https://www.digitalocean.com/community/tutorials/kotlin-print-println-readline-scanner-repl)
- [geeksforgeeks.org](https://geeksforgeeks.org/kotlin-when-expression/)
# Future Work
- Tie this into a database to more easily query the data, especially when getting the Insights for the moods
- Add a way for the user to delete a entry
- Add an exit or cancel while writing an entry