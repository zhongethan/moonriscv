package tinyriscv
import chisel3._

class if_id2id extends Bundle {
  val inst_o = Output(UInt(params.InstBus.W))
  val inst_addr_o = Output(UInt(params.InstAddrBus.W))
}

class top2if_id extends Bundle{
  val inst_i = Input(UInt(params.InstBus.W))
}

class pc_reg2if_id extends Bundle{
  val inst_addr_i = Input(UInt(params.InstAddrBus.W))
}

class ctrl2if_id extends Bundle{
  val hold_flag_i = Input(UInt(params.Hold_Flag_Bus.W))
}