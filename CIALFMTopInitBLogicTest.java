package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTopInitBLogic;

public class CIALFMTopInitBLogicTest{
    PrintWriter pw = null;
    File file = null;
    ChangeSelectUserData userData  = null;
    ChangeSelectPeriodData periodData = null;
    static CIALFMTopInitBLogic topInitBLogic = null;
    private static ClassPathXmlApplicationContext csx = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    final String notToday = "20200101";
    final String today = df.format(new Date());

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        topInitBLogic = (CIALFMTopInitBLogic) csx.getBean("topInitBLogic");
    }
    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/外貨資格更新トップ初期表示BLogic動作確認/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        userData = new ChangeSelectUserData(pw);
        periodData = new ChangeSelectPeriodData(pw);
    }
    public void playTest(int testNo,int periodNo,int fmAdCount,String lastFMAdDate, int lastFMPassNo) {
        try {
            fileMake(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%dデータ準備開始",testNo));
        if(testNo != 25) {
            periodData.updateFMPeriodChange(periodNo);
        } else {
            periodData.updateFMPeriodChangeFuture(1, "20400101", "20401231");
        }
        userData.updateUserFMInfoChange(fmAdCount, lastFMAdDate, lastFMPassNo);
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%d開始",testNo));
        pw.println("●結果");
        try {
            pw.println(topInitBLogic.execute());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%d終了",testNo));
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        userData.updateUserFMInfoClear();
        pw.println(String.format("外貨資格更新トップ初期表示BLogic動作確認_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No1() {playTest(1,1,0,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No2() {playTest(2,1,1,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No3() {playTest(3,1,2,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No4() {playTest(4,1,0,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No5() {playTest(5,1,1,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No6() {playTest(6,1,2,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No7() {playTest(7,1,0,today,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No8() {playTest(8,1,1,today,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No9() {playTest(9,1,2,today,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No10() {playTest(10,1,0,today,1);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No11() {playTest(11,1,1,today,1);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No12() {playTest(12,1,2,today,1);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No13() {playTest(13,5,0,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No14() {playTest(14,5,1,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No15() {playTest(15,5,2,null,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No16() {playTest(16,5,0,notToday,0);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No17() {playTest(17,5,1,notToday,4);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No18() {playTest(18,5,2,notToday,4);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No19() {playTest(19,5,0,today,4);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No20() {playTest(20,5,1,today,4);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No21() {playTest(21,5,2,today,4);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No22() {playTest(22,5,0,today,5);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No23() {playTest(23,5,1,today,5);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No24() {playTest(24,5,2,today,5);}
    @Test
    public void test外貨資格更新トップ初期表示BLogic動作確認_No25() {playTest(25,5,2,today,5);}
}
