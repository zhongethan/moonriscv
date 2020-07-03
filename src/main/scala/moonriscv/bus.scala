package moonriscv
import chisel3._
import chisel3.util._
import org.scalacheck.Prop.True

class bus extends Module{
  val io = IO(new Bundle() {
    val ahb_icode = new ahb
    val ahb_dcode = new ahb
    val iaddr = Input(UInt(30.W))
  })

  //define transfer types, burst operation and hsize
  val single :: incr :: wrap4 :: incr4 :: wrap8 :: incr8 :: wrap16 :: incr16 :: Nil = Enum(8)
  val idle :: busy :: nonseq :: seq :: Nil = Enum(4)
  val bit8 :: bit16 :: bit32 :: bit64 :: bit128 :: bit256 :: bit512 :: bit1024 :: Nil = Enum(8)
  val read :: write :: Nil = Enum(2)

  val addr_next = RegInit(0.U(30.W))
  when(io.ahb_icode.hready===true.B){
    addr_next := Cat(io.iaddr,0.U(2.W))
  }
  io.ahb_icode.haddr := addr_next
  io.ahb_icode.htrans := Mux(io.ahb_icode.hready===true.B,nonseq,idle)
  io.ahb_icode.hsize := bit32
  io.ahb_icode.hburst := single
  io.ahb_icode.hwrite := read
  io.ahb_icode.hwdata := 0.U

}
