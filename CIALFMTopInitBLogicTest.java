package junitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;

import org.junit.Test;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.thin.tutorial.web.test.blogic.CIALFMTopInitBLogic;
import jp.terasoluna.utlib.spring.DaoTestCase;

public class CIALFMTopInitBLogicTest extends DaoTestCase{
    private QueryDAO queryDAO = null;
    private UpdateDAO updateDAO = null;
    PrintWriter pw = null;
    File file = null;
    changeForeignMoneyData changeFMData  = null;
    CIALFMTopInitBLogic topInitBLogic = new CIALFMTopInitBLogic();

    public void fileMake(String fileName) throws IOException {
        file = new File(String.format("junitTestLog/%s.txt",fileName));
        pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
        changeFMData = new changeForeignMoneyData(pw);
    }
    @Test
    public void test当日受講回数受講日合格No() throws ParseException, IOException {
        topInitBLogic.setQueryDAO(queryDAO);
        topInitBLogic.setUpdateDAO(updateDAO);
        fileMake("当日受講回数受講日合格No確認");
        pw.println("当日受講回数0受講日null合格No0");
        changeFMData.updateUserFMInfoClear();
        System.out.println(topInitBLogic.execute());
        pw.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoClear();
        System.out.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoClear();
        System.out.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoClear();
        System.out.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoChange(2,"20200101",0);
        pw.println("当日受講回数2受講日今日日付以外合格No0");
        System.out.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoChange(2,"20200126",0);
        pw.println("当日受講回数2受講日今日日付No0");
        changeFMData.updateUserFMInfoChange(2,"20200126",1);
        pw.println("当日受講回数2受講日今日日付No1");
        System.out.println(topInitBLogic.execute());
        changeFMData.updateUserFMInfoClear();
        pw.close();
    }
    @Override
    protected void setUpData() throws Exception {
    }
    public void setQueryDAO(QueryDAO param){
        this.queryDAO = param;
    }
    public void setUpdateDAO(UpdateDAO param){
        this.updateDAO = param;
    }

    @Override
    protected void cleanUpData() throws Exception {}
    @Override
    protected String[] doGetConfigLocations() {
        return new String[] {"junitTest/dao-test.xml"};
    }

}
