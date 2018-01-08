/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DataIndexed.XPage;
import DataIndexed.XWord;
import Engine.Indexer;
import Engine.SearchHelper;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.DefaultCaret;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import parsers.WikiXMLParser;

/**
 *
 * @author Arif
 */
public class SearchEngineJFrame extends javax.swing.JFrame implements Indexer.IndexingCallbacks {

    void searching() {

        SearchHelper searchHelper = new SearchHelper(pageMapRepository, invertedIndex);

        List<XPage> result = searchHelper.search("april");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("https://simple.wikipedia.org/wiki/" + result.get(i).getTitle());
            System.out.println(result.get(i).getWordInstances().get("april").size());
        }

//        
//        Cursor<XWord> cursor = invertedIndex.find(ObjectFilters.eq("word", "a"));
//
//        
//        XWord xword = cursor.firstOrDefault();
////                
//        System.out.println(xword.getPagesContainingThis().size());
    }

    void initIndexing() {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("test2.db")
                .openOrCreate("user", "password");

        ObjectRepository<XPage> pageMapRepository = db.getRepository(XPage.class);
        ObjectRepository<XWord> invertedIndex = db.getRepository(XWord.class);

        final Indexer indexer = new Indexer(pageMapRepository, invertedIndex, this);//TODO: temporary...

        WikiXMLParser xmlParser = new WikiXMLParser(new File("path to file"), (WikiXMLParser.WikiXMLParserCallbackReciever) indexer);

        xmlParser.init();

    }

    int count = 0;

    public void onProgress(int id) {
        System.out.println(id);
    }

    public void done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ObjectRepository<XPage> pageMapRepository;
    ObjectRepository<XWord> invertedIndex;

    /**
     * Creates new form SearchJFrame
     */
    public SearchEngineJFrame() {
        initComponents();

        this.jEditorPaneMain.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        this.jEditorPaneMain.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        );

        DefaultCaret caret = (DefaultCaret) this.jEditorPaneMain.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        this.jEditorPaneMain.setEditable(false);
        //adjust positions of windows
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = new Dimension(screenSize.width / 2, screenSize.width/3);
        this.setSize(d);
        centreWindow(this);
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("test2.db")
                .openOrCreate("user", "password");

        pageMapRepository = db.getRepository(XPage.class);
        invertedIndex = db.getRepository(XWord.class);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldQuery = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPaneMain = new javax.swing.JEditorPane();
        jButtonSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldQuery.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQueryActionPerformed(evt);
            }
        });

        jEditorPaneMain.setEditable(false);
        jScrollPane1.setViewportView(jEditorPaneMain);

        jButtonSearch.setBackground(new java.awt.Color(102, 0, 204));
        jButtonSearch.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(153, 0, 255));
        jLabel1.setText("Search Wikipedia Simple");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        search();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextFieldQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQueryActionPerformed
        search();
    }//GEN-LAST:event_jTextFieldQueryActionPerformed

    void search() {
        SearchHelper searchHelper = new SearchHelper(pageMapRepository, invertedIndex);

        List<XPage> result = searchHelper.search(this.jTextFieldQuery.getText());
        String textResult = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "h1 {\n"
                + "    color: blue;\n"
                + "    font-family: verdana;\n"
                + "    font-size: 300%;\n"
                + "}\n"
                + "a  {\n"
                + "    color: orange;\n"
                + "    font-family: courier;\n"
                + "    font-size: 160%;\n"
                + "}";
        for (int i = 0; i < result.size(); i++) {
            textResult += "\n<a  href=\"https://simple.wikipedia.org/wiki/"+ result.get(i).getTitle().replace(" ", "_") +"\">" + result.get(i).getTitle() + "</a><br>";
//            textResult += "<p>" + result.get(i).getWordInstances().get(this.jTextFieldQuery.getText()).size() + "</p>";
        }
        this.jEditorPaneMain.setText(textResult);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchEngineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchEngineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchEngineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchEngineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchEngineJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JEditorPane jEditorPaneMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldQuery;
    // End of variables declaration//GEN-END:variables

}
