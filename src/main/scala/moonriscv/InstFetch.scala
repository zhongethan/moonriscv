package moonriscv
import chisel3._
import chisel3.util._

class InstFetch extends Module{
  val io = IO(new Bundle() {
    val inst = Input(UInt(32.W))
    val srcA = Output(UInt(5.W))
    val srcB = Output(UInt(5.W))
  })

  val opcode = Wire(UInt(7.W))
  val rsA = RegInit(0.U(5.W))
  val rsB = RegInit(0.U(5.W))
  val rd = RegInit(0.U(5.W))
  val func3 = RegInit(0.U(3.W))
  val func7 = RegInit(0.U(7.W))
  val imm = RegInit(0.U(20.W))

  opcode := io.inst(6,0)
  when(!(opcode===Rv32I.lui || opcode===Rv32I.auipc || opcode === Rv32I.jal)){
    rsA := io.inst(19,15)
  }.otherwise{
    rsA := 0.U
  }

  when(opcode===Rv32I.instTypeR || opcode===Rv32I.instTypeS || opcode===Rv32I.instTypeB){
    rsB := io.inst(24,20)
    func3 := io.inst(14,12)
  }.otherwise{
    rsB := 0.U
    func3 := 0.U
  }

  when(opcode===Rv32I.instTypeR){
    func7 := io.inst(31,25)
  }.otherwise{
    func7 := 0.U
  }

  when((opcode===Rv32I.instTypeS || opcode===Rv32I.instTypeB)){
    rd := io.inst(11,7)
  }.otherwise{
    rd := 0.U
  }

  when(opcode===Rv32I.instTypeI || opcode===Rv32I.instTypeIF || opcode===Rv32I.instTypeIL || opcode === Rv32I.instTypeIM || opcode === Rv32I.jalr){ //I-type
    imm := io.inst(31,20)
  }.elsewhen(opcode===Rv32I.instTypeS){  // S-type
    imm := Cat(io.inst(31,25),io.inst(11,7))
  }.elsewhen(opcode===Rv32I.instTypeB){  // B-type
    imm := Cat(io.inst(31),io.inst(7),io.inst(30,25),io.inst(11,8))
  }.elsewhen(opcode===Rv32I.lui || opcode===Rv32I.auipc){  //U-type
    imm := io.inst(31,12)
  }.elsewhen(opcode===Rv32I.jal){
    imm := Cat(io.inst(31),io.inst(19,12),io.inst(20),io.inst(30,21))
  }

}

