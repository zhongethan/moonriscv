package moonriscv
import chisel3._

class if_stage extends Module{
  val io = IO(new Bundle() {
    val ihready = Input(UInt(1.W))

  })
  val pend = !io.ihready

  val if_reg = new InstFetch
  if_reg.io.pend := pend
}
