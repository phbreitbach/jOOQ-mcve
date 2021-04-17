/*
 * This file is generated by jOOQ.
 */
package org.jooq.mcve.java;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;
import org.jooq.mcve.java.tables.Mcvetest;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Devsb extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEVSB</code>
     */
    public static final Devsb DEVSB = new Devsb();

    /**
     * The table <code>DEVSB.MCVETEST</code>.
     */
    public final Mcvetest MCVETEST = Mcvetest.MCVETEST;

    /**
     * No further instances allowed
     */
    private Devsb() {
        super("DEVSB", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Mcvetest.MCVETEST);
    }
}
