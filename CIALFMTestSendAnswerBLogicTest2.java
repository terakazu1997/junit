package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestSendAnswerBLogic;

public class CIALFMTestSendAnswerBLogicTest2 extends BaseJunitTestVariable{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        testSendAnswerBLogic= (CIALFMTestSendAnswerBLogic) csx.getBean("testSendAnswerBLogic");
        plus1.add(Calendar.DATE, 1);
        minus1.add(Calendar.DATE, -1);
        plus30.add(Calendar.DATE, 30);
        plus31.add(Calendar.DATE, 31);
        plus32.add(Calendar.DATE, 32);
        plus61.add(Calendar.DATE, 61);
        plus62.add(Calendar.DATE, 62);
        todayPlus1 =df.format(plus1.getTime());
        todayMinus1 =df.format(minus1.getTime());
        todayPlus30 =df.format(plus30.getTime());
        todayPlus31 =df.format(plus31.getTime());
        todayPlus32 =df.format(plus32.getTime());
        todayPlus61 =df.format(plus61.getTime());
        todayPlus62 =df.format(plus62.getTime());
    }
    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/外貨資格更新テスト回答送信BLogic動作確認2/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        userData = new ChangeSelectUserData(pw);
        periodData = new ChangeSelectPeriodData(pw);
        foreignHistoryData = new SelectForeignHistoryData(pw);
    }
    public void playTest(int testNo,String[] periodList,int fMAdDayCount,String lastFMAdDate, String lastFMPassDate,int lastFMScore, int lastFMPassNo,int okCnt,boolean passFlg) {
        try {
            fileMake(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%dデータ準備開始",testNo));
        System.out.println(periodList);
        periodData.updateFMPeriodChange(periodList);
        userData.updateUserFMInfoChange(fMAdDayCount, lastFMAdDate, lastFMPassDate,lastFMScore,lastFMPassNo);
        paramP.setOkCnt(okCnt);
        paramP.setPassFlg(passFlg);
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%d開始",testNo));
        pw.println("●結果");
        pw.println(testSendAnswerBLogic.execute(paramP));
        userData.selectFMUserInfo();
        foreignHistoryData.selectForeignHistoryInfo();
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%d終了",testNo));
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        userData.updateUserFMInfoClear();
        pw.println(String.format("外貨資格更新テスト回答送信BLogic動作確認2_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No1(){playTest(1,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,0,0,5,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No2(){playTest(2,new String[] {today,todayPlus30,todayPlus31,todayPlus61},1,null,null,0,0,5,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No3(){playTest(3,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,notToday,notToday,0,0,5,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No4(){playTest(4,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,2,0,0,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No5(){playTest(5,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,0,0,10,false);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No6(){playTest(6,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,0,0,5,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No7(){playTest(7,new String[] {today,todayPlus30,todayPlus31,todayPlus61},1,null,null,0,0,5,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No8(){playTest(8,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,notToday,notToday,0,0,5,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No9(){playTest(9,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,2,0,0,true);}
    @Test
    public void test外貨資格更新テスト回答送信BLogic動作確認2_No10(){playTest(10,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,null,0,0,10,true);}
}
