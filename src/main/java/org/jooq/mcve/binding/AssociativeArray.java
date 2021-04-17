package org.jooq.mcve.binding;

import java.sql.Array;
import java.sql.Struct;

import org.jooq.Binding;
import org.jooq.Converter;
import org.jooq.DataType;
import org.jooq.Package;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.impl.ArrayRecordImpl;
import org.jooq.impl.DefaultBinding;
import org.jooq.impl.DefaultConverterProvider;
import org.jooq.impl.SQLDataType;

public class AssociativeArray {

  private final Struct[] structs;
  public AssociativeArray(Struct[] structs) {
    this.structs = structs;
  }

  public Struct[] getStructs() {
    return structs;
  }
}
