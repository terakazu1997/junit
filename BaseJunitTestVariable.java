package junitTest;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.terasoluna.thin.tutorial.web.test.bean.CIAFMTestSendAnswerInput;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestInitBLogic;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTestSendAnswerBLogic;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTopInitBLogic;

public class BaseJunitTestVariable {
    PrintWriter pw = null;
    File file = null;
    static ChangeSelectUserData userData  = null;
    static ChangeSelectPeriodData periodData = null;
    static ChangeSelectQuestionAnswerData questionData  = null;
    static SelectForeignHistoryData foreignHistoryData = null;
    static CIALFMTestSendAnswerBLogic testSendAnswerBLogic = null;
    static CIALFMTestInitBLogic testInitBLogic = null;
    static CIALFMTopInitBLogic topInitBLogic = null;
    static ClassPathXmlApplicationContext csx = null;
    static Calendar plus1 = Calendar.getInstance();
    static Calendar minus1 = Calendar.getInstance();
    static Calendar plus30 = Calendar.getInstance();
    static Calendar plus31 = Calendar.getInstance();
    static Calendar plus32 = Calendar.getInstance();
    static Calendar plus61 = Calendar.getInstance();
    static Calendar plus62 = Calendar.getInstance();

    static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    static final String notToday = "20200101";
    static final String notTodayLastYearFrom = "20190101";
    static final String notTodayLastYearTo = "20191231";
    static final String today = df.format(new Date());
    static String todayPlus1 = null;
    static String todayMinus1 = null;
    static String todayPlus30 = null;
    static String todayPlus31 = null;
    static String todayPlus32 = null;
    static String todayPlus61 = null;
    static String todayPlus62 = null;

    static CIAFMTestSendAnswerInput paramP = new CIAFMTestSendAnswerInput();

}
