package com.example

case class UserAgentInfo(browser: String, operatingSystem: String, device: String){
  override def toString: String = {
    s"$browser\t$operatingSystem\t$device"
  }
}
