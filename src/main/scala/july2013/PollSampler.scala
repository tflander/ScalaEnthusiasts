package july2013

//object PollField extends Enumeration {
//  type PollField = Value
//  val Party, Sex, Position = Value
//
//  def fieldValForSample(field: Value, sample: PollSample): String = {
//    field match {
//      case Party => sample.party
//      case Sex => sample.sex
//      case Position => sample.position
//    }
//  }
//}

  case class RawPollSampleWrapper(raw: Seq[PollSample]) {
    import PollField._
    def makeTree(field1: PollField.Value, field2: PollField.Value, field3: PollField.Value) = {
      require(field1 != field2)
      require(field1 != field3)
      require(field2 != field3)
      raw.groupBy(fieldValForSample(field1, _))
        .mapValues(_.groupBy(fieldValForSample(field2, _))
          .mapValues(_.groupBy(fieldValForSample(field3, _))))
    }
  }

  object PollSampler {
	  implicit def RawPollDataToRawPollSampleWrapper(raw: Seq[PollSample]) = RawPollSampleWrapper(raw)
  }