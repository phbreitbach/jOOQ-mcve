/*
 * This file is generated by jOOQ.
 */
package org.jooq.mcve.java.packages;


import java.math.BigDecimal;

import org.jooq.Configuration;
import org.jooq.impl.PackageImpl;
import org.jooq.mcve.java.Devsb;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInParamRow;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutNocopyParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutParamRow;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsOutParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsOutParamRow;
import org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRecord;
import org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRowRecord;


/**
 * Convenience access to all stored procedures and functions in PROC_USE_ASSOCIATIVEARRAYS
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProcUseAssociativearrays extends PackageImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS</code>
     */
    public static final ProcUseAssociativearrays PROC_USE_ASSOCIATIVEARRAYS = new ProcUseAssociativearrays();

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_NOCOPY_PARAM</code>
     */
    public static ArrayAsInoutNocopyParam arrayAsInoutNocopyParam(
          Configuration configuration
        , String pFehlertext
        , TAssociativearrayRecord pListe
    ) {
        ArrayAsInoutNocopyParam p = new ArrayAsInoutNocopyParam();
        p.setPFehlertext(pFehlertext);
        p.setPListe(pListe);

        p.execute(configuration);
        return p;
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_PARAM</code>
     */
    public static ArrayAsInoutParam arrayAsInoutParam(
          Configuration configuration
        , String pFehlertext
        , TAssociativearrayRecord pListe
    ) {
        ArrayAsInoutParam p = new ArrayAsInoutParam();
        p.setPFehlertext(pFehlertext);
        p.setPListe(pListe);

        p.execute(configuration);
        return p;
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_PARAM_ROW</code>
     */
    public static ArrayAsInoutParamRow arrayAsInoutParamRow(
          Configuration configuration
        , String pFehlertext
        , TAssociativearrayRowRecord pListe
    ) {
        ArrayAsInoutParamRow p = new ArrayAsInoutParamRow();
        p.setPFehlertext(pFehlertext);
        p.setPListe(pListe);

        p.execute(configuration);
        return p;
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_IN_PARAM</code>
     */
    public static BigDecimal arrayAsInParam(
          Configuration configuration
        , TAssociativearrayRecord pListe
    ) {
        ArrayAsInParam p = new ArrayAsInParam();
        p.setPListe(pListe);

        p.execute(configuration);
        return p.getPAufrufergebnis();
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_IN_PARAM_ROW</code>
     */
    public static BigDecimal arrayAsInParamRow(
          Configuration configuration
        , TAssociativearrayRowRecord pListe
    ) {
        ArrayAsInParamRow p = new ArrayAsInParamRow();
        p.setPListe(pListe);

        p.execute(configuration);
        return p.getPAufrufergebnis();
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_OUT_PARAM</code>
     */
    public static ArrayAsOutParam arrayAsOutParam(
          Configuration configuration
        , String pFehlertext
    ) {
        ArrayAsOutParam p = new ArrayAsOutParam();
        p.setPFehlertext(pFehlertext);

        p.execute(configuration);
        return p;
    }

    /**
     * Call <code>DEVSB.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_OUT_PARAM_ROW</code>
     */
    public static ArrayAsOutParamRow arrayAsOutParamRow(
          Configuration configuration
        , String pFehlertext
    ) {
        ArrayAsOutParamRow p = new ArrayAsOutParamRow();
        p.setPFehlertext(pFehlertext);

        p.execute(configuration);
        return p;
    }

    /**
     * No further instances allowed
     */
    private ProcUseAssociativearrays() {
        super("PROC_USE_ASSOCIATIVEARRAYS", Devsb.DEVSB);
    }
}
