import dataframe.DataFrame;
import dataframe.DataFrameGrouped;
import myExceptions.ExceptionColIsGrouped;
import myExceptions.ExceptionNoSuchColumn;
import myExceptions.ExceptionNotSameSize;
import myExceptions.ExceptionString;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataFrameGroupedTest {
    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;
    DataFrameGrouped dfGroupedToTestGroupedByNom;
    DataFrameGrouped dfGroupedToTestGroupedByNomPrenom;

    @Before
    public void init() throws ExceptionNoSuchColumn, ExceptionNotSameSize {
        col = new ArrayList<ArrayList<? extends Comparable>>();
        labels = new ArrayList<String>();

        ArrayList<String>  firstCol = new ArrayList<String>();
        firstCol.add("Baverel");
        firstCol.add("Isnel");
        firstCol.add("Gomes");
        firstCol.add("Isnel");
        firstCol.add("Baverel");
        firstCol.add("Valdes");




        ArrayList<String>  thirdCol = new ArrayList<String>();
        thirdCol.add("Victor");
        thirdCol.add("Maxime");
        thirdCol.add("Selena");
        thirdCol.add("Maxime");
        thirdCol.add("Victor");
        thirdCol.add("Victor");




        ArrayList<Integer>  secondCol = new ArrayList<Integer>();
        secondCol.add(3000);
        secondCol.add(4000);
        secondCol.add(13000);
        secondCol.add(10);
        secondCol.add(2000);
        secondCol.add(1000);




        ArrayList<Double>  fifthCol = new ArrayList<Double>();
        fifthCol.add(3000.0);
        fifthCol.add(4000.0);
        fifthCol.add(13000.0);
        fifthCol.add(10.0);
        fifthCol.add(2000.0);
        fifthCol.add(1000.0);

        ArrayList<String>  fourthCol = new ArrayList<String>();
        fourthCol.add("Bel-ami");
        fourthCol.add("L'assommoir");
        fourthCol.add("Le rouge et le noir");
        fourthCol.add("20 000 lieues sous les mers");
        fourthCol.add("Kafka sur le rivage");
        fourthCol.add("Le bourreau de Gaudi");

        col.add(thirdCol);
        col.add(firstCol);
        col.add(secondCol);
        col.add(fifthCol);
        col.add(fourthCol);


        labels.add("Prénom");
        labels.add("Nom");
        labels.add("RevenusInt");
        labels.add("RevenusDouble");
        labels.add("Livres préférés");


        dfToTest = new DataFrame(labels,col);
        ArrayList<String> colsToGroup = new ArrayList<String>();
        colsToGroup.add("Prénom");
        dfGroupedToTestGroupedByNom = dfToTest.groupBy(colsToGroup);

        colsToGroup.add("Nom");
        dfGroupedToTestGroupedByNomPrenom = dfToTest.groupBy(colsToGroup);
    }





    /*CES PREMIERS TESTS S'EFFECTUEUNT SUR UN GROUP BY UNIQUEMENT SUR LA COLONNE PRENOM*/

    @Test
    public void testCountLineOnGroup(){
        ArrayList<Integer> waitedResult = new ArrayList<Integer>();
        waitedResult.add(3);
        waitedResult.add(2);
        waitedResult.add(1);
        assertTrue("Le résultat doit-être [3,2,1]",
                compareList(waitedResult,dfGroupedToTestGroupedByNom.count())
                        &&
                        dfGroupedToTestGroupedByNom.count().size() == waitedResult.size());
    }

    @Test
    public void testSumLineOnGroup() throws ExceptionString, ExceptionColIsGrouped {


        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(6000);
        resultWaited.add(4010);
        resultWaited.add(13000);

        assertTrue("Victor doit avoir 6000, Maxime 4010, Selena 13000",
                compareList(resultWaited,dfGroupedToTestGroupedByNom.sum("RevenusInt")));
    }


    @Test
    public void testAvgLineOnGroup() throws ExceptionString, ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(2000);
        resultWaited.add(2005);
        resultWaited.add(13000);
        assertTrue("Victor doit avoir 2000, Maxime 2005, Selena 13000",
                compareList(resultWaited,dfGroupedToTestGroupedByNom.avg("RevenusInt")));
    }

    @Test
    public void testMaxLineOnGroup() throws ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(3000);
        resultWaited.add(4000);
        resultWaited.add(13000);

        assertTrue("Victor doit avoir 3000, Maxime 4000, Selena 13000",
                compareList(resultWaited,dfGroupedToTestGroupedByNom.max("RevenusInt")));
    }

    @Test
    public void testMinLineOnGroup() throws ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(1000);
        resultWaited.add(10);
        resultWaited.add(13000);

        assertTrue("Victor doit avoir 1000, Maxime 10, Selena 13000",
                compareList(resultWaited,dfGroupedToTestGroupedByNom.min("RevenusInt"))
        );
    }




    /*CES PREMIERS TESTS S'EFFECTUEUNT SUR UN GROUP BY SUR LES COLONNES NOM ET PRENOM*/

    @Test
    public void testCountLineOnGroupNomPrenom(){


        ArrayList<Integer> waitedResult = new ArrayList<Integer>();
        waitedResult.add(2);
        waitedResult.add(2);
        waitedResult.add(1);
        waitedResult.add(1);
        assertTrue("Le résultat doit-être [2,2,1,1]",
                compareList(waitedResult,dfGroupedToTestGroupedByNomPrenom.count())
                        &&
                        dfGroupedToTestGroupedByNomPrenom.count().size() == waitedResult.size());
    }

    @Test
    public void testSumLineOnGroupNomPrenom() throws ExceptionString, ExceptionColIsGrouped {

        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(5000.0);
        resultWaited2.add(4010.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);
        assertTrue("Victor Baverel doit avoir 5000, Maxime Isnel 4010, Selena Gomes 13000, Victor Valdes 1000",
                compareList(resultWaited2,dfGroupedToTestGroupedByNomPrenom.sum("RevenusDouble")));
    }


    @Test
    public void testAvgLineOnGroupNomPrenom() throws ExceptionString, ExceptionColIsGrouped {


        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(2500.0);
        resultWaited2.add(2005.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);
        assertTrue("Victor Baverel doit avoir 2500, Maxime Isnel 2005, Selena Gomes 13000, Victor Valdes 1000",
                compareList(resultWaited2,dfGroupedToTestGroupedByNomPrenom.avg("RevenusDouble")));

        dfGroupedToTestGroupedByNomPrenom.avgPrint("RevenusDouble");
    }

    @Test
    public void testMaxLineOnGroupNomPrenom() throws ExceptionColIsGrouped {



        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(3000.0);
        resultWaited2.add(4000.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);

        assertTrue("Victor Baverel doit avoir 3000, Maxime Isnel 2000, Selena Gomes 13000, Victor Valdes 1000",
                compareList(resultWaited2,dfGroupedToTestGroupedByNomPrenom.max("RevenusDouble")));




        ArrayList<String> resultWaited3 = new ArrayList<String>();
        resultWaited3.add("Kafka sur le rivage");
        resultWaited3.add("L'assommoir");
        resultWaited3.add("Le rouge et le noir");
        resultWaited3.add("Le bourreau de Gaudi");


        assertTrue("Victor Baverel doit avoir Kafka sur le rivage, Maxime Isnel L'assommoir," +
                        " Selena Gomes Le Rouge et le Noir, Victor Valdes Le Bourreau de Gaudi",
                compareList(resultWaited3, dfGroupedToTestGroupedByNomPrenom.max("Livres préférés")));
    }

    @Test
    public void testMinLineOnGroupNomPrenom() throws ExceptionColIsGrouped {

        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(2000.0);
        resultWaited2.add(10.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);

        assertTrue("Victor Baverel doit avoir 2000, Maxime Isnel 10, Selena Gomes 13000, Victor Valdes 1000",
                compareList(resultWaited2,dfGroupedToTestGroupedByNomPrenom.min("RevenusDouble")));


        ArrayList<String> resultWaited3 = new ArrayList<String>();
        resultWaited3.add("Bel-ami");
        resultWaited3.add("20 000 lieues sous les mers");
        resultWaited3.add("Le rouge et le noir");
        resultWaited3.add("Le bourreau de Gaudi");

        assertTrue("Victor Baverel doit avoir Bel-ami, Maxime Isnel 20 000 lieues sous les mers," +
                        " Selena Gomes Le Rouge et le Noir, Victor Valdes Le Bourreau de Gaudi",
                compareList(resultWaited3,dfGroupedToTestGroupedByNomPrenom.min("Livres préférés")));
    }



    /*TESTS D'OPERATION ARITHMETIQUES SUR DES COLONNES STRING*/

    @Test(expected = ExceptionString.class)
    public void testAvgString() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.avg("Livres préférés");
    }

    @Test(expected = ExceptionString.class)
    public void testSumString() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.sum("Livres préférés");
    }




    /*TESTS D'OPERATION SUR DES COLONNES DEJA GROUPEES SUR DES COLONNES STRING*/

    @Test(expected = ExceptionColIsGrouped.class)
    public void testAvgAlreadyGrouped() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.avg("Nom");
    }

    @Test(expected = ExceptionColIsGrouped.class)
    public void testSumAlreadyGrouped() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.sum("Prénom");
    }

    @Test(expected = ExceptionColIsGrouped.class)
    public void testMinAlreadyGrouped() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.min("Nom");
    }

    @Test(expected = ExceptionColIsGrouped.class)
    public void testMaxAlreadyGrouped() throws ExceptionString, ExceptionColIsGrouped {
        dfGroupedToTestGroupedByNomPrenom.max("Prénom");
    }

    /**
     * Vérifie que list1 et list2 sont bien égales
     * @param list2
     * @param list1
     * @return
     */
    private boolean compareList(ArrayList<? extends Comparable> list1, ArrayList<? extends Comparable> list2) {
        if(list2.size()!=list1.size()){
            return false;
        }else{
            for(int i = 0; i<list1.size(); i++){
                if(!list2.contains(list1.get(i)))
                    return false;
            }
        }
        return true;
    }

    @Test
    public void testPrintAvg() throws ExceptionString, ExceptionColIsGrouped {
        assertEquals("Prénom\t\t\tNom\t\t\tAVG RevenusInt\n" +
                "Selena\t\t\tGomes\t\t\t13000\n" +
                "Maxime\t\t\tIsnel\t\t\t2005\n" +
                "Victor\t\t\tBaverel\t\t\t2500\n" +
                "Victor\t\t\tValdes\t\t\t1000\n",dfGroupedToTestGroupedByNomPrenom.avgPrint("RevenusInt") );
    }

    @Test
    public void testPrintMin() throws ExceptionString, ExceptionColIsGrouped {
        assertEquals("Prénom\t\t\tNom\t\t\tMIN RevenusInt\n" +
                "Victor\t\t\tBaverel\t\t\t1000\n" +
                "Maxime\t\t\tIsnel\t\t\t10\n" +
                "Selena\t\t\tGomes\t\t\t13000\n", dfGroupedToTestGroupedByNom.minPrint("RevenusInt"));
    }

    @Test
    public void testPrintMax() throws ExceptionString, ExceptionColIsGrouped {
        assertEquals("Prénom\t\t\tNom\t\t\tMAX RevenusInt\n" +
                "Victor\t\t\tBaverel\t\t\t3000\n" +
                "Maxime\t\t\tIsnel\t\t\t4000\n" +
                "Selena\t\t\tGomes\t\t\t13000\n", dfGroupedToTestGroupedByNom.maxPrint("RevenusInt"));
    }


    @Test
    public void testPrintSum() throws ExceptionString, ExceptionColIsGrouped {
        assertEquals("Prénom\t\t\tNom\t\t\tSUM RevenusInt\n" +
                "Selena\t\t\tGomes\t\t\t13000\n" +
                "Maxime\t\t\tIsnel\t\t\t4010\n" +
                "Victor\t\t\tBaverel\t\t\t5000\n" +
                "Victor\t\t\tValdes\t\t\t1000\n", dfGroupedToTestGroupedByNomPrenom.sumPrint("RevenusInt"));
    }

    @Test
    public void testPrintCount() throws ExceptionString, ExceptionColIsGrouped {
        assertEquals("Prénom\t\t\tNom\t\t\tCOUNT\n" +
                "Selena\t\t\tGomes\t\t\t1\n" +
                "Maxime\t\t\tIsnel\t\t\t2\n" +
                "Victor\t\t\tBaverel\t\t\t2\n" +
                "Victor\t\t\tValdes\t\t\t1\n", dfGroupedToTestGroupedByNomPrenom.countPrint());
    }

}
