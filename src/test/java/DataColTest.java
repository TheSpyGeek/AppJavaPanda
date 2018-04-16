import org.junit.Test;
import myExceptions.*;

import java.util.ArrayList;
import dataframe.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataColTest {


    @Test
    public void testMaxData(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals("Max Data", 7, datacol.getMax());
    }

    @Test
    public void testMinDataInteger(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(3, datacol.getMin());
    }

    @Test
    public void testSumData() throws ExceptionString {
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(10, datacol.getSum());
    }

    @Test(expected= ExceptionString.class)
    public void testSumDataWithString() throws ExceptionString {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");

        DataCol datacol = new DataCol("Prenom", data);

        datacol.getSum();
    }

    @Test
    public void testGetLabel(){
        DataCol datacol = new DataCol("Label", null);
        assertEquals("Label", datacol.getLabel());
    }

    @Test
    public void testGetSize(){
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        assertEquals(4, datacol.getSize());
    }

    @Test
    public void testSelectLine() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(2);
        linesSelect.add(3);

        DataCol newDatacol = datacol.selectByLine(linesSelect);

        assertEquals(2, newDatacol.getSize());
        assertEquals("Prenom", newDatacol.getLabel());
    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLineNegativeIndex() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);
        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(-2);
        linesSelect.add(3);


        DataCol newDatacol = datacol.selectByLine(linesSelect);

    }

    public void testSelectLineInversedIndex() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");

        DataCol datacol = new DataCol("Prenom", data);

        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(2);
        linesSelect.add(1);
        DataCol newDatacol = datacol.selectByLine(linesSelect);

    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLineOutOfBoundIndex() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(2);
        linesSelect.add(5);

        DataCol newDatacol = datacol.selectByLine(linesSelect);

    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLineOutOfBoundIndex2() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(5);
        linesSelect.add(2);

        DataCol newDatacol = datacol.selectByLine(linesSelect);

    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLineOutOfBoundIndex3() throws ExceptionOutOfRange {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        ArrayList<Integer> linesSelect = new ArrayList<Integer>();
        linesSelect.add(-1);
        linesSelect.add(2);

        DataCol newDatacol = datacol.selectByLine(linesSelect);

    }

    @Test
    public void testAvgInteger() throws ExceptionString {
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(5);
        data.add(5);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(5, datacol.getAvg());
    }

    @Test
    public void testAvgDouble() throws ExceptionString {
        ArrayList<Double> data = new ArrayList<Double>();
        data.add(0.0);
        data.add(2.0);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(1.0, datacol.getAvg());
    }

    @Test(expected = ExceptionString.class)
    public void testAvgString() throws ExceptionString {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);
        datacol.getAvg();
    }

    @Test
    public void testMinCol(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(1500);
        data.add(2000);
        data.add(100);
        data.add(500);

        DataCol datacol = new DataCol("Int", data);
        assertEquals(100, datacol.getMin());
    }

    @Test
    public void testMaxCol(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(1500);
        data.add(2000);
        data.add(100);
        data.add(500);

        DataCol datacol = new DataCol("Int", data);
        assertEquals(2000, datacol.getMax());
    }

    @Test
    public void testEqualsNotSameType(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(1500);
        data.add(2000);
        data.add(100);
        data.add(500);

        DataCol datacol = new DataCol("Int", data);


        assertFalse(datacol.equals("Hello"));
    }

    @Test
    public void testEqualsNotSameSize(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(1500);
        data.add(2000);
        data.add(100);
        data.add(500);

        DataCol datacol = new DataCol("Int", data);

        ArrayList<Integer> data2 = new ArrayList<Integer>();
        data2.add(1500);
        data2.add(2000);

        DataCol datacol2 = new DataCol("Int", data2);


        assertFalse(datacol.equals(datacol2));
    }

}
