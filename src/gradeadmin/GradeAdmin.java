/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author ishin
 *
 *
 *
 */
public class GradeAdmin extends JFrame {

    private final static String FONT_NAME = "ＭＳ ゴシック";
    private final static int FONT_SIZE = 12;
    private final static int MIN_WIDTH = 480;
    private final static int MIN_HEIGHT = 200;
    private final static String TITLE = "成績管理";

    private final static String[] SUBJECTS_NAMES = {
        "全体", "英語", "数学", "国語", "社会", "理科"
    };
    
    private final static String[] ACTIVE_ITEMS = {
        "CSV取込", "CSV出力", "ヒストグラム"
    };
    
    private final static String[] COLUMNS_NAMES = {
        "番号", "氏名",
        "英語", "数学", "国語", "社会", "理科",
        "平均", "合計"
    };

    private String[][] TABLEDATA = {
        {"0001", "石水", "75", "60", "55", "80", "75", "", ""},
        {"0002", "山田", "70", "95", "85", "55", "70", "", ""},
        {"0003", "鈴木", "65", "90", "65", "65", "45", "", ""},
        {"0004", "佐藤", "90", "40", "40", "90", "25", "", ""},
        {"0005", "田中", "55", "65", "85", "30", "35", "", ""}
    };

    public GradeAdmin() {
        setInit();
        initComponents();

    }

    private void setInit() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(TITLE);
    }
    
    private void initComponents() {

        JPanel nPanel = new JPanel();
        nPanel.setLayout(new BoxLayout(nPanel, BoxLayout.X_AXIS));
        JComboBox hCbx = new JComboBox(SUBJECTS_NAMES);
        //hCbx.setPreferredSize(new Dimension(64, 20));
        JComboBox oCbx = new JComboBox(ACTIVE_ITEMS);
        //oCbx.setPreferredSize(new Dimension(88, 20));
        JButton aBtn = new JButton("実行");
        
        nPanel.add(hCbx);
        nPanel.add(oCbx);
        nPanel.add(aBtn);
    
        JTable cTbl = new JTable(TABLEDATA, COLUMNS_NAMES);
        cTbl.setPreferredSize(cTbl.getPreferredSize());
        JScrollPane sp = new JScrollPane(cTbl);
        //sp.setPreferredSize(new Dimension(400, 90));
        sp.setPreferredSize(sp.getPreferredSize());
        JPanel cPanel = new JPanel();
        cPanel.setPreferredSize(cPanel.getPreferredSize());
        cPanel.add(sp);

        add(nPanel, BorderLayout.NORTH);
        add(cPanel, BorderLayout.CENTER);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        FontUIResource font = new FontUIResource(FONT_NAME, Font.PLAIN, FONT_SIZE);
        setUIFont(font);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | UnsupportedLookAndFeelException e) {
            Logger.getLogger(GradeAdmin.class.getName()).log(Level.SEVERE, null, e);
        }

        GradeAdmin ga = new GradeAdmin();
        ga.setLocationRelativeTo(null);
        ga.setVisible(true);
    }

    private static void setUIFont(FontUIResource font) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }
}
