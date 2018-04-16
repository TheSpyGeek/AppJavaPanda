import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import myExceptions.*;
import dataframe.*;

public class DataFrameTest {

    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;
    DataFrame dfToTestCsv;

    @Before
    public void init() throws IOException, ExceptionNotSameSize {
        dfToTestCsv = new DataFrame("src/test/ressources/csv1.csv");
        col = new ArrayList<ArrayList<? extends Comparable>>();
        labels = new ArrayList<String>();
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);
        firstCol.add(0);
        firstCol.add(1);
        firstCol.add(2);
        firstCol.add(3);
        firstCol.add(8);

        ArrayList<Double>  secondCol = new ArrayList<Double>();
        secondCol.add(1.0);
        secondCol.add(2.5);
        secondCol.add(0.0);
        secondCol.add(0.4);
        secondCol.add(0.0);
        secondCol.add(0.1);

        ArrayList<String>  thirdCol = new ArrayList<String>();
        thirdCol.add("Victor");
        thirdCol.add("Maxime");
        thirdCol.add("Malotru");
        thirdCol.add("Vaccination");
        thirdCol.add("Abeille");
        thirdCol.add("Zébulon!");

        col.add(firstCol);
        col.add(secondCol);
        col.add(thirdCol);

        labels.add("Index");
        labels.add("Note");
        labels.add("Mot");

