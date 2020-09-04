package moonriscv
import chisel3._
import chisel3.util._

class Alu extends Module{
  val io = new Bundle() {
    val opcode = Input(UInt(6.W))
    val func3 = Input(UInt(3.W))
    val func7 = Input(UInt(7.W))
    val valA = Input(UInt(32.W))
    val valB = Input(UInt(32.W))
    val valP = Input(UInt(32.W))
    val imm = Input(UInt(32.W))
    val valE = Output(UInt(32.W))
  }

  def add(a:UInt,b:UInt):UInt ={
    a + b
  }

  def sub(a:UInt,b:UInt):UInt = {
    a - b
  }

  def and(a:UInt,b:UInt):UInt = {
    a & b
  }

  def or(a:UInt,b:UInt):UInt = {
    a | b
  }

  def xor(a:UInt,b:UInt):UInt = {
    a ^ b
  }

  def shiftLeftLogical(a:UInt,b:UInt):UInt ={
    (a << b(4,0)).asUInt
  }

  def shiftRightLogical(a:UInt,b:UInt):UInt = {
    (a >> b(4,0)).asUInt
  }

  def shiftRightArithmetic(a:UInt,b:UInt):UInt = {
    (a.asSInt() >> b(4,0)).asUInt
  }

  def lessThanUnsigned (a:UInt,b:UInt):UInt = {
    (a < b)
  }

  def lessThanSigned (a:UInt,b:UInt) :UInt = {
    a.asSInt < b.asSInt
  }

  val aluA = io.valA
  val aluB = Wire(UInt(32.W))

  aluB := Mux(io.opcode === Rv32I.instTypeI, io.imm, io.valB)

  val ans = RegInit(0.U(32.W))
  switch(io.func3){
    is(Rv32I.add_sub){
      when(io.func7===0.U(7.W)){
        ans := add(aluA,aluB)
      }.otherwise{
        ans := sub(aluA,aluB)
      }
    }
    is(Rv32I.and){
      ans := and(aluA,aluB)
    }
    is(Rv32I.or){
      ans := or(aluA,aluB)
    }
    is(Rv32I.xor){
      ans := xor(aluA,aluB)
    }
    is(Rv32I.sll){
      ans := shiftLeftLogical(aluA,aluB)
    }
    is(Rv32I.srl_sra){
      when(io.func7===0.U(7.W)){
        ans := shiftRightLogical(aluA,aluB)
      }.otherwise{
        ans := shiftRightArithmetic(aluA,aluB)
      }
    }
    is(Rv32I.slt){
      ans := lessThanSigned(aluA,aluB)
    }
    is(Rv32I.sltu){
      ans := lessThanUnsigned(aluA,aluB)
    }
  }

  io.valE := ans


}
