package moonriscv
import chisel3._

class ahb extends Bundle {
  //master signals
  val haddr = Output(UInt(32.W))
  val hburst = Output(UInt(3.W))  //single-incr-wrap4-inc4-wrap8-inc8-wrap16-inc16
  val htrans = Output(UInt(2.W)) //idle-busy-nonseq-seq
  val hsize = Output(UInt(3.W))   //8bit-16bit-32bit-64bit-128bit-256bit-512bit
  val hwdata = Output(UInt(32.W))
  val hwrite = Output(UInt(1.W))
  val hmasterlock = Output(UInt(1.W))
  val hport = Output(UInt(4.W))
  val hrdata = Input(UInt(32.W))
  val hready = Input(UInt(1.W))
  val hresp = Input(UInt(1.W))

}