        try {
            dfToTest = new DataFrame(labels,col);
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            exceptionNotSameSize.printStackTrace();
        }
    }


    @Test
    public void testSize() throws ExceptionNoSuchColumn {

        assertEquals("Doit être égal à 6 pour la colonne Index",6, dfToTest.getCount("Index"));
        assertEquals("Doit être égal à 6 pour la colonne Note",6, dfToTest.getCount("Note"));
        assertEquals("Doit être égal à 6 pour la colonne Mot",6, dfToTest.getCount("Mot"));

    }

    @Test
    public void testSum() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Somme de la colonne Index",13, dfToTest.getSum("Index"));
        assertEquals("Somme de la colonne Note",4.0, dfToTest.getSum("Note"));
    }

    @Test(expected = ExceptionString.class)
    public void testSumString() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTest.getSum("Mot");
    }

    @Test
    public void testAvg() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Moyenne de la colonne Index",(13/6), dfToTest.getAvg("Index"));
        assertEquals("Moyenne de la colonne Note",(double)4/6, dfToTest.getAvg("Note"));
    }

    @Test(expected= ExceptionString.class)
    public void testAvgString() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTest.getAvg("Mot");
    }

    @Test
    public void testMin() throws ExceptionNoSuchColumn {
        assertEquals("Minimum de la colonne Index",-1, dfToTest.getMin("Index"));
        assertEquals("Minimum de la colonne Mot","Abeille", dfToTest.getMin("Mot"));
        assertEquals("Minimum de la colonne Note",0.0, dfToTest.getMin("Note"));
    }

    @Test
    public void testMax() throws ExceptionNoSuchColumn {
        assertEquals("Maximum de la colonne Index",8, dfToTest.getMax("Index"));
        assertEquals("Maximum de la colonne Mot","Zébulon!", dfToTest.getMax("Mot"));
        assertEquals("Maximum de la colonne Note",2.5, dfToTest.getMax("Note"));
    }

    @Test(expected = ExceptionNotSameSize.class )
    public void testConstructDataFrameNotSameNbColThanDatas() throws ExceptionNotSameSize {
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("A");
        labels.add("B");

        ArrayList<ArrayList<? extends Comparable>> datas = new ArrayList<ArrayList<? extends Comparable>>();
        datas.add(firstCol);

        new DataFrame(labels,datas);
    }










    //Tests sur colonnes n'existant pas

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testSizeNotExistColonne() throws ExceptionNoSuchColumn {
        dfToTest.getCount("NotExist");

    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testAvgNotExistColonne() throws ExceptionNoSuchColumn, ExceptionString {

        dfToTest.getAvg("DontExists");
    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testSumNotExistColonne() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTest.getSum("NotExist");
    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testMinNotExistColonne() throws ExceptionNoSuchColumn {
        dfToTest.getMin("NotExist");
    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testMaxNotExistColonne() throws ExceptionNoSuchColumn {
        dfToTest.getMax("NotExist");
    }



    @Test(expected = ExceptionNotSameSize.class)
    public void testConstructor() throws ExceptionNotSameSize {
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);
        firstCol.add(0);


        ArrayList<Double>  secondCol = new ArrayList<Double>();
        secondCol.add(1.0);
        secondCol.add(2.5);
        secondCol.add(0.0);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Entier");
        labels.add("Double");

        ArrayList<ArrayList<? extends Comparable>> col = new ArrayList<ArrayList<? extends Comparable>>();
        col.add(firstCol);
        col.add(secondCol);

        DataFrame dt = new DataFrame(labels, col);

    }

    @Test
    public void testSelectLabels() throws ExceptionNoSuchColumn, ExceptionNotSameSize {
        ArrayList cloneTestSelectLabelsData = new ArrayList();
        ArrayList<String> cloneTestSelectLabelsDataCol1 = new ArrayList<String>();
        cloneTestSelectLabelsDataCol1.add("Victor");
        cloneTestSelectLabelsDataCol1.add("Maxime");
        cloneTestSelectLabelsDataCol1.add("Malotru");
        cloneTestSelectLabelsDataCol1.add("Vaccination");
        cloneTestSelectLabelsDataCol1.add("Abeille");
        cloneTestSelectLabelsDataCol1.add("Zébulon!");
        cloneTestSelectLabelsData.add(cloneTestSelectLabelsDataCol1);


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Mot");

        DataFrame dfToCompare = new DataFrame(labels,cloneTestSelectLabelsData);
        DataFrame dfSelect = dfToTest.selectFromLabel(labels);

        assertTrue("Les dataframes doivent-être similaires (Selection Sur la colonne Mot)! ",
                dfSelect.equals(dfToCompare));

        labels.add("Index");

        ArrayList<Integer> cloneTestSelectLabelsDataCol2 = new ArrayList<Integer>();
        cloneTestSelectLabelsDataCol2.add(-1);
        cloneTestSelectLabelsDataCol2.add(0);
        cloneTestSelectLabelsDataCol2.add(1);
        cloneTestSelectLabelsDataCol2.add(2);
        cloneTestSelectLabelsDataCol2.add(3);
        cloneTestSelectLabelsDataCol2.add(8);
        cloneTestSelectLabelsData.add(cloneTestSelectLabelsDataCol2);

        dfSelect = dfToTest.selectFromLabel(labels);

        dfToCompare = new DataFrame(labels,cloneTestSelectLabelsData);

        assertTrue("Les dataframes doivent-être similaires (Selection Sur la colonne Mot et Index)! ",
                dfSelect.equals(dfToCompare));
    }



    @Test(expected = ExceptionNoSuchColumn.class)
    public void testSelectLabelsNotExist() throws ExceptionNoSuchColumn {
        ArrayList labels = new ArrayList();
        labels.add("NOT EXISTS");
        DataFrame dfSelect = dfToTest.selectFromLabel(labels);
    }

    /**
     * Compare deux dataframes inégaux.
     */
    @Test
    public void testEqualsDataFrame(){
        assertFalse("Les dataframes sont inégaux!",dfToTest.equals(dfToTestCsv));
    }

    @Test
    public void testSelectLinesCompare1() throws IOException, ExceptionNotSameSize, ExceptionOutOfRange {
        DataFrame dfCsvFail = new DataFrame("src/test/ressources/csv2.csv");

        ArrayList<Integer> lineToSelect = new ArrayList<Integer>();
        lineToSelect.add(1);
        DataFrame dfCsvFailSelect = dfCsvFail.selectFromLine(lineToSelect);

        DataFrame dfSelect = dfToTest.selectFromLine(lineToSelect);

        assertFalse("Ces lignes ne sont pas égales en type!",dfCsvFailSelect.equals(dfSelect));
    }


    @Test
    public void testSelectLines() throws IOException, ExceptionNotSameSize, ExceptionOutOfRange {
        ArrayList<Integer> lineToSelect = new ArrayList<Integer>();
        lineToSelect.add(0);
        lineToSelect.add(3);
        lineToSelect.add(5);



        col = new ArrayList<ArrayList<? extends Comparable>>();
        labels = new ArrayList<String>();
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);
        firstCol.add(2);
        firstCol.add(8);

        ArrayList<Double>  secondCol = new ArrayList<Double>();
        secondCol.add(1.0);
        secondCol.add(0.4);
        secondCol.add(0.1);

        ArrayList<String>  thirdCol = new ArrayList<String>();
        thirdCol.add("Victor");
        thirdCol.add("Vaccination");
        thirdCol.add("Zébulon!");


        DataFrame dfSelect = dfToTest.selectFromLine(lineToSelect);

        col.add(firstCol);
        col.add(secondCol);
        col.add(thirdCol);

        labels.add("Index");
        labels.add("Note");
        labels.add("Mot");
        DataFrame dfCompare = new DataFrame(labels,col);

        assertTrue("Ces lignes sont égales",dfSelect.equals(dfCompare));
    }


    @Test(expected = IOException.class)
    public void createDfFromInexistantFile() throws IOException, ExceptionNotSameSize {
        DataFrame dataFrame = new DataFrame("FAIL");
    }




    @Test(expected = ExceptionOutOfRange.class)
    public void testPrintOutOfRange1() throws ExceptionOutOfRange {
        dfToTest.print(4,0);
    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testPrintOutOfRange2() throws ExceptionOutOfRange {
        dfToTest.print(-1,12);
    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testPrintOutOfRange3() throws ExceptionOutOfRange {
        dfToTest.print(0,12);
    }


    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLinesOutOfRange1() throws ExceptionOutOfRange {
        ArrayList<Integer> linesToSelect = new ArrayList<Integer>();
        linesToSelect.add(-1);
        DataFrame df = dfToTest.selectFromLine(linesToSelect);
    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLinesOutOfRange2() throws ExceptionOutOfRange {
        ArrayList<Integer> linesToSelect = new ArrayList<Integer>();
        linesToSelect.add(30);
        DataFrame df = dfToTest.selectFromLine(linesToSelect);
    }

    @Test(expected = ExceptionOutOfRange.class)
    public void testSelectLinesOutOfRange3() throws ExceptionOutOfRange {
        ArrayList<Integer> linesToSelect = new ArrayList<Integer>();
        linesToSelect.add(1);
        linesToSelect.add(2);
        linesToSelect.add(3);
        linesToSelect.add(4);
        linesToSelect.add(5);
        linesToSelect.add(6);
        DataFrame df = dfToTest.selectFromLine(linesToSelect);
    }




    //////CSV PART//////

    @Test
    public void testSizeCsv() throws ExceptionNoSuchColumn {

        assertEquals("Doit être égal à 6 pour la colonne Index",6, dfToTestCsv.getCount("Index"));
        assertEquals("Doit être égal à 6 pour la colonne Nom",6, dfToTestCsv.getCount("Nom"));
        assertEquals("Doit être égal à 6 pour la colonne Prenom",6, dfToTestCsv.getCount("Prenom"));
        assertEquals("Doit être égal à 6 pour la colonne Age",6, dfToTestCsv.getCount("Age"));
        assertEquals("Doit être égal à 6 pour la colonne Note",6, dfToTestCsv.getCount("Note"));
        assertEquals("Doit être égal à 6 pour la colonne HASH",6, dfToTestCsv.getCount("HASH"));

    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testColonneExistCsv() throws ExceptionNoSuchColumn, ExceptionString {
        dfToTestCsv.getAvg("DontExists");
    }

    //INTEGER
    @Test
    public void testSumCsv() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Somme de la colonne Age",34.5, dfToTestCsv.getSum("Age"));
        assertEquals("Somme de la colonne Note",20.9, dfToTestCsv.getSum("Note"));
        assertEquals("Somme de la colonne Index",15, dfToTestCsv.getSum("Index"));
    }

    @Test(expected = ExceptionString.class)
    public void testSumStringCsv() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTestCsv.getSum("HASH");
    }

    @Test(expected= ExceptionString.class)
    public void testAvgStringCsv() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTestCsv.getAvg("Prenom");
    }

    @Test
    public void testAvgCsv() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Moyenne de la colonne Age",34.5/6, dfToTestCsv.getAvg("Age"));
        assertEquals("Moyenne de la colonne Note",20.9/6, dfToTestCsv.getAvg("Note"));
        assertEquals("Moyenne de la colonne Index",15/6, dfToTestCsv.getAvg("Index"));
    }

    @Test
    public void testMinCsv() throws ExceptionNoSuchColumn {
        assertEquals("Minimum de la colonne Index",0, dfToTestCsv.getMin("Index"));
        assertEquals("Minimum de la colonne Age",2.0, dfToTestCsv.getMin("Age"));
        assertEquals("Minimum de la colonne Note",1.0, dfToTestCsv.getMin("Note"));
    }

    @Test
    public void testMaxCsv() throws ExceptionNoSuchColumn {
        assertEquals("Maximum de la colonne Index",5, dfToTestCsv.getMax("Index"));
        assertEquals("Maximum de la colonne Age",9.0, dfToTestCsv.getMax("Age"));
        assertEquals("Maximum de la colonne Note",5.0, dfToTestCsv.getMax("Note"));
    }

    @Test(expected = ExceptionNoSuchColumn.class)
    public void testMinExceptionString() throws ExceptionNoSuchColumn {
        dfToTestCsv.getMin("Lol");
    }

    @Test(expected = ExceptionNoSuchColumn.class)
    public void testMaxExceptionString() throws ExceptionNoSuchColumn {
        dfToTestCsv.getMax("Ici");
    }

    @Test
    public void testConstructCSV() throws ExceptionNoSuchColumn, IOException, ExceptionNotSameSize {

        DataFrame dt = new DataFrame("src/test/ressources/csv1.csv");
        assertEquals(5.0, dt.getMax("Note"));
    }

    @Test(expected = IOException.class)
    public void testConstructCSVNotExist() throws IOException, ExceptionNotSameSize {
        DataFrame dt = new DataFrame("src/test/ressources/csvnotexist.csv");
    }

    @Test(expected = ExceptionNotSameSize.class)
    public void testCSVWithEmptyFields() throws IOException, ExceptionNotSameSize {
        DataFrame dt = new DataFrame("src/test/ressources/csv4.csv");
    }

    @Test
    public void testPrintAll() throws ExceptionOutOfRange {
        Assert.assertEquals("\t\t\tIndex\t\t\tNote\t\t\tMot\t\t\t\n" +
                "0\t\t\t-1\t\t\t1.0\t\t\tVictor\t\t\t\n" +
                "1\t\t\t0\t\t\t2.5\t\t\tMaxime\t\t\t\n" +
                "2\t\t\t1\t\t\t0.0\t\t\tMalotru\t\t\t\n" +
                "3\t\t\t2\t\t\t0.4\t\t\tVaccination\t\t\t\n"+
                "4\t\t\t3\t\t\t0.0\t\t\tAbeille\t\t\t\n" +
                "5\t\t\t8\t\t\t0.1\t\t\tZébulon!\t\t\t\n",
                dfToTest.printAll());
    }

    @Test
    public void testPrintFirstLine() throws ExceptionOutOfRange {
        ArrayList<Integer> lineToSelect = new ArrayList<Integer>();
        lineToSelect.add(0);
        DataFrame dfFirstLine = dfToTest.selectFromLine(lineToSelect);

        Assert.assertEquals("\t\t\tIndex\t\t\tNote\t\t\tMot\t\t\t\n" +
                "0\t\t\t-1\t\t\t1.0\t\t\tVictor\t\t\t\n",
                dfFirstLine.printAll());

    }
}
