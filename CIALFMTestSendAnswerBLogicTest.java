package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.bean.CIAFMTestSendAnswerInput;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestSendAnswerBLogic;

public class CIALFMTestSendAnswerBLogicTest {
    PrintWriter pw = null;
    File file = null;
    ChangeSelectUserData userData  = null;
    ChangeSelectPeriodData periodData = null;
    SelectForeignHistoryData foreignHistoryData = null;
    static CIALFMTestSendAnswerBLogic testSendAnswerBLogic = null;
    private static ClassPathXmlApplicationContext csx = null;
    CIAFMTestSendAnswerInput paramP = new CIAFMTestSendAnswerInput();
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    final String notToday = "20200101";
    final String today = df.format(new Date());
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        testSendAnswerBLogic= (CIALFMTestSendAnswerBLogic) csx.getBean("testSendAnswerBLogic");
    }
    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/外貨資格更新テスト回答送信BLogic動作確認/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        userData = new ChangeSelectUserData(pw);
        periodData = new ChangeSelectPeriodData(pw);
        foreignHistoryData = new SelectForeignHistoryData(pw);
    }
    public void playTest(int testNo,int periodNo,int fMAdDayCount,String lastFMAdDate, String lastFMPassDate,int lastFMScore, int lastFMPassNo,int okCnt,boolean passFlg) {
        try {
            fileMake(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%dデータ準備開始",testNo));
        periodData.updateFMPeriodChange(periodNo);
        userData.updateUserFMInfoChange(fMAdDayCount, lastFMAdDate, lastFMPassDate,lastFMScore,lastFMPassNo);
        paramP.setOkCnt(okCnt);
        paramP.setPassFlg(passFlg);
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%d開始",testNo));
        pw.println("●結果");
        pw.println(testSendAnswerBLogic.execute(paramP));
        userData.selectFMUserInfo();
        foreignHistoryData.selectForeignHistoryInfo();
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%d終了",testNo));
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        userData.updateUserFMInfoClear();
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No1(){playTest(1,5,0,null,null,2,0,0,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No2(){playTest(2,5,0,null,null,0,0,5,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No3(){playTest(3,5,0,notToday,notToday,2,0,10,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No4(){playTest(4,5,0,null,null,2,0,20,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No5(){playTest(5,5,1,null,null,2,4,0,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No6(){playTest(6,5,0,null,null,2,0,0,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No7(){playTest(7,5,0,null,notToday,0,0,5,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No8(){playTest(8,5,1,null,null,2,0,10,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認_No9(){playTest(9,5,1,null,null,2,4,20,true);}
}
