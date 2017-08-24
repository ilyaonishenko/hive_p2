package com.example

import eu.bitwalker.useragentutils.UserAgent
import org.apache.hadoop.hive.ql.exec.UDF
import org.apache.hadoop.io.Text


class UserAgentParser extends UDF {
  def evaluate(line: Text): Text = {
    val userAgent = UserAgent.parseUserAgentString(line.toString)
    userAgentInfoToText(
      UserAgentInfo(userAgent.getBrowser.getName,
        userAgent.getOperatingSystem.getName,
        userAgent.getOperatingSystem.getDeviceType.getName)
    )
  }

  def userAgentInfoToText(userAgentInfo: UserAgentInfo): Text = new Text(userAgentInfo.toString)
}
