package dm.fe.test
import com.redis._

class PrimeGenerator(maxNumber: Int = 100, redisHost: String = "127.0.0.1", redisPort : Int = 6379) {

  val redisServer = if (redisHost != null ) new RedisClient(redisHost, redisPort) else null
  var key = "Pkey"
  if (redisServer != null) {
    val primesToMax = generatePrimes(maxNumber);
    primesToMax foreach ((i: Int) => redisServer.sadd(key, i))
  }
  def generatePrimes(upTo:Int) : List[Int] = {
     def isPrime(i:Int):Boolean = {
       if( i <= 1 ) return false;
       (2 to (i - 1)) foreach( d => if ( i % d == 0) return false )
       return true
     }
    var v = Vector.empty[Int]
    (0 to upTo) foreach( i => if(isPrime(i)) {v = v :+ i} )
    v.toList
  }
  def getCachedPrimes(start: Int, end :Int) = {
    scala.util.control.Exception
    if (end > maxNumber) throw  new IllegalArgumentException("Error:" +end +" > " +maxNumber)
    if ( start > end ) throw new IllegalArgumentException ("Error:" +start +" > "+ end)
    if ( redisServer == null) throw new NullPointerException("Error: redis server is not initialized")
    var lst = Vector.empty[Int];
    (start to end) foreach((i : Int) => if ( redisServer.sismember(key, i) ) {lst = lst :+ i} )
    lst.toList
  }
}
