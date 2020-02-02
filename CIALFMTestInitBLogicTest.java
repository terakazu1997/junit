package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestInitBLogic;

public class CIALFMTestInitBLogicTest {

    PrintWriter pw = null;
    File file = null;
    ChangeSelectQuestionAnswerData questionData  = null;
    ChangeSelectPeriodData periodData = null;
    static CIALFMTestInitBLogic testInitBLogic = null;
    private static ClassPathXmlApplicationContext csx = null;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        csx = new ClassPathXmlApplicationContext("junitTest/test.xml");
        testInitBLogic = (CIALFMTestInitBLogic) csx.getBean("testInitBLogic");
    }
    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/外貨資格更新テスト初期表示BLogic動作確認/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        questionData = new ChangeSelectQuestionAnswerData(pw);
        periodData = new ChangeSelectPeriodData(pw);
    }
    public void playTest(int testNo,int questionCnt,int[] choicePassStatus,String deleteProcess, int randomCnt) {
        try {
            fileMake(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("前提データ");
        periodData.selectFMPeriodInfo();
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%dデータ準備開始",testNo));
        if(testNo != 19) {
            questionData.updateMakeTestQuestion(questionCnt,choicePassStatus,deleteProcess);
        } else {
            questionData.updateQuestionZero();
        }
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%d開始",testNo));
        pw.println("●結果");
        for(int i =1; i<=randomCnt; i++) {
            String result = testInitBLogic.execute();
            if(result.equals("")) {
                pw.println("''(ブランク)");
            }else{
                pw.println(result);
            }
        }
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%d終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%dデータ戻し開始",testNo));
        questionData.UpdateQuestionDefault();
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No1() {playTest(1,50,new int[] {1,1,0,0},"patternX",50);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No2() {playTest(2,20,new int[] {1,0,0,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No3() {playTest(3,20,new int[] {0,1,0,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No4() {playTest(4,20,new int[] {0,0,1,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No5() {playTest(5,20,new int[] {0,0,0,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No6() {playTest(6,20,new int[] {1,1,0,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No7() {playTest(7,20,new int[] {1,0,1,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No8() {playTest(8,20,new int[] {1,0,0,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No9() {playTest(9,20,new int[] {0,1,1,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No10() {playTest(10,20,new int[] {0,1,0,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No11() {playTest(11,20,new int[] {0,0,1,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No12() {playTest(12,20,new int[] {0,1,1,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No13() {playTest(13,20,new int[] {1,0,1,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No14() {playTest(14,20,new int[] {1,1,0,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No15() {playTest(15,20,new int[] {1,1,1,0},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No16() {playTest(16,20,new int[] {1,1,1,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No17() {playTest(17,10,new int[] {1,1,1,1},"patternX",3);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No18() {playTest(18,9,new int[] {1,1,1,1},"patternX",1);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認_No19() {playTest(19,0,new int[] {1,1,1,1},"patternX",1);}
}
