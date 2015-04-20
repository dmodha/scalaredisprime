package dm.fe.test

import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert._
/**
 * Created by digant on 4/19/15.
 */
class PrimeGeneratorTest {

  @Test
  def generatePrimeTest (): Unit = {
    val pg = new PrimeGenerator(100,null,0)
    val primes = pg.generatePrimes(100)
    val primesTo100 = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    assertEquals(primesTo100,primes)
  }
  @Test
  def getCachedPrimes() :Unit= {
    val pg = new PrimeGenerator(100,null,0)
    try {
      pg.getCachedPrimes(101, 10)
      fail("bad validation: start > end")
    } catch {
      case ex:Exception => {}
    }
    try {
      pg.getCachedPrimes(10, 101)
      fail("bad validation: end > max cached prime")
    } catch {
      case ex:Exception => {}
    }
  }
}
