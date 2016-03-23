/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ishin
 *
 *
 *
 */
public class GradeAdmin extends JFrame implements ActionListener {

    private final static String FONT_NAME = "ＭＳ ゴシック";
    private final static int FONT_SIZE = 12;
    private final static int MIN_WIDTH = 640;
    private final static int MIN_HEIGHT = 400;
    private final static String TITLE = "成績管理";

    private JButton beginBtn;
    private JButton clearBtn;
    private JLabel statusBar;
    private JComboBox oCbx;
    private JTable cTbl;
    private DefaultTableModel tableModel;
    private ReadCSVData rcd;
    private List<ScoreData> csvData;

    private final static String[] ACTIVE_ITEMS = {
        "CSV取込", "CSV出力", "ヒストグラム"
    };

    private final static String[] COLUMNS_NAMES = {
        "番号", "氏名", "平均", "合計",
        "英語", "数学", "国語", "社会", "理科",};

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

        oCbx = new JComboBox(ACTIVE_ITEMS);
        beginBtn = new JButton("開始");
        clearBtn = new JButton("クリア");

        nPanel.add(oCbx);
        nPanel.add(beginBtn);
        nPanel.add(clearBtn);

        tableModel = new DefaultTableModel(COLUMNS_NAMES, 0);
        cTbl = new JTable(tableModel);
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(cTbl);

        statusBar = new JLabel("Ready.");
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sPanel.add(statusBar);

        add(nPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(sPanel, BorderLayout.SOUTH);

        beginBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // クリアボタン
        if (e.getSource() == clearBtn) {
            statusBar.setText("クリア");

        }

        // 開始ボタン
        if (e.getSource() == beginBtn) {

            int index = oCbx.getSelectedIndex();
            switch (index) {
                case 0:
                    // CSV取り込み
                    statusBar.setText(ACTIVE_ITEMS[0]);
                    csvfileInput();
                    break;
                case 1:
                    // CSV出力
                    statusBar.setText(ACTIVE_ITEMS[1]);
                    csvfileOutput();
                    break;
                default:
                    // ヒストグラム
                    statusBar.setText(ACTIVE_ITEMS[2]);
                    drawHistogram();
            }

        }
    }
    
    private void drawHistogram(){
        // ヒストグラムを作成、表示
    }
    
    private void csvfileOutput(){
        // ＣＳＶファイルを出力
    }

    private void csvfileInput() {
        // ＣＳＶファイル取り込み
        
        JFileChooser filechooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("CSVファイル", "csv", "CSV");
        filechooser.setFileFilter(filter);
        
        int selected = filechooser.showOpenDialog(this);
        switch (selected) {
            case JFileChooser.APPROVE_OPTION:
                File file = filechooser.getSelectedFile();
                System.out.println(file.getName());
                readCSVData(file);
                break;
            case JFileChooser.CANCEL_OPTION:
                System.out.println("キャンセルされました");
                break;
            case JFileChooser.ERROR_OPTION:
                System.out.println("エラー又は取消しがありました");
                break;
            default:
                break;
        }

    }

    private void readCSVData(File file) {
        rcd = new ReadCSVData();
        csvData = rcd.readScoreData(file);

        String[] data = new String[9];
        String datas[] = new String[5];
        for (int i = 0; i < csvData.size(); i++) {
            ScoreData sd = csvData.get(i);

            System.out.println("no = " + sd.getID());
            System.out.println("name = " + sd.getName());
            data[0] = sd.getID();
            data[1] = sd.getName();

            datas = sd.getScore();
            for (int j = 0; j < 5; j++) {
                System.out.println("kamoku" + j + " = " + data[j]);
                data[j + 4] = datas[j];
            }
            tableModel.addRow(data);

        }
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
