package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestChangeState.class,
   TestThrowCraps.class,
   TestPrintCraps.class
})
public class JunitTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}
