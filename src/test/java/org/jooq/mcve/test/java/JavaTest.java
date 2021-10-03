package org.jooq.mcve.test.java;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Struct;

import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInParamRow;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutNocopyParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsInoutParamRow;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsOutParam;
import org.jooq.mcve.java.packages.proc_use_associativearrays.ArrayAsOutParamRow;
import org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRecord;
import org.jooq.mcve.java.packages.types.udt.records.TAssociativearrayRowRecord;
import org.jooq.mcve.java.tables.records.McvetestRecord;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaTest extends AbstractTest {

    @Test
    public void arrayAsInParamTest() throws SQLException {

        ArrayAsInParam arrayAsInParamRoutine = new ArrayAsInParam();
        TAssociativearrayRecord trecs /* ;-D */ = new TAssociativearrayRecord();
        trecs.put("a", "Should come forth in PL/SQL");
        arrayAsInParamRoutine.setPListe(trecs);
        arrayAsInParamRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsInParamRoutine.getPAufrufergebnis();
        assertEquals(1, (int) rc.intValue());
    }

    @Test
    public void arrayAsInRowParamTest() {
        ArrayAsInParamRow arrayAsInParamRowRoutine = new ArrayAsInParamRow();
        arrayAsInParamRowRoutine.setPListe(new TAssociativearrayRowRecord());
        arrayAsInParamRowRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsInParamRowRoutine.getPAufrufergebnis();
        assertEquals(0, (int) rc.intValue());
    }

    @Test
    public void arrayAsOutParamTest() throws SQLException {
        ArrayAsOutParam arrayAsOutParamRoutine = new ArrayAsOutParam();
        arrayAsOutParamRoutine.setPFehlertext("Kein Fehler wenn das im OUT Parameter zurueckkommt");
        arrayAsOutParamRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsOutParamRoutine.getPAufrufergebnis();
        assertEquals(0, (int) rc.intValue());
        TAssociativearrayRecord associativeArray = arrayAsOutParamRoutine.getPListe();
        assertEquals("Kein Fehler wenn das im OUT Parameter zurueckkommt", associativeArray.get("1"));
    }

    @Test
    public void arrayAsOutParamRowTest() throws SQLException {
        ArrayAsOutParamRow arrayAsOutParamRowRoutine = new ArrayAsOutParamRow();
        arrayAsOutParamRowRoutine.setPFehlertext("Kein Fehler wenn das im OUT Parameter zurueckkommt");
        arrayAsOutParamRowRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsOutParamRowRoutine.getPAufrufergebnis();
        TAssociativearrayRowRecord associativeArray = arrayAsOutParamRowRoutine.getPListe();
        assertEquals(0, (int) rc.intValue());
        assertEquals(1, associativeArray.size());
        McvetestRecord mcvetestRecord = associativeArray.get("1");
        assertEquals(BigDecimal.ONE, mcvetestRecord.getId());
        assertEquals("ValueOutRow", mcvetestRecord.getValue());
        assertEquals("Kein Fehler wenn das im OUT Parameter zurueckkommt", mcvetestRecord.getFehlertext());
    }

    @Test
    public void arrayAsInOutParamTest() throws SQLException {
        ArrayAsInoutParam arrayAsInoutParamRoutine = new ArrayAsInoutParam();
        arrayAsInoutParamRoutine.setPFehlertext("Should be appended to p_liste.");
        TAssociativearrayRecord trecs /* ;-D */ = new TAssociativearrayRecord();
        trecs.put("a", "Should come forth in PL/SQL");
        arrayAsInoutParamRoutine.setPListe(trecs);
        arrayAsInoutParamRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsInoutParamRoutine.getPAufrufergebnis();
        assertEquals(0, (int) rc.intValue());
        TAssociativearrayRecord associativeArray = arrayAsInoutParamRoutine.getPListe();
        assertEquals(2, (int) associativeArray.size());
        assertEquals("Should come forth in PL/SQL", associativeArray.get(0));
        assertEquals("Should be appended to p_liste.", associativeArray.get(1));
    }

    @Test
    public void arrayAsInOutNocopyParamTest() throws SQLException {
        ArrayAsInoutNocopyParam arrayAsInoutNocopyParamRoutine = new ArrayAsInoutNocopyParam();
        arrayAsInoutNocopyParamRoutine.setPFehlertext("Should be appended to p_liste.");
        TAssociativearrayRecord trecs /* ;-D */ = new TAssociativearrayRecord();
        trecs.put("a", "Should come forth in PL/SQL");
        arrayAsInoutNocopyParamRoutine.setPListe(trecs);
        arrayAsInoutNocopyParamRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsInoutNocopyParamRoutine.getPAufrufergebnis();
        assertEquals(0, (int) rc.intValue());
        TAssociativearrayRecord associativeArray = arrayAsInoutNocopyParamRoutine.getPListe();
        assertEquals(2, (int) associativeArray.size());
        assertEquals("Should come forth in PL/SQL", associativeArray.get(0));
        assertEquals("Should be appended to p_liste.", associativeArray.get(1));
    }

    @Test
    public void arrayAsInOutParamRowTest() throws SQLException {
        ArrayAsInoutParamRow arrayAsInoutParamRowRoutine = new ArrayAsInoutParamRow();
        arrayAsInoutParamRowRoutine.setPFehlertext("Should be appended to p_liste.");
        TAssociativearrayRowRecord trecs /* ;-D */ = new TAssociativearrayRowRecord();
        arrayAsInoutParamRowRoutine.setPListe(trecs);
        arrayAsInoutParamRowRoutine.execute(ctx.configuration());
        BigDecimal rc = arrayAsInoutParamRowRoutine.getPAufrufergebnis();
        TAssociativearrayRowRecord associativeArray = arrayAsInoutParamRowRoutine.getPListe();
        assertEquals(0, (int) rc.intValue());
        assertEquals(1, associativeArray.size());
        Struct result = (Struct)associativeArray.get(0);
        assertEquals(BigDecimal.ONE, result.getAttributes()[0]);
        assertEquals("ValueInOutRow2", result.getAttributes()[1]);
        assertEquals("Should be appended to p_liste.", result.getAttributes()[2]);
    }
}
