package com.example

import org.apache.hadoop.io.Text
import org.scalatest.FunSpec

class UserAgentParserTest extends FunSpec {

  val line1 = "8a15b98c8f9e60d4f92aaab01acf52a4\t20130606000104192\t1\tVhTVORqG36N6qMj\tMozilla/5.0 (Windows NT 5.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17\t114.100.37.*\t106\t117\t1\tlsxSl559Xql7FmMs\t8c9742e63497713b97ac7e780a8f9a12\tnull\tmm_30232185_2681382_11190685\t950\t90\t0\t1\t0\t23d6dade7ed21cea308205b37594003e\t227\t207\tb2e35064f3549d447edbbdfb1f707c8c\t3427\t10063,10684,10083,13403,10059,10024,10048,10057,13678,10079,10006,10076,10075,13866,10052,10093,14273,10110"
  val userAgentParser = new UserAgentParser

  describe("this class"){
    it("should return text"){
      val ua = userAgentParser.evaluate(new Text(line1))
      val res = UserAgentInfo("Chrome 24", "Windows XP", "Computer")
      assert(ua.toString === res.toString)
    }
  }
}
