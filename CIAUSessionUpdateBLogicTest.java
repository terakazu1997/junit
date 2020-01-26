package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.terasoluna.thin.tutorial.web.test.blogic.CIAUSessionUpdateBLogic;

public class CIAUSessionUpdateBLogicTest {
    @Test
    public void testExecute() {
        CIAUSessionUpdateBLogic sessionTest = new CIAUSessionUpdateBLogic();
        assertEquals("success",sessionTest.execute());
    }

}
