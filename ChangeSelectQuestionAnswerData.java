package junitTest;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ChangeSelectQuestionAnswerData extends BaseDatabase {

    public ChangeSelectQuestionAnswerData(PrintWriter pw) {
        super(pw);
    }

    public void selectFMQuestionInfo() {
        initConnectionDB();
        try {
            pw.println("・外貨資格更新問題、回答テーブル整形内容");
            //-> データを取得するSQLを準備
            ps = conn.prepareStatement(
                    "SELECT\n" +
                    "        CONCAT('パターン:',FMUQ.PATTERN_NO,' カテゴリ:',FMUQ.CATEGORY,' 問題番号:',FMUQ.QUESTION_ID,' 選択肢:',CHOICE_NO) AS uniqueNum\n" +
                    "        ,QUESTION_SENTENCE\n" +
                    "        ,QUESTION_EXPLANATION\n" +
                    "         ,CASE FMUQ.DELETE_FLG\n" +
                    "         WHEN '1' THEN '削除済み'\n" +
                    "         ELSE '削除されてない'\n" +
                    "         END AS '問題削除有無'\n" +
                    "        ,CHOICE_SENTENCE\n" +
                    "        ,CASE OK_FLG\n" +
                    "         WHEN '1' THEN '正答'\n" +
                    "         ELSE '不正答'\n" +
                    "         END as '正否'\n" +
                    "         ,CASE FMUA.DELETE_FLG\n" +
                    "         WHEN '1' THEN '削除済み'\n" +
                    "         ELSE '削除されてない'\n" +
                    "         END AS '回答削除有無'\n" +
                    "    FROM\n" +
                    "        test_mysql_database.FMUQ as FMUQ\n" +
                    "        INNER JOIN test_mysql_database.FMUA as FMUA\n" +
                    "        on FMUQ.PATTERN_NO = FMUA.PATTERN_NO\n" +
                    "        AND FMUQ.CATEGORY = FMUA.CATEGORY\n" +
                    "        AND FMUQ.QUESTION_ID = FMUA.QUESTION_ID" +
                    "    ORDER BY\n" +
                    "        FMUQ.PATTERN_NO, FMUQ.CATEGORY, FMUQ.QUESTION_ID"
            );
            //-> SQLを実行してデータを取得
            rs = ps.executeQuery();
            //-> データを表示
            pw.println("パターン-カテゴリ-問題番号-選択肢の連番,問題文,解説,問題削除有無,選択肢内容,正否,正否削除有無");
            pw.println("(削除有無は、削除フラグが1なら削除済み1以外なら削除されていないと表示、正否は正答フラグが1なら正答、1以外なら不正答を表示)");
            while(rs.next()) {
                pw.print(rs.getString("uniqueNum"));
                pw.print(", ");
                pw.print(rs.getString("QUESTION_SENTENCE"));
                pw.print(", ");
                pw.print(rs.getString("QUESTION_EXPLANATION"));
                pw.print(", ");
                pw.print(rs.getString("問題削除有無"));
                pw.print(", ");
                pw.print(rs.getString("CHOICE_SENTENCE"));
                pw.print(", ");
                pw.print(rs.getString("正否"));
                pw.print(", ");
                pw.println(rs.getString("回答削除有無"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    public void UpdateQuestionDefault() {
        // (3) SQL送信用インスタンスの作成
        initConnectionDB();
        try {
            ps = conn.prepareStatement(
                "DELETE FROM FMUQ WHERE CATEGORY = '2' OR CATEGORY = '9'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
                "DELETE FROM FMUA WHERE CHOICE_NO = '5' OR CATEGORY = '2' OR CATEGORY = '9'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
              "UPDATE FMUA SET DELETE_FLG = '',PATTERN_NO = 'A'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
              "UPDATE FMUQ SET DELETE_FLG = '',PATTERN_NO = 'A'"
            );
            ps.executeUpdate();
            selectFMQuestionInfo();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
    public void updateMakeTestQuestion(int questionCnt,int[] choicePassStatus) {
        // (3) SQL送信用インスタンスの作成
        initConnectionDB();
        try {
            ps = conn.prepareStatement(
                "UPDATE FMUQ SET PATTERN_NO = 'X'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
                "UPDATE FMUA SET PATTERN_NO = 'X'"
            );
            ps.executeUpdate();
            for(int i =1; i<= questionCnt; i++) {
                ps = conn.prepareStatement(
                     "insert into FMUQ VALUES('A','2',?,?,?,'')"
                 );
                ps.setInt(1, i);
                ps.setString(2, String.format("%d問目の問題", i));
                ps.setString(3, String.format("%d問目の問題の解説", i));
                ps.executeUpdate();
                for(int j=1; j<5; j++) {
                    ps = conn.prepareStatement(
                        "INSERT INTO FMUA VALUES ('A','2',?,?,?,?,'');"
                    );
                    ps.setInt(1, i);
                    ps.setInt(2, j);
                    ps.setString(3, String.format("%d問目の%dつ目の選択肢", i,j));
                    switch(j) {
                        case 1:
                            ps.setInt(4, choicePassStatus[0]);
                            break;
                        case 2:
                            ps.setInt(4, choicePassStatus[1]);
                            break;
                        case 3:
                            ps.setInt(4, choicePassStatus[2]);
                            break;
                        case 4:
                            ps.setInt(4, choicePassStatus[3]);
                            break;
                    }
                    ps.executeUpdate();
                }
            }
            selectFMQuestionInfo();
            ps.close();
         } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
    public void updateMakeTestQuestion(int questionCnt,int choiceCnt,int questionPatternJudgFlg,int choicePatternJudgFlg,String choiceCategory, String questionDeleteFlg, String choiceDeleteFlg) {
        // (3) SQL送信用インスタンスの作成
        initConnectionDB();
        try {
            ps = conn.prepareStatement("UPDATE FMUQ SET PATTERN_NO = 'X'");
            ps.executeUpdate();
            ps = conn.prepareStatement("UPDATE FMUA SET PATTERN_NO = 'X'");
            ps.executeUpdate();
            for(int i =1; i<= questionCnt; i++) {
                ps = conn.prepareStatement(
                     "insert into FMUQ VALUES(?,2,?,?,?,?)"
                );
                switch(questionPatternJudgFlg) {
                    case 1:
                        ps.setString(1, "A");
                        break;
                    case 3:
                        if(i <= 10) {
                            ps.setString(1, "A");
                        } else {
                            ps.setString(1, "B");
                        }
                        break;
                }
                ps.setInt(2, i);
                ps.setString(3, String.format("%d問目の問題", i));
                ps.setString(4, String.format("%d問目の問題の解説", i));
                ps.setString(5, questionDeleteFlg);
                ps.executeUpdate();
                for(int j=1; j<=choiceCnt; j++) {
                    ps = conn.prepareStatement(
                        "INSERT INTO FMUA VALUES (?,?,?,?,?,?,?);"
                    );
                    switch(questionPatternJudgFlg) {
                    case 1:
                        ps.setString(1, "A");
                        break;
                    case 2:
                        ps.setString(1, "B");
                        break;
                    case 3:
                        if(i <= 10) {
                            ps.setString(1, "A");
                        } else {
                            ps.setString(1, "B");
                        }
                        break;
                    }
                    ps.setString(2, choiceCategory);
                    ps.setInt(3, i);
                    ps.setInt(4, j);
                    ps.setString(5, String.format("%d問目の%dつ目の選択肢", i,j));
                    if(j <= 2) {
                        ps.setInt(6, 1);
                    }else {
                        ps.setInt(6, 0);
                    }
                    ps.setString(7, choiceDeleteFlg);
                    ps.executeUpdate();
                }
            }
            selectFMQuestionInfo();
            ps.close();
         } catch (SQLException e) {
             // TODO 自動生成された catch ブロック
             e.printStackTrace();
         }
    }
    public void updateQuestionRandom9() {
        // (3) SQL送信用インスタンスの作成
        initConnectionDB();
        try {
            ps = conn.prepareStatement(
                "UPDATE FMUQ SET PATTERN_NO = 'X'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
                "UPDATE FMUA SET PATTERN_NO = 'X'"
            );
            ps.executeUpdate();
            for(int i =1; i< 10; i++) {
                ps = conn.prepareStatement(
                     "insert into FMUQ VALUES('A','2',?,?,?,'')"
                 );
                ps.setInt(1, i);
                ps.setString(2, String.format("%d問目の問題", i));
                ps.setString(3, String.format("%d問目の問題の解説", i));
                ps.executeUpdate();
                for(int j=1; j<5; j++) {
                    if(j <= 2) {
                        ps = conn.prepareStatement(
                            "INSERT INTO FMUA VALUES ('A','2',?,?,?,'1','');"
                        );
                    } else {
                        ps = conn.prepareStatement(
                            "INSERT INTO FMUA VALUES ('A','2',?,?,?,'0','');"
                        );
                    }
                    ps.setInt(1, i);
                    ps.setInt(2, j);
                    ps.setString(3, String.format("%d問目の%dつ目の選択肢", i,j));
                    ps.executeUpdate();
                }
            }
            selectFMQuestionInfo();
            ps.close();
         } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
    public void updateQuestionZero() {
        // (3) SQL送信用インスタンスの作成
        initConnectionDB();
        try {
            ps = conn.prepareStatement(
                "UPDATE FMUQ SET DELETE_FLG = '1'"
            );
            ps.executeUpdate();
            ps = conn.prepareStatement(
                "UPDATE FMUA SET DELETE_FLG = '1'"
            );
            ps.executeUpdate();
            selectFMQuestionInfo();
            ps.close();
         } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
  }