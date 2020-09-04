;buildInfoPackage: chisel3, version: 3.2-20191106-SNAPSHOT, scalaVersion: 2.12.10, sbtVersion: 1.3.2
circuit ShiftRightlogical : 
  module ShiftRightlogical : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip a : UInt<32>, flip b : UInt<32>, c : UInt<32>}
    
    node _T = asSInt(io.a) @[Test.scala 11:23]
    node _T_1 = bits(io.b, 4, 0) @[Test.scala 11:33]
    node _T_2 = dshr(_T, _T_1) @[Test.scala 11:26]
    node _T_3 = asUInt(_T_2) @[Test.scala 11:40]
    io.c <= _T_3 @[Test.scala 11:8]
    
