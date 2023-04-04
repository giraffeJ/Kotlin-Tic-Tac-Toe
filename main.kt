package tictactoe
fun printState(context: String) {
    println("---------")
    println("| ${context[0]} ${context[1]} ${context[2]} |")
    println("| ${context[3]} ${context[4]} ${context[5]} |")
    println("| ${context[6]} ${context[7]} ${context[8]} |")
    println("---------")
}

fun updateState(context: String, turn: Char): String {
    var pos: Int = -1
    while(true) {
        val input: String = readln()
        val input_x: Int = input.split(" ")[0].toInt()
        val input_y: Int = input.split(" ")[1].toInt()
        var check_num: Boolean = true
        for (i in input) {
            if (i == ' ') continue
            if (i < '0' || i > '9') check_num = false
        }
        when{
            !check_num -> println("You should enter numbers!")
            input_x < 1 || input_x > 3 || input_y < 1 || input_y > 3 -> println("Coordinates should be from 1 to 3!")
            context[input_x * 3 + input_y - 4] != ' ' -> println("This cell is occupied! Choose another one!")
            else -> pos = input_x * 3 + input_y - 4
        }
        if(pos >= 0) break
    }
    return context.substring(0, pos) + turn + context.substring(pos + 1)
}

fun checkWin (context: String): String {
    for (i in 0..2) {
        if (context[i * 3] != ' ' && context[i * 3] == context[i * 3 + 1] && context[i * 3] == context[i * 3 + 2])
            return context[i * 3].toString()
        if (context[i] != ' ' && context[i] == context[i + 3] && context[i] == context[i + 6])
            return context[i].toString()
    }
    if (context[0] != ' ' && context[0] == context[4] && context[0] == context[8])
        return context[0].toString()
    if (context[2] != ' ' && context[2] == context[4] && context[2] == context[6])
        return context[2].toString()
        
    var count_o: Int = 0
    var count_x: Int = 0
    for (i in context) {
        if (i == 'O')
            count_o++
        if (i == 'X')
            count_x++
    }
    if (count_o + count_x == 9)
        return "Draw"
    return "none"
}

fun main() {
    var context: String = "         "    
    var turn = 'X'
    while(true){
        printState(context)
        context = updateState(context, turn)
        turn = if (turn == 'X') 'O' else 'X'
        val winner: String = checkWin(context)
        if (winner == "none") continue
        printState(context)
        print(winner)
        if (winner != "Draw")
            println(" wins") 
        break
    }
}
