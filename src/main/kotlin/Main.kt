// Игра с интерфейсом: https://github.com/1vnazarov/BullsAndCows
fun main() {
    val gen = genNumber()
    println(gen)
    var user: List<Int>
    var res: Pair<Int, Int>
    do {
        user = getInput()
        res = check(gen, user)
        println("Ваш результат:\nБыки: ${res.first}\nКоровы: ${res.second}")
        if (res.first < 4) println("Попробуйте еще раз")
        else println("Вы победили")
    }
    while (res.first < 4)
}

fun genNumber(): List<Int> {
    val numbers = (0..9).toMutableList()
    val num = mutableListOf<Int>()
    repeat(4) {
        val index = (0..<numbers.size).random()
        num += numbers[index]
        numbers.removeAt(index)
    }
    return num
}

fun getInput(): List<Int> {
    var input: String
    do {
        println("Введите последовательность из четырех уникальных цифр")
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
    for (i in gen.indices) {
        if (user[i] in gen) {
            if (gen[i] == user[i]) bulls++
            else cows++
        }
    }
    return Pair(bulls, cows)
}