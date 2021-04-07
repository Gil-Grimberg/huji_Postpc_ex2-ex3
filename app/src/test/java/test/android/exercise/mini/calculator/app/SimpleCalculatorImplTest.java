package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    // delete last
    calculatorUnderTest.deleteLast();
    // test
    String expected = "5+7";
    assertEquals(expected, calculatorUnderTest.output());

  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertMinus();
    // clear all
    calculatorUnderTest.clear();
    // test
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
  }

  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!
  @Test
  public void when_MoreThanOneInputGiven_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some digits inputs
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    // give one action
    calculatorUnderTest.insertPlus();

    assertEquals("12+", calculatorUnderTest.output());
  }
  @Test
  public void when_MoreThanOneActionGiven_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some digits inputs
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    // give one action
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();

    assertEquals("12+", calculatorUnderTest.output());
  }
  @Test
  public void when_EqualWithNoInput_than_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertEquals();
    assertEquals("0",calculatorUnderTest.output());
  }
  @Test
  public void when_EqualWithComplicatedInput_than_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // insert digits and actions
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    assertEquals("89",calculatorUnderTest.output());
  }
}