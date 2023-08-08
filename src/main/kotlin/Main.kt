import kotlin.random.Random
import kotlin.math.floor

class Start {
    val wordList = WordList().wordList

    fun start() {
        println("Starting game...")
        while (true) {
            var wordNew = getNewWord()
            var thisWord = GuessWord(wordNew)
            var guessLeft = thisWord.returnGuessLeft()
            val guessInit = thisWord.returnGuessInit()
            var wordProg = thisWord.returnWordHidden()
            var containsUnderscore = wordProg.contains('_')
            while (guessLeft > 0) {
                println("Word: $wordProg Guesses left: $guessLeft Guess character:")
                var inputUser = readLine()
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
                println("You lost!")
            } else {
                println("You won!")
            }
            println("New game (y/n)?")
            var newgame = readLine()
            if (newgame != "y") {
                break
            }

        }
    }
    fun getNewWord(): String {
        var randomIndex = Random.nextInt(wordList.size)
        var getNewWord = wordList[randomIndex]
        return getNewWord
    }

}

class GuessWord(val word: String) {
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
    private val guessInit = guessLeft

    fun increaseGuessLeft() {
        guessLeft += 1
    }
    fun returnGuessInit(): Int {
        return guessInit
    }
    fun returnGuessLeft(): Int {
        return guessLeft
    }

    fun wordinfo(): String {
        var retString = "Help called -> Word: $word Guesses left: $guessLeft"
        return retString
    }

    fun revealLetter(letter: Char) {
        var charIndex = word.indexOf(letter)
        var wordHidden_ca = wordHidden.toCharArray()
        if (charIndex == -1) {
            guessLeft -= 1
        }
        while (charIndex != -1) {
            if (charIndex >= 0) {
                wordHidden_ca[charIndex] = letter
            }
            charIndex = word.indexOf(letter, charIndex + 1)
        }
        wordHidden = String(wordHidden_ca)
    }

    fun returnWordHidden(): String {
        return wordHidden
    }
}
fun main() {
    val start = Start()
    start.start()
}

