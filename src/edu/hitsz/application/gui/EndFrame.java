package edu.hitsz.application.gui;

import edu.hitsz.application.Main;
import edu.hitsz.leaderboard.Round;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author SoraShu
 */
public class EndFrame {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JTable roundTable;
    private JScrollPane mediumPanel;
    private JLabel levelDisplayLabel;
    private JButton delButton;

    private List<Round> rounds;

    private final String[] columnNames = {"名次", "玩家名", "得分", "记录时间"};

    private Object[][] tableData = {{}};


    public EndFrame() {
        levelDisplayLabel.setText(Main.getDifficulty().getName());

        setTable();


        delButton.addActionListener(e -> {
            Object[] options = {"是", "否"};
            int value = JOptionPane.showOptionDialog(null,
                    "请确认是否删除",
                    "提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    "是");

            if (value != JOptionPane.CLOSED_OPTION) {
                switch (value) {
                    case 0:
                        deleteRow();
                        break;
                    case 1:
                    default:
                }
            }
        });


    }

    private void setTable(){
        rounds = Main.roundDao.getSortedRounds();
        if (rounds != null) {
            int len = rounds.size();
            int wid = columnNames.length;
            tableData = new Object[len][wid];
            for (int i = 0; i < len; i++) {
                Round round = rounds.get(i);
                tableData[i][0] = i + 1;
                tableData[i][1] = round.getPlayerName();
                tableData[i][2] = round.getFinalScore();
                tableData[i][3] = round.getPlayTime();
            }
        } else {
            tableData = null;
        }

        roundTable.setModel(new DefaultTableModel(tableData, columnNames){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        mediumPanel.setViewportView(roundTable);

    }

    private void deleteRow() {
        int row = roundTable.getSelectedRow();
        if (row != -1) {
            Main.roundDao.delRound(rounds.get(row).getId());
            setTable();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
