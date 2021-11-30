package riscv

import chisel3._
import chiseltest._
import org.scalatest.{FlatSpec, Matchers}

class FetchSpec extends FlatSpec with ChiselScalatestTester with Matchers {
  behavior of "mycpu"

  it should "work through hex" in {
    test(new Top) {c =>
      while(!c.io.exit.peek().litToBoolean) {
        c.clock.step()
      }
    }
  }

}