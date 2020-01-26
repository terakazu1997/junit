package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.junit.Test;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestInitBLogic;
import jp.terasoluna.utlib.spring.DaoTestCase;

public class CIALFMTestInitBLogicTest extends DaoTestCase{
    private QueryDAO queryDAO = null;
    PrintWriter pw = null;
    File file = null;
    changeForeignMoneyData changeFMData  = null;
    CIALFMTestInitBLogic testInitBLogic = new CIALFMTestInitBLogic();

    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        changeFMData = new changeForeignMoneyData(pw);
    }

    @Test
    public void test問題回答毎回ランダム確認() throws IOException {
        fileMake("test問題回答毎回ランダム確認");
        pw.println("test問題回答毎回ランダム確認");
        changeFMData.UpdateQuestionDefault();
        changeFMData.updateQuestionRandom25();
        testInitBLogic.setQueryDAO(queryDAO);
        for(int i =0; i<=25; i++) {
            String result = testInitBLogic.execute();
            pw.println(result);
        }
        changeFMData.UpdateQuestionDefault();
        pw.println("test問題回答毎回ランダム確認終了");
    }
    @Test
    public void test取得10件() throws IOException {
        fileMake("テスト取得10件確認");
        pw.println("テスト取得10件確認");
        changeFMData.updateAllPass();
        testInitBLogic.setQueryDAO(queryDAO);
        String result = testInitBLogic.execute();
        pw.println(result);
        changeFMData.UpdateQuestionDefault();
        pw.println("テスト取得10件確認終了");
    }
    @Test
    public void dtest取得9件() throws IOException {
        fileMake("テスト取得9件確認");
        pw.println("テスト取得9件確認");
        changeFMData.updateQuestionRandom9();
        testInitBLogic.setQueryDAO(queryDAO);
        String result = testInitBLogic.execute();
        assertEquals(result,"");
        changeFMData.UpdateQuestionDefault();
        pw.println("テスト取得9件確認終了");
    }
    @Test
    public void dtest取得0件() throws IOException {
        fileMake("テスト取得0件確認");
        pw.println("テスト取得0件確認");
        changeFMData.updateQuestionZero();
        testInitBLogic.setQueryDAO(queryDAO);
        String result = testInitBLogic.execute();
        assertEquals(result,"");
        changeFMData.UpdateQuestionDefault();
        pw.println("テスト取得0件確認終了");
        pw.close();
    }
    @Override
    protected void setUpData() throws Exception {
    }
    public void setQueryDAO(QueryDAO param){
        this.queryDAO = param;
    }

    @Override
    protected void cleanUpData() throws Exception {}
    @Override
    protected String[] doGetConfigLocations() {
        return new String[] {"junitTest/dao-test.xml"};
    }

}
