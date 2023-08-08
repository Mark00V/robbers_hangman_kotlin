import kotlin.random.Random
import kotlin.math.floor

class Start {
    private val wordList = WordList().wordList

    fun start() {
        println("Starting game...")
        while (true) {
            val wordNew = getNewWord()
            val thisWord = GuessWord(wordNew)
            var guessLeft = thisWord.returnGuessLeft()
            var wordProg = thisWord.returnWordHidden()
            var containsUnderscore = wordProg.contains('_')
            while (guessLeft > 0) {
                println("Word: $wordProg Guesses left: $guessLeft Guess character:")
                val inputUser = readLine()
                var inputChar: Char? = null
                if (inputUser == "reveal") {
                    println(thisWord.wordinfo())
                } else {
                    if (!inputUser.isNullOrEmpty()) {
                        inputChar = inputUser[0]
                        thisWord.revealLetter(inputChar)
                    }
                    guessLeft = thisWord.returnGuessLeft()
                    wordProg = thisWord.returnWordHidden()
                    containsUnderscore = wordProg.contains('_')
                    if (!containsUnderscore) {
                        break
                    }
                }
            }
            if (guessLeft == 0) {
                println("You lost! The word was '$wordNew'")
            } else {
                println("You won!")
            }
            println("New game (y/n)?")
            val newgame = readLine()
            if (newgame != "y") {
                break
            }

        }
    }
    private fun getNewWord(): String {
        val randomIndex = Random.nextInt(wordList.size)
        return wordList[randomIndex]
    }

}

class GuessWord(private val word: String) {
    private val wordLenght = word.length
    private var wordHidden = '_'.toString().repeat(wordLenght)
    private val uniqueChars = mutableSetOf<Char>()

    init {
        for (char in word) {
            uniqueChars.add(char)
        }
    }
    private val nbrLetters = uniqueChars.size
    private var guessLeft =  floor(nbrLetters.toDouble() / 3).toInt() + 2

    fun returnGuessLeft(): Int {
        return guessLeft
    }

    fun wordinfo(): String {
        return "Help called -> Word: $word Guesses left: $guessLeft"
    }

    fun revealLetter(letter: Char) {
        var charIndex = word.indexOf(letter)
        val wordhiddenCa = wordHidden.toCharArray()
        if (charIndex == -1) {
            guessLeft -= 1
        }
        while (charIndex != -1) {
            if (charIndex >= 0) {
                wordhiddenCa[charIndex] = letter
            }
            charIndex = word.indexOf(letter, charIndex + 1)
        }
        wordHidden = String(wordhiddenCa)
    }

    fun returnWordHidden(): String {
        return wordHidden
    }
}
fun main() {
    val start = Start()
    start.start()
}

