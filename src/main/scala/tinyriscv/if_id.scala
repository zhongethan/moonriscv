package tinyriscv
import chisel3._

class if_id extends Module{
  val io = new Bundle() {
    val top_i = new top2if_id
    val ctrl_i = new ctrl2if_id
    val pc_reg_i = new pc_reg2if_id
    val id_o = new if_id2id
  }
  val inst = RegInit(params.INST_NOP)
  val inst_addr = RegInit(params.ZeroWord)

  when(io.ctrl_i.hold_flag_i>=params.Hold_If){
    inst := params.INST_NOP
  }.otherwise{
    inst := io.top_i.inst_i
  }
  inst_addr := io.pc_reg_i.inst_addr_i
  io.id_o.inst_o := inst
  io.id_o.inst_addr_o := inst_addr

}
