package tinyriscv

import chisel3._

class params {

  def CpuResetAdd = 0.U(32.W)

  def RstEnable = 0.U(1.W)
  def RstDisable = 1.U(1.W)
  def ZeroWord = 0.U(32.W)
  def ZeroReg = 0.U(5.W)
  def WriteEnable =1.U(1.W)
  def WriteDisable = 0.U(1.W)
  def ReadEnable = 1.U(1.W)
  def ReadDisable =0.U(1.W)
  def ChipEnable = 1.U(1.W)
  def ChipDisable = 0.U(1.W)
  def JumpEnable = 1.U(1.W)
  def JumpDisable = 0.U(1.W)
  def DivResultNotReady = 0.U(1.W)
  def DivResultReady = 1.U(1.W)
  def DivStart = 1.U(1.W)
  def DivStop = 0.U(1.W)
  def HoldEnable = 1.U(1.W)
  def HoldDisable = 0.U(1.W)
  def Stop = 1.U(1.W)
  def NoStop = 0.U(1.W)
  def RIB_ACK = 1.U(1.W)
  def RIB_NACK = 0.U(1.W)
  def RIB_REQ = 1.U(1.W)
  def RIB_NREQ = 0.U(1.W)
  def INT_ASSERT = 1.U(1.W)
  def INT_DEASSERT = 0.U(1.W)

  val INT_BUS = 8
  def INT_NONE = "h0".asUInt(8.W)
  def INT_RET = "hff".asUInt(8.W)
  def INT_TIMER0 = "b00000001".asUInt(8.W)
  def INT_TIMER0_ENTRY_ADDR ="h4".asUInt(32.W)

  val Hold_Flag_Bus = 3
  def Hold_None = "b000".asUInt(3.W)
  def Hold_Pc   = "b001".asUInt(3.W)
  def Hold_If   = "b010".asUInt(3.W)
  def Hold_Id   = "b011".asUInt(3.W)

  // Ipe inst
  def INST_TYPE_I = "b0010011".asUInt(7.W)
  def INST_ADDI   = "b000".asUInt(3.W)
  def INST_SLTI   = "b010".asUInt(3.W)
  def INST_SLTIU  = "b011".asUInt(3.W)
  def INST_XORI   = "b100".asUInt(3.W)
  def INST_ORI    = "b110".asUInt(3.W)
  def INST_ANDI   = "b111".asUInt(3.W)
  def INST_SLLI   = "b001".asUInt(3.W)
  def INST_SRI    = "b101".asUInt(3.W)

  // Lpe inst
  def INST_TYPE_L = "b0000011".asUInt(7.W)
  def INST_LB     = "b000".asUInt(3.W)
  def INST_LH     = "b001".asUInt(3.W)
  def INST_LW     = "b010".asUInt(3.W)
  def INST_LBU    = "b100".asUInt(3.W)
  def INST_LHU    = "b101".asUInt(3.W)

  // Spe inst
  def INST_TYPE_S = "b0100011".asUInt(7.W)
  def INST_SB     = "b000".asUInt(3.W)
  def INST_SH     = "b001".asUInt(3.W)
  def INST_SW     = "b010".asUInt(3.W)

  // Rd M type inst
  def INST_TYPE_R_M = "b0110011".asUInt(7.W)
  // Rpe inst
  def INST_ADD_SUB = "b000".asUInt(3.W)
  def INST_SLL     = "b001".asUInt(3.W)
  def INST_SLT     = "b010".asUInt(3.W)
  def INST_SLTU    = "b011".asUInt(3.W)
  def INST_XOR     = "b100".asUInt(3.W)
  def INST_SR      = "b101".asUInt(3.W)
  def INST_OR      = "b110".asUInt(3.W)
  def INST_AND     = "b111".asUInt(3.W)
  // Mpe inst
  def INST_MUL     = "b000".asUInt(3.W)
  def INST_MULH    = "b001".asUInt(3.W)
  def INST_MULHSU  = "b010".asUInt(3.W)
  def INST_MULHU   = "b011".asUInt(3.W)
  def INST_DIV     = "b100".asUInt(3.W)
  def INST_DIVU    = "b101".asUInt(3.W)
  def INST_REM     = "b110".asUInt(3.W)
  def INST_REMU    = "b111".asUInt(3.W)

  // Jpe inst
  def INST_JAL   = "b1101111".asUInt(7.W)
  def INST_JALR  = "b1100111".asUInt(7.W)

  def INST_LUI   = "b0110111".asUInt(7.W)
  def INST_AUIPC = "b0010111".asUInt(7.W)
  def INST_NOP    = "h00000001".asUInt(32.W)
  def INST_NOP_OP = "b0000001".asUInt(7.W)
  def INST_MRET   = "h30200073".asUInt(32.W)
  def INST_RET    = "h00008067".asUInt(32.W)

  def INST_FENCE  = "b0001111".asUInt(7.W)
  def INST_ECALL  = "h73".asUInt(32.W)
  def INST_EBREAK = "h00100073".asUInt(32.W)

  // Jpe inst
  def INST_TYPE_B = "b1100011".asUInt(7.W)
  def INST_BEQ    = "b000".asUInt(3.W)
  def INST_BNE    = "b001".asUInt(3.W)
  def INST_BLT    = "b100".asUInt(3.W)
  def INST_BGE    = "b101".asUInt(3.W)
  def INST_BLTU   = "b110".asUInt(3.W)
  def INST_BGEU   = "b111".asUInt(3.W)

  // Cinst
  def INST_CSR    = "b1110011".asUInt(7.W)
  def INST_CSRRW  = "b001".asUInt(3.W)
  def INST_CSRRS  = "b010".asUInt(3.W)
  def INST_CSRRC  = "b011".asUInt(3.W)
  def INST_CSRRWI = "b101".asUInt(3.W)
  def INST_CSRRSI = "b110".asUInt(3.W)
  def INST_CSRRCI = "b111".asUInt(3.W)

  // Creg addr
  def CSR_CYCLE    = "hc00".asUInt(12.W)
  def CSR_CYCLEH   = "hc80".asUInt(12.W)
  def CSR_MTVEC    = "h305".asUInt(12.W)
  def CSR_MCAUSE   = "h342".asUInt(12.W)
  def CSR_MEPC     = "h341".asUInt(12.W)
  def CSR_MIE      = "h304".asUInt(12.W)
  def CSR_MSTATUS  = "h300".asUInt(12.W)
  def CSR_MSCRATCH = "h340".asUInt(12.W)

  val RomNum = 4096  // rom depth(how many words)

  val MemNum = 4096  // memory depth(how many words)
  val MemBus = 32
  val MemAddrBus = 32

  val InstBus = 32
  val InstAddrBus = 32

  // con regs
  val RegAddrBus = 5
  val RegBus = 32
  val DoubleRegBus = 64
  val RegWidth = 32
  val RegNum = 32        // reg num
  val RegNumLog2 = 5


}
