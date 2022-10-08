import java.util.*

fun printMatrix(matrix:Array<Array<Array<Array<Char>>>>) {
    for(i in matrix.indices){
        for (j in matrix[i].indices) {
            for (l in matrix[i][j].indices) {
                for (k in matrix[i][j][l].indices){
                    print(matrix[i][j][l][k])
                    print(" ")
                }
                print(" \t")
            }
            println()
        }
        println()
    }
}

fun checkWin(field_i:Int, field_j:Int,i:Int, j:Int, matrix: Array<Array<Array<Array<Char>>>>):Boolean {
    val symbol = matrix[field_i-1][i-1][field_j-1][j-1]
    var ver = true
    var hor = true
    var dia = i==j
    for (idx in matrix.indices){
        if (matrix[field_i-1][idx][field_j-1][j-1] != symbol) {ver = false}
        if (matrix[field_i-1][i-1][field_j-1][idx] != symbol) {hor = false}
        if (i == j) {
            if (matrix[field_i-1][idx][field_j-1][idx] != symbol) dia = false
        }
    }
    return ver || hor || dia
}

fun main() {
    val sc = Scanner(System.`in`)
    println("Enter size:")
    val size = sc.nextInt()
    val matrix = Array(size){Array(size){Array(size){Array(size){'☐'} }}}
    var y = 2
    var x = 2
    var xo = 0
    while (true) {
        println("Enter coordinates or 's' to show the field:")
        var input = sc.next()
        when (input) {
            "s" -> printMatrix(matrix)
            else -> {
                val i = input.toInt()
                input = sc.next()
                val j = input.toInt()
                if (xo % 2 == 0) {
                    matrix[y-1][i-1][x-1][j-1] = '☒'
                    if (checkWin(y, x, i, j, matrix)) {
                        printMatrix(matrix)
                        println("X has won")
                        break
                    }
                }
                else {
                    matrix[y-1][i-1][x-1][j-1] = '○'
                    if (checkWin(y, x, i, j, matrix)) {
                        printMatrix(matrix)
                        println("○ has won")
                        break
                    }
                }
                xo++
                y = i
                x = j
            }
        }
    }
}
