package riscv

import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
import common.Consts.WORD_LEN

class ImemPortIo extends Bundle {
  val address = Input(UInt(WORD_LEN.W))
  val instruction = Output(UInt(WORD_LEN.W))
}

class Memory extends Module {
  val io = IO(new Bundle {
    val imem = new ImemPortIo()
  })

  val mem = Mem(16384, UInt(8.W))
  loadMemoryFromFile(mem, "src/hex/fetch.hex")

  io.imem.instruction := Cat(
    mem(io.imem.address + 3.U(WORD_LEN.W)),
    mem(io.imem.address + 2.U(WORD_LEN.W)),
    mem(io.imem.address + 1.U(WORD_LEN.W)),
    mem(io.imem.address)
  )
}

