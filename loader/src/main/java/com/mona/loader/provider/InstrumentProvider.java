package com.mona.loader.provider;

import java.time.Instant;
import java.util.List;

public interface InstrumentProvider {

  List<String> getInstruments(Instant date);

  String toInstrument(String instrument);
}
