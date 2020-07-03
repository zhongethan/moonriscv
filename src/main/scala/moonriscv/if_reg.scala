package moonriscv
import chisel3._

class if_reg extends Module{
  val io = IO(new Bundle() {
    val br_taken = Input(UInt(1.W))
    val br_addr  = Input(UInt(1.W))
    val pend    = Input(UInt(1.W))
  })

  val if_en  = RegInit(0.U(1.W))
  val if_reg = RegInit(0.U(30.W))

  when(io.pend===false.B){
    when(io.br_taken===true.B){
      if_reg := io.br_addr
    } .otherwise {
      if_reg := if_reg + 1.U
    }
  }

}

