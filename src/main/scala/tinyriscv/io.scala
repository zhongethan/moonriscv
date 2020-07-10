package tinyriscv
import chisel3._


class io extends Bundle {
  val params = new params();
  val inst_i = Input(UInt(params.InstBus.W))
}
