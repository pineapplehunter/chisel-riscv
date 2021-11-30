import org.scalatest.{FlatSpec, Matchers}
import chiseltest._
import chisel3._

class HelloSpec extends FlatSpec with ChiselScalatestTester with Matchers {
  behavior of "Hello"

  it should "toggle" in {
    test(new Hello) { c =>
      c.io.led.expect(0.U)
      c.clock.step(49)
      c.io.led.expect(0.U)
      c.clock.step()
      c.io.led.expect(1.U)
      c.clock.step(49)
      c.io.led.expect(1.U)
      c.clock.step()
      c.io.led.expect(0.U)
    }
  }

  it should "blink" in {
    test(new Hello) { c =>
      var led_state: BigInt = 0
      var changed = 0
      for (i <- 0 until 300) {
        c.clock.step()
        if (c.io.led.peek().litValue !== led_state) {
          led_state = c.io.led.peek().litValue
          changed += 1
          println(if (led_state === 1) "o" else "x")
        }
      }
      changed should not be 0
    }
  }
}