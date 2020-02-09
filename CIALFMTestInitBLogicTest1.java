package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.terasoluna.thin.tutorial.web.test.bean.CIAFMUpdateAttendanceOutput;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestInitBLogic;

public class CIALFMTestInitBLogicTest1 extends BaseJunitTestVariable{
    static final int patternA = 1;
    static final int patternB = 2;
    static final int patternAB = 3;

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
        file = new File(String.format("junitTestLog/外貨資格更新テスト初期表示BLogic動作確認1/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        questionData = new ChangeSelectQuestionAnswerData(pw);
        periodData = new ChangeSelectPeriodData(pw);
    }
    public void playTest(int testNo,String periodList[],int questionCnt,int choiceCnt,int questionPatternJudgFlg,int choicePatternJudgFlg,String choiceCategory, String questionDeleteFlg, String choiceDeleteFlg) {
        try {
            fileMake(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%d",testNo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionData.UpdateQuestionDefault();
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%dデータ準備開始",testNo));
        periodData.updateFMPeriodChange(periodList);
        questionData.updateMakeTestQuestion(questionCnt,choiceCnt,questionPatternJudgFlg,choicePatternJudgFlg,choiceCategory,questionDeleteFlg,choiceDeleteFlg);
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%dデータ準備終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%d開始",testNo));
        pw.println("●結果");
        try {
            String result = testInitBLogic.execute();
            if(result.equals("")) {
                pw.println("''(ブランク)");
            }else{
                ObjectMapper mapper = new ObjectMapper();
                List<CIAFMUpdateAttendanceOutput> outputList;
                    outputList = mapper.readValue(result, new TypeReference<List<CIAFMUpdateAttendanceOutput>>(){});
                    int i = 1;
                    for(CIAFMUpdateAttendanceOutput output : outputList) {
                        pw.println(String.format("%d問目の出力問題リスト", i));
                        pw.println(String.format(
                                "問題ID:%s,問題文:%s,解説:%s,選択肢リスト:%s,正答リスト:%s",
                                output.getQuestionId(),output.getQuestionSentence(),output.getQuestionExplanation(),output.getChoiceList(),output.getPassList()
                        ));
                        i++;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (Exception e) {
                // TODO 自動生成された catch ブロック
                System.out.print(e);
                pw.println(e);
            }
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%d終了",testNo));
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%dデータ戻し開始",testNo));
        periodData.updateFMPeriodDefault();
        questionData.UpdateQuestionDefault();
        pw.println(String.format("外貨資格更新テスト初期表示BLogic動作確認1_No%dデータ戻し終了",testNo));
        pw.close();
    }
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No1() {playTest(1,new String[] {todayPlus1,todayPlus31,todayPlus32,todayPlus62},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No2() {playTest(2,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No3() {playTest(3,new String[] {notToday,today,todayPlus1,todayPlus31},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No4() {playTest(4,new String[] {notToday,todayMinus1,today,todayPlus30},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No5() {playTest(5,new String[] {notTodayLastYearFrom,notTodayLastYearTo,notToday,today},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No6() {playTest(6,new String[] {notTodayLastYearFrom,notTodayLastYearTo,notToday,todayPlus1},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No7() {playTest(7,new String[] {today,todayPlus30,today,todayPlus30},20,4,patternAB,patternAB,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No8() {playTest(8,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2",null,null);}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No9() {playTest(9,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2","0","0");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No10() {playTest(10,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2","1","1");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No11() {playTest(11,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No12() {playTest(12,new String[] {today,todayPlus30,todayPlus31,todayPlus61},10,4,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No13() {playTest(13,new String[] {today,todayPlus30,todayPlus31,todayPlus61},9,4,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No14() {playTest(14,new String[] {today,todayPlus30,todayPlus31,todayPlus61},0,4,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No15() {playTest(15,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No16() {playTest(16,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternB,"9","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No17() {playTest(17,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,4,patternA,patternA,"2","","1");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No18() {playTest(18,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,0,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No19() {playTest(19,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,2,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No20() {playTest(20,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,3,patternA,patternA,"2","","");}
    @Test
    public void test外貨資格更新テスト初期表示BLogic動作確認1_No21() {playTest(21,new String[] {today,todayPlus30,todayPlus31,todayPlus61},20,5,patternA,patternA,"2","","");}
}
