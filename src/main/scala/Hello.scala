import chisel3._
import chisel3.stage.ChiselStage

class Hello extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(1.W))
  })

  val COUNT_MAX = (100 / 2 - 1).U

  val count_reg = RegInit(0.U(32.W))
  val blink_reg = RegInit(0.U(1.W))

  count_reg := count_reg + 1.U

  when(count_reg === COUNT_MAX) {
    count_reg := 0.U
    blink_reg := ~blink_reg
  }
  io.led := blink_reg
}

object Hello extends App {
  (new ChiselStage).emitVerilog(new Hello(), Array("--target-dir", "generated/"))
}