CREATE TABLE mcvetest (
   id    NUMBER NOT NULL,
   value VARCHAR2(256),
   fehlertext VARCHAR2(240 BYTE),
   somedate DATE,
   CONSTRAINT pk_test PRIMARY KEY (id)
);

create or replace package types is

    TYPE t_associativearray IS TABLE OF mcvetest.fehlertext%TYPE INDEX BY VARCHAR2(9);

    TYPE t_associativearray_row IS TABLE OF mcvetest%ROWTYPE INDEX BY VARCHAR2(9);

end types; -- PACKAGE types
/

create or replace package proc_use_associativearrays is

    PROCEDURE array_as_in_param(
        p_aufrufErgebnis                OUT  NUMBER
        , p_liste           IN  types.t_associativearray
    );

    PROCEDURE array_as_in_param_row(
        p_aufrufErgebnis                OUT  NUMBER
        , p_liste           IN  types.t_associativearray_row
    );

    PROCEDURE array_as_out_param(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         OUT  types.t_associativearray
    );

    PROCEDURE array_as_out_param_row(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         OUT  types.t_associativearray_row
    );

    PROCEDURE array_as_inout_param(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         IN OUT  types.t_associativearray
    );

    PROCEDURE array_as_inout_nocopy_param(
        p_fehlertext                    IN   VARCHAR2
      , p_aufrufErgebnis                OUT  NUMBER
      , p_liste         IN OUT NOCOPY  types.t_associativearray
    );

    PROCEDURE array_as_inout_param_row(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         IN OUT  types.t_associativearray_row
    );
END proc_use_associativearrays; -- PACKAGE
/

create or replace package body proc_use_associativearrays is
    PROCEDURE array_as_in_param(
        p_aufrufErgebnis        OUT  NUMBER
       , p_liste  IN  types.t_associativearray
    ) is
        v_fehlertext mcvetest.fehlertext%TYPE;
        v_idx VARCHAR2(9);
        v_idx_first VARCHAR2(9);

    BEGIN
        p_aufrufErgebnis := 0;
        v_idx := p_liste.FIRST;
        v_idx_first := v_idx;
        while v_idx is not null
            loop
                v_fehlertext := p_liste(v_idx);
                DBMS_OUTPUT.put_line('Fehlertext: <' || v_fehlertext || '>.');
                v_idx := p_liste.NEXT(v_idx);
            end loop;
        if p_liste(v_idx_first) = 'Should come forth in PL/SQL'
            then p_aufrufErgebnis := 1;
            else p_aufrufErgebnis := -1;
        end if;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -2;
    END;

    PROCEDURE array_as_in_param_row(
        p_aufrufErgebnis   OUT  NUMBER
       , p_liste           IN  types.t_associativearray_row
    ) is
        v_mcvetest mcvetest%ROWTYPE;
        v_idx VARCHAR2(9);
    BEGIN
        p_aufrufErgebnis := 0;
        v_idx := p_liste.FIRST;
        while v_idx is not null
            loop
                v_mcvetest := p_liste(v_idx);
                DBMS_OUTPUT.put_line('ID: <' || v_mcvetest.id || '>, Value: <' || v_mcvetest.value || '>, ' ||
                                     'Fehlertext:' || v_mcvetest.fehlertext || '>.');
                v_idx := p_liste.NEXT(v_idx);
            end loop;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
    END;

    PROCEDURE array_as_out_param(
        p_fehlertext             IN   VARCHAR2
        , p_aufrufErgebnis       OUT  NUMBER
        , p_liste  OUT  types.t_associativearray
    ) is

    BEGIN
        p_aufrufErgebnis := 0;
        p_liste(1) := p_fehlertext;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
    END;

    PROCEDURE array_as_out_param_row(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         OUT  types.t_associativearray_row
    ) is

    BEGIN
        p_aufrufErgebnis := 0;
        p_liste('1').id := 1;
        p_liste('1').value := 'ValueOutRow';
        p_liste('1').fehlertext := p_fehlertext;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
            p_liste('2').id := 2;
            p_liste('2').value := SQLCODE;
            p_liste('2').fehlertext := SUBSTR(SQLERRM, 1, 240);
    END;

    PROCEDURE array_as_inout_param(
        p_fehlertext                    IN   VARCHAR2
      , p_aufrufErgebnis                OUT  NUMBER
      , p_liste         IN OUT  types.t_associativearray
    ) is

    BEGIN
        p_aufrufErgebnis := 0;
        p_liste('2') := p_fehlertext;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
    END;

    PROCEDURE array_as_inout_nocopy_param(
        p_fehlertext                    IN   VARCHAR2
      , p_aufrufErgebnis                OUT  NUMBER
      , p_liste         IN OUT NOCOPY types.t_associativearray
    ) is

    BEGIN
        p_aufrufErgebnis := 0;
        p_liste('2') := p_fehlertext;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
    END;

    PROCEDURE array_as_inout_param_row(
        p_fehlertext                    IN   VARCHAR2
        , p_aufrufErgebnis                OUT  NUMBER
        , p_liste         IN OUT  types.t_associativearray_row
    ) is

    BEGIN
        p_aufrufErgebnis := 0;
        p_liste('2').id := 1;
        p_liste('2').value := 'ValueInOutRow2';
        p_liste('2').fehlertext := p_fehlertext;
    EXCEPTION
        WHEN OTHERS THEN
            p_aufrufErgebnis := -1;
    END;

END proc_use_associativearrays; -- PACKAGE BODY procdures_using_associativearrays
/
