package org.jooq.mcve.binding;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Struct;
import java.sql.Types;

import org.jooq.Binding;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;
import org.jooq.Keyword;
import org.jooq.conf.ParamType;
import org.jooq.conf.RenderKeywordStyle;
import org.jooq.impl.AbstractBinding;
import org.jooq.impl.DSL;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.KeywordValue;

public class CustomAssociativeArrayBinding implements Binding<Object
  , AssociativeArray> {

  private final CustomAssociativeArrayConverter converter = new CustomAssociativeArrayConverter();

  @Override
  public Converter<Object, AssociativeArray> converter() {
    return converter;
  }

  @Override
  public void sql(BindingSQLContext<AssociativeArray> ctx) throws SQLException {
    if (ctx.render().paramType() == ParamType.INLINED) {
      if (ctx.value() == null) {
        ctx.render().visit(DSL.keyword("null"));
      } else {
        this.sqlInline(ctx);
      }
    } else {
      this.sqlBind(ctx);
    }

  }

  protected void sqlInline(BindingSQLContext<AssociativeArray> ctx) throws SQLException {
    ctx.render().visit(DSL.inline("" + ctx.value()));
  }

  protected void sqlBind(BindingSQLContext<AssociativeArray> ctx) throws SQLException {
    ctx.render().sql(ctx.variable());
  }

  @Override
  public void register(final BindingRegisterContext<AssociativeArray> bindingRegisterContext)
    throws SQLException {
    // FIXME ARRAY or OTHER?
    bindingRegisterContext.statement().registerOutParameter(bindingRegisterContext.index(), Types.ARRAY);
  }

  @Override
  public void set(final BindingSetStatementContext<AssociativeArray> bindingSetStatementContext)
    throws SQLException {
    converter.resetConnection(bindingSetStatementContext.statement().getConnection());
    Array array = (Array) bindingSetStatementContext.convert(converter()).value();
    bindingSetStatementContext.statement().setArray(bindingSetStatementContext.index(), array);
  }

  @Override
  public void set(final BindingSetSQLOutputContext<AssociativeArray> bindingSetSQLOutputContext)
    throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public void get(final BindingGetResultSetContext<AssociativeArray> bindingGetResultSetContext)
    throws SQLException {
    converter.resetConnection(bindingGetResultSetContext.resultSet().getStatement().getConnection());
    bindingGetResultSetContext.convert(converter()).value(bindingGetResultSetContext.resultSet().getArray(bindingGetResultSetContext.index()));
  }

  @Override
  public void get(final BindingGetStatementContext<AssociativeArray> bindingGetStatementContext)
    throws SQLException {
    converter.resetConnection(bindingGetStatementContext.statement().getConnection());
    bindingGetStatementContext.convert(converter()).value(bindingGetStatementContext.statement().getArray(bindingGetStatementContext.index()));
  }

  @Override
  public void get(final BindingGetSQLInputContext<AssociativeArray> bindingGetSQLInputContext)
    throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }
}
