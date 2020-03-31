package zad1.View;

import javafx.embed.swing.JFXPanel;
import zad1.Utils.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGui extends JFrame {

    private JPanel panelWeather;
    private JPanel panelCurrency;
    private JPanel panelNBP;
    private JPanel panelWiki;
    private JTabbedPane tabbedPane;

    public SwingGui(int width, int height, String frameTitle) throws HeadlessException {
        this.setSize(new Dimension(width,height));
        this.setTitle(frameTitle);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(createInputPanel(),BorderLayout.NORTH);
        this.add(createTabbedPanes(),BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,30));

        JTextField countryTextArea = new JTextField("Poland");
        countryTextArea.setPreferredSize(new Dimension(100,20));

        JTextField cityTextArea = new JTextField("Warsaw");
        cityTextArea.setPreferredSize(new Dimension(100,20));

        JTextField currencyTextArea = new JTextField("USD");
        currencyTextArea.setPreferredSize(new Dimension(100,20));

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(100,20));

        panel.add(countryTextArea);
        panel.add(cityTextArea);
        panel.add(currencyTextArea);
        panel.add(confirmButton);

        addListenersToInputBar(countryTextArea,cityTextArea,currencyTextArea,confirmButton);

        return panel;
    }

    public void addListenersToInputBar(JTextField textField1, JTextField textField2, JTextField textField3, JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Service service = new Service(textField1.getText());
                addWeather(service.getWeather(textField2.getText()));
                addCurracy(service.getRateFor(textField3.getText()));
                addNBP(service.getNBPRate());
                addWikipediaInformation(service.getWikipediaCity(textField2.getText()));
            }
        });
    }

    private JPanel createTabbedPanes(){
        JPanel panel = new JPanel();
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(800,450));

        this.panelWeather = new JPanel();
        tabbedPane.addTab("Weather",panelWeather);

        this.panelCurrency = new JPanel();
        tabbedPane.addTab("Currency",panelCurrency);

        this.panelNBP= new JPanel();
        tabbedPane.addTab("NBP",panelNBP);

        this.panelWiki = new JPanel();
        tabbedPane.addTab("Wikipedia information",panelWiki);

        panel.add(tabbedPane);
        return panel;
    }

    private JPanel makeTabPanel(String name){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,400));
        JLabel label = new JLabel(name);
        panel.add(label);
        return panel;
    }

    public void addWeather(String json) {
        if(panelWeather == null) return;
        panelWeather.add(createTextAreaForTab(json));
    }

    public void addCurracy(Double rate) {
        if(panelCurrency == null) return;
        panelCurrency.add(createTextAreaForTab(String.valueOf(rate)));
    }

    public void addNBP(Double nbp) {
        if(panelNBP == null) return;
        panelNBP.add(createTextAreaForTab(String.valueOf(nbp)));
    }

    public void addWikipediaInformation(JFXPanel panel){
        if(panelWiki == null) return;
        panelWiki.add(panel);
    }

    public JTextArea createTextAreaForTab(String text){
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setPreferredSize(tabbedPane.getPreferredSize());
        return textArea;
    }

}
