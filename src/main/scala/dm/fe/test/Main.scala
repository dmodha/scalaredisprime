package dm.fe.test

/**
 * Created by digant on 4/15/15.
 */
object Main {
  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println("usage: Main <maxPrime>")
      return ;
    };
    var enterAnother: Boolean = true
    val maxNumber = args(0).toInt
    val primes = new PrimeGenerator(maxNumber)
    do {
      print("Enter a lower bound: ")
      val lowerBound = scala.io.StdIn.readInt();
      print("Enter a upper bound: ")
      val upperBound = scala.io.StdIn.readInt();
      println("Result:");
      try {
        val primeNumbersList = primes.getCachedPrimes(lowerBound, upperBound)
        val primeNumbers = primeNumbersList.mkString( ",")
        println("Primes numbers: [" + primeNumbers +"]")
        println("Sum: " + primes.sumList(primeNumbersList))
        println("Mean: " + primes.meanList(primeNumbersList))
        println("Enter Y to get another range? ")
        val char = scala.io.StdIn.readChar()

        if (char != 'Y' && char != 'y')
          enterAnother = false
      } catch {
        case ex: StringIndexOutOfBoundsException => enterAnother = false
        case ex: IllegalArgumentException => println(ex.getMessage)
      }
    } while (enterAnother)

    return ;
  }
}
