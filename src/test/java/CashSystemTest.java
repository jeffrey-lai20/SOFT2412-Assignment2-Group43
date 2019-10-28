import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class CashSystemTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
       
    @After
    public void restoreStreams() {
        System.setOut(systemOut);
        System.setIn(systemIn);
    }
    
    @Test
    public void OutputText(){
        CashSystem testCashSystem = new CashSystem();
        testCashSystem.cashChoiceText();
        String test = "\nPlease choose the note or coin value,\n" +
                "Followed by the amount to be inputted:\n"+
                "(E.g. 1 10 = $200 inputted )\n"+
                "    1. $20 note\n"+
                "    2. $10 note\n"+
                "    3.  $5 note\n"+
                "    4.  $2 coin\n"+
                "    5.  $1 coin\n"+
                "    6. 50c coin\n"+
                "    7. 20c coin\n"+
                "    8. 10c coin\n"+
                "    9. Cancel Transaction\n";
        assertEquals(test, testOut.toString());
    }


    @Test
    public void cashInputTest() {
        String test = ("\nYour total value is : $100.0\nPlease choose the note or coin value,\n" +
        "Followed by the amount to be inputted:\n"+
        "(E.g. 1 10 = $200 inputted )\n"+
        "    1. $20 note\n"+
        "    2. $10 note\n"+
        "    3.  $5 note\n"+
        "    4.  $2 coin\n"+
        "    5.  $1 coin\n"+
        "    6. 50c coin\n"+
        "    7. 20c coin\n"+
        "    8. 10c coin\n"+
        "    9. Cancel Transaction\n"+
        "Transaction cancelled. See you next time!\n"
        );
        String input = "9";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        CashSystem testCashSystem = new CashSystem();
        testCashSystem.cashInput(100);
        assertEquals(test, testOut.toString());
    }


    @Test
    public void cashInputTest2() {
        String test = ("\nYour total value is : $100.0\nPlease choose the note or coin value,\n" +
                "Followed by the amount to be inputted:\n"+
                "(E.g. 1 10 = $200 inputted )\n"+
                "    1. $20 note\n"+
                "    2. $10 note\n"+
                "    3.  $5 note\n"+
                "    4.  $2 coin\n"+
                "    5.  $1 coin\n"+
                "    6. 50c coin\n"+
                "    7. 20c coin\n"+
                "    8. 10c coin\n"+
                "    9. Cancel Transaction\n"+
                "Thank you for purchasing !\n"+
                "Please collect your change: $0.0\n"
        );
        String input = "1 5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        CashSystem testCashSystem = new CashSystem();
        testCashSystem.cashInput(100);
        assertEquals(test, testOut.toString());
    }

    @Test
    public void cashInputTest3() {
        String test = ("\nYour total value is : $100.0\nPlease choose the note or coin value,\n" +
                "Followed by the amount to be inputted:\n" +
                "(E.g. 1 10 = $200 inputted )\n" +
                "    1. $20 note\n" +
                "    2. $10 note\n" +
                "    3.  $5 note\n" +
                "    4.  $2 coin\n" +
                "    5.  $1 coin\n" +
                "    6. 50c coin\n" +
                "    7. 20c coin\n" +
                "    8. 10c coin\n" +
                "    9. Cancel Transaction\n" +
                "Total inputted cash value:80.0\n" +
                "Remaining value:20.0" +
                "\nPlease choose the note or coin value,\n" +
                "Followed by the amount to be inputted:\n"+
                "(E.g. 1 10 = $200 inputted )\n"+
                "    1. $20 note\n"+
                "    2. $10 note\n"+
                "    3.  $5 note\n"+
                "    4.  $2 coin\n"+
                "    5.  $1 coin\n"+
                "    6. 50c coin\n"+
                "    7. 20c coin\n"+
                "    8. 10c coin\n"+
                "    9. Cancel Transaction\n"+
                "Thank you for purchasing !\n"+
                "Please collect your change: $0.0\n"
        );
        String simulatedUserInput = "1 4" + System.getProperty("line.separator")
                + "1 1" + System.getProperty("line.separator");
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        CashSystem testCashSystem = new CashSystem();
        testCashSystem.cashInput(100);
        System.setIn(savedStandardInputStream);
        assertEquals(test, testOut.toString());
    }
    @Test
    public void changeSystemTest(){
        CashSystem testCashSystem = new CashSystem();
        List<Integer> testList = new ArrayList<Integer>(){{
            add(1);
            add(1);
        }};
        assertEquals(testCashSystem.changeSystem(100,160),60.0,0.0);
        assertEquals(testCashSystem.changeSystem(100,150),50.0,0.0);
        assertEquals(testCashSystem.changeSystem(100,115),15.0,0.0);
        assertEquals(testCashSystem.changeSystem(100,106),6.0,0.0);
        assertEquals(testCashSystem.changeSystem(100,103),3.0,0.0);
        assertEquals(testCashSystem.changeSystem(100,101.5),1.5,0.0);
        assertEquals(testCashSystem.changeSystem(100,100.6),0.6,0.0);
        assertEquals(testCashSystem.changeSystem(100,100.3),0.3,0.0);
        assertEquals(testCashSystem.changeSystem(100,100.1),0.1,0.0);
        assertNotEquals(testCashSystem.changeSystem(100,90),0.1,0.0);

        assertEquals(testCashSystem.inputChecker("1 1").size(),testList.size());
        assertEquals(testCashSystem.inputChecker("a a").size(),testList.size());

    }
    @Test
    public void cashHandlerTest(){
        CashSystem testSystem = new CashSystem();
        List<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),20,0.0);
        testList = new ArrayList<>();
        testList.add(3);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),10,0.0);
        testList = new ArrayList<>();
        testList.add(4);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),4,0.0);
        testList = new ArrayList<>();
        testList.add(5);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),2,0.0);
        testList = new ArrayList<>();
        testList.add(6);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),1,0.0);
        testList = new ArrayList<>();
        testList.add(7);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),0.4,0.0);
        testList = new ArrayList<>();
        testList.add(8);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),0.2,0.0);
        testList = new ArrayList<>();
        testList.add(9);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),-1,0.0);
        testList = new ArrayList<>();
        testList.add(200);
        testList.add(2);
        assertEquals(testSystem.cashHandler(testList),0,0.0);
    }
}
