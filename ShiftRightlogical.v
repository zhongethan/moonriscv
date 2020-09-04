module ShiftRightlogical(
  input         clock,
  input         reset,
  input  [31:0] io_a,
  input  [31:0] io_b,
  output [31:0] io_c
);
  wire [31:0] _T; // @[Test.scala 11:23]
  wire [4:0] _T_1; // @[Test.scala 11:33]
  wire [31:0] _T_2; // @[Test.scala 11:26]
  assign _T = $signed(io_a); // @[Test.scala 11:23]
  assign _T_1 = io_b[4:0]; // @[Test.scala 11:33]
  assign _T_2 = $signed(_T) >>> _T_1; // @[Test.scala 11:26]
  assign io_c = $unsigned(_T_2); // @[Test.scala 11:8]
endmodule
