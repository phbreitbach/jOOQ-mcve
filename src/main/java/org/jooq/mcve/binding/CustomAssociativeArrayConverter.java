package org.jooq.mcve.binding;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.jooq.Converter;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import oracle.jdbc.OracleConnection;

public class CustomAssociativeArrayConverter implements Converter<Object, AssociativeArray> {

  private Connection con = null;

  @Override
  public AssociativeArray from(final Object o) {
    try {
      Array array = (Array) o;
      // Why is this method called for an IN parameter?
      Struct[] structs = array == null ? null : (Struct[]) array.getArray();
      return new AssociativeArray(structs);
    } catch (SQLException e) {
      throw new IllegalStateException("Unkonwn SQL Exception", e);
    }
  }

  @Override
  public Object to(final AssociativeArray array) {
    try {
      Struct[] structs = array.getStructs();
      OracleConnection oraCon = (OracleConnection) con.unwrap(OracleConnection.class);
      Array oraArray = oraCon.createOracleArray("DEVSB.TYPES.T_ASSOCIATIVEARRAY_MCVETEST", structs);
      return oraArray;
    } catch (SQLException e) {
      throw new IllegalStateException("Unkonwn SQL Exception", e);
    }

  }

  @Override
  public Class<Object> fromType() {
    return Object.class;
  }

  @Override
  public Class<AssociativeArray> toType() {
    return AssociativeArray.class;
  }

  public void resetConnection(Connection con) {
    this.con = con;
  }
}
