package handshake

object HandShakeGen extends App{
  chisel3.Driver.execute(args,()=>new HandShake(32))
}
