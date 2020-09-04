package Test
import chisel3._
import chisel3.util._

class ShiftRightlogical extends Module{
  val io = IO(new Bundle() {
    val a = Input(UInt(32.W))
    val b = Input(UInt(32.W))
    val c = Output(UInt(32.W))
  })
  io.c := (io.a.asSInt() >> io.b(4,0)).asUInt

//  printf("result:%d\n",io.c)

}

object Test extends App{
  chisel3.Driver.execute(args,()=>new ShiftRightlogical)
}



