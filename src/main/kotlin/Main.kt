import kotlin.random.Random

class Start {
    val wordList = WordList().wordList

    fun start() {
        var wordNew = getNewWord()
        var thisWord = GuessWord(wordNew)
        thisWord.wordinfo()
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



    fun wordinfo() {
        print("$word $wordLenght $wordHidden")
    }

}
fun main() {
    val start = Start()
    start.start()
}

