package moonriscv
import chisel3._
object Rv32I {

    //Integer computation

  val instTypeB = "b1100011".U
  val instTypeR = "b0110011".U
  val instTypeS = "b0100011".U

  val instTypeI = "b0010011".U
  val add_sub = "b000".U         // funct7=0, add; funct7=0100000, sub
  val and = "b111".U
  val or = "b110".U
  val xor = "b100".U
  val sll = "b001".U             //shift left logical
  val srl_sra = "b101".U         //shift right logical funct7=0/shift right arithmetic funct7=010_0000
  val slt = "b010".U             //set less than
  val sltu = "b011".U            //set less than unsigned

  val instTypeIM = "b1110011".U
  val ecall_ebreak = "b000".U    //environment break/ enviroment call
  val csrrw = "b001".U           //control status register read & write
  val csrrs = "b010".U           //control status register read & set
  val csrrc = "b011".U           //control status register read & clear
  val csrrwi = "b101".U          //control status register read & write immediate
  val csrrsi = "b110".U          //control status register read & set  immediate
  val csrrci = "b111".U          //control status register read & clear immediate

  val instTypeIL = "b0000011".U
  val lb = "b000".U              // load byte
  val lh = "b001".U              // load half_word
  val lw = "b010".U              // load word
  val lbu = "b100".U             // load byte unsigned
  val lhu = "b101".U             // load half_word unsigned

  val instTypeIF ="0001111".U
  val fence = "b000".U
  val fencei = "b001".U

  // U-type instruction
  val lui = "b0110111".U         //load upper immediate
  val auipc = "b0010111".U       //add upper immediate to pc
  // J-type instruction
  val jal = "b1101111".U         //jump and link
  // I-type instruction
  val jalr = "b1100111".U        //jump and link register
}
