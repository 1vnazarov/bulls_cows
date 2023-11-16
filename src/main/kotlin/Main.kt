fun main() {
    val gen = genNumber()
    println(gen)
    var user: List<Int>?
    var res: Pair<Int, Int>
    do {
        user = getInput()
        res = check(gen, user!!)
        println("Ваш результат: Быки ${res.first} Коровы ${res.second}")
    }
    while (res.first < 4)
}

fun genNumber(): List<Int> {
    val numbers = (0..9).toMutableList()
    val num = mutableListOf<Int>()
    repeat(4) {
        val index = (0 until numbers.size).random()
        num += numbers[index]
        numbers.removeAt(index)
    }
    return num
}

fun getInput(): List<Int>? {
    var input = ""
    do {
        println("Введите последовательность из четырех цифр (уникальных)")
        input = readln()
    }
    while (input.length < 4)
    input.forEach {
        if (it.toString().toIntOrNull() == null) getInput()
    }
    val checked = mutableListOf<String>()
    repeat(4) {
        if (!checked.contains(input[it].toString())) {
            checked += input[it].toString()
        }
        else {
            getInput()
        }
    }
    val res = mutableListOf<Int>()
    repeat(4) {
        res += input[it].toString().toInt()
    }
    return res
}

fun check(gen: List<Int>, user: List<Int>): Pair<Int, Int> {
    var cows = 0
    var bulls = 0
    val guessed = mutableSetOf<Int>()
    for (i in gen.indices) {
        if (gen[i] == user[i]) bulls++
        else guessed.add(gen[i])
    }
    for (i in guessed.indices) {
        if (gen[i] != user[i] && user[i] in guessed) {
            cows++
            guessed.remove(gen[i])
        }
    }
    return Pair(bulls, cows)
}