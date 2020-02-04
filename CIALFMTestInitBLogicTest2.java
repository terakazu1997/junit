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

import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestInitBLogic;

public class CIALFMTestInitBLogicTest2 extends BaseJunitTestVariable{


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        testInitBLogic = (CIALFMTestInitBLogic) csx.getBean("testInitBLogic");
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
        file = new File(String.format("junitTestLog/外貨資格更新テスト初期表示BLogic動作確認2/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        questionData = new ChangeSelectQuestionAnswerData(pw);
        periodData = new ChangeSelectPeriodData(pw);
    }
    public void playTest(int testNo,String periodList[],int questionCnt, int[] choicePassStatus,int randomCnt) {
        try {
            fileMake(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%dデータ準備開始",testNo));
        periodData.updateFMPeriodChange(periodList);
        questionData.updateMakeTestQuestion(questionCnt, choicePassStatus);
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%d開始",testNo));
        pw.println("●結果");
        for(int i =1; i<=randomCnt; i++) {
            String result = testInitBLogic.execute();
            pw.println(result);
        }
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%d終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        questionData.UpdateQuestionDefault();
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認2_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No1() {playTest(1,new String[] {today,todayPlus30,todayPlus31,todayPlus61},50,new int[] {1,1,0,0},50);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No2() {playTest(2,new String[] {today,todayPlus30,todayPlus31,todayPlus61},50,new int[] {1,1,1,1},50);}
   @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No3() {playTest(3,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,0,0,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No4() {playTest(4,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,1,0,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No5() {playTest(5,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,0,1,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No6() {playTest(6,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,0,0,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No7() {playTest(7,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,1,0,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No8() {playTest(8,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,0,1,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No9() {playTest(9,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,0,0,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No10() {playTest(10,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,1,1,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No11() {playTest(11,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,1,0,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No12() {playTest(12,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,0,1,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No13() {playTest(13,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {0,1,1,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No14() {playTest(14,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,0,1,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No15() {playTest(15,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,1,0,1},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No16() {playTest(16,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,1,1,0},1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認2_No17() {playTest(17,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,new int[] {1,1,1,1},1);}
}
