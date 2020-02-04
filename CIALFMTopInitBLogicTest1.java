package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTopInitBLogic;

public class CIALFMTopInitBLogicTest1 extends BaseJunitTestVariable{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        topInitBLogic = (CIALFMTopInitBLogic) csx.getBean("topInitBLogic");
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
        file = new File(String.format("junitTestLog/外貨資格更新トップ初期表示BLogic動作確認1/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        userData = new ChangeSelectUserData(pw);
        periodData = new ChangeSelectPeriodData(pw);
    }
    public void playTest(int testNo,String[] periodList,int fmAdCount,String lastFMAdDate, int lastFMPassNo) {
        try {
            fileMake(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%dデータ準備開始",testNo));
        periodData.updateFMPeriodChange(periodList);
        userData.updateUserFMInfoChange(fmAdCount, lastFMAdDate, lastFMPassNo);
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%d開始",testNo));
        pw.println("●結果");
        try {
            pw.println(topInitBLogic.execute());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%d終了",testNo));
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        userData.updateUserFMInfoClear();
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認1_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No1() {playTest(1,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No2() {playTest(2,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No3() {playTest(3,new String[] {today,todayPlus30,todayPlus31,todayPlus61},1,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No4() {playTest(4,new String[] {today,todayPlus30,todayPlus31,todayPlus61},2,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No5() {playTest(5,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,today,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No6() {playTest(6,new String[] {today,todayPlus30,todayPlus31,todayPlus61},1,today,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認1_No7() {playTest(7,new String[] {today,todayPlus30,todayPlus31,todayPlus61},2,today,0);}
}
