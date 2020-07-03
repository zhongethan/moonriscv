package handshake

import chisel3._
import chisel3.util._

// handshake sync module
class HandShake(width:Int) extends RawModule{
  val io = IO(new Bundle() {
    // master clock domain
    val mclk = Input(Clock())
    val m_nrst = Input(AsyncReset())

    // slave clock domain
    val sclk = Input(Clock())
    val s_nrst = Input(AsyncReset())

    // master side
    val mval = Input(UInt(1.W))
    val mack = Output(UInt(1.W))
    val mdata = Input(UInt(width.W))

    // slave side
    val sval = Output(UInt(1.W))
    val sack = Input(UInt(1.W))
    val sdata = Output(UInt(width.W))
  })
  val req_mclk = Wire(UInt())
  val ack_sclk = Wire(UInt())

  //fsm in hclk domain
  val m_reset :: m_wait_req :: m_wait_ack :: m_wait_nack :: Nil = Enum(4)
  withClockAndReset(io.mclk,io.s_nrst){
    val ack_mclk =  {RegNext(ack_sclk)}
    val ack_mclk_d = {RegNext(ack_mclk)}
    val mabort_nack = !ack_mclk_d
    val m_state = {RegInit(m_reset)}
    switch(m_state) {
      is(m_reset){
        m_state := m_wait_req
      }
      is(m_wait_req) {
        when(io.mval === 1.U){
          m_state := m_wait_ack
        }}
      is(m_wait_ack){
        when(!io.mval){
          m_state := m_wait_req
        }.elsewhen(ack_mclk_d===1.U){
          m_state := m_wait_nack
        }
      }
      is(m_wait_nack){
        when(mabort_nack){
          m_state := m_wait_req
        }
      }
    }
    req_mclk := (m_state === m_wait_req) && io.mval === 1.U ||
                    (m_state === m_wait_ack) && (!ack_mclk_d)

    io.mack := (m_state === m_wait_ack) && (ack_mclk_d===1.U)
  }
  //fsm in sclk domain

  val s_reset :: s_wait_req :: s_wait_ack :: s_wait_nreq :: Nil = Enum(4)
  withClockAndReset(io.sclk,io.s_nrst){
    val s_state = {RegInit(s_reset)}
    val req_sclk = {RegNext(req_mclk)}
    val req_sclk_d = {RegNext(req_sclk)}
    switch(s_state){
      is(s_reset){
        s_state := s_wait_req
      }
      is(s_wait_req){
        when(req_sclk_d===1.U){s_state := s_wait_ack}
      }
      is(s_wait_ack){
        when(io.sack === 1.U) {s_state := s_wait_nreq}
      }
      is(s_wait_nreq){
        when(!req_sclk_d) {s_state := s_wait_req}
      }
    }
    io.sval := (s_state === s_wait_ack) || ((s_state === s_wait_req) && (req_sclk_d=== 1.U))
    ack_sclk := (s_state === s_wait_nreq) && (req_sclk_d === 1.U)
  }

  io.sdata := io.mdata

}
