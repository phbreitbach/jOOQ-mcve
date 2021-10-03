/*
 * This file is generated by jOOQ.
 */
package org.jooq.mcve.java.packages.proc_use_associativearrays;


import java.math.BigDecimal;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.mcve.java.Mcve;
import org.jooq.mcve.java.packages.ProcUseAssociativearrays;
import org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArrayAsInoutParam extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter
     * <code>MCVE.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_PARAM.P_FEHLERTEXT</code>.
     */
    public static final Parameter<String> P_FEHLERTEXT = Internal.createParameter("P_FEHLERTEXT", SQLDataType.VARCHAR, false, false);

    /**
     * The parameter
     * <code>MCVE.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_PARAM.P_AUFRUFERGEBNIS</code>.
     */
    public static final Parameter<BigDecimal> P_AUFRUFERGEBNIS = Internal.createParameter("P_AUFRUFERGEBNIS", SQLDataType.NUMERIC, false, false);

    /**
     * The parameter
     * <code>MCVE.PROC_USE_ASSOCIATIVEARRAYS.ARRAY_AS_INOUT_PARAM.P_LISTE</code>.
     */
    public static final Parameter<TAssociativearrayRecord> P_LISTE = Internal.createParameter("P_LISTE", SQLDataType.VARCHAR(240).asAssociativeArrayDataType(org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRecord.class), false, false);

    /**
     * Create a new routine call instance
     */
    public ArrayAsInoutParam() {
        super("ARRAY_AS_INOUT_PARAM", Mcve.MCVE, ProcUseAssociativearrays.PROC_USE_ASSOCIATIVEARRAYS);

        addInParameter(P_FEHLERTEXT);
        addOutParameter(P_AUFRUFERGEBNIS);
        addInOutParameter(P_LISTE);
    }

    /**
     * Set the <code>P_FEHLERTEXT</code> parameter IN value to the routine
     */
    public void setPFehlertext(String value) {
        setValue(P_FEHLERTEXT, value);
    }

    /**
     * Set the <code>P_LISTE</code> parameter IN value to the routine
     */
    public void setPListe(TAssociativearrayRecord value) {
        setValue(P_LISTE, value);
    }

    /**
     * Get the <code>P_AUFRUFERGEBNIS</code> parameter OUT value from the
     * routine
     */
    public BigDecimal getPAufrufergebnis() {
        return get(P_AUFRUFERGEBNIS);
    }

    /**
     * Get the <code>P_LISTE</code> parameter OUT value from the routine
     */
    public TAssociativearrayRecord getPListe() {
        return get(P_LISTE);
    }
}
