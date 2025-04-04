package com.fbi.plugins.unigrative.reportFunctions;

import com.evnt.client.common.EVEManager;
import com.evnt.client.common.EVEManagerUtil;
import com.fbi.fbo.impl.dataexport.QueryRow;
import com.fbi.gui.button.FBMainToolbarButton;
import com.fbi.gui.panel.TitlePanel;
import com.fbi.plugins.FishbowlPlugin;
import com.fbi.sdk.constants.MenuGroupNameConst;
import com.fbi.plugins.unigrative.reportFunctions.repository.Repository;
import com.fbi.plugins.unigrative.reportFunctions.util.property.PropertyGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class reportFunctionsPlugin extends FishbowlPlugin implements PropertyGetter, Repository.RunSql {
    public static final String MODULE_NAME = "Report Functions"; //CHANGE
    public static final String MODULE_FRIENDLY_NAME = "Report Functions"; //CHANGE
    private static final Logger LOGGER = LoggerFactory.getLogger((Class)reportFunctionsPlugin.class);

    private static final String PLUGIN_GENERIC_PANEL = "pluginGenericPanel";

    private Repository repository;

    private static reportFunctionsPlugin instance = null;

    EVEManager eveManager = EVEManagerUtil.getEveManager(); //get access to eve manager

    private TitlePanel titleLabel;
    private JToolBar mainToolBar;
    private FBMainToolbarButton btnSave;

    private JPanel pnlCards;
    private JPanel pnlGeneric;

    public reportFunctionsPlugin() {
        instance = this; //this is so we can access the FishbowlPlugin module methods from other classes
        this.setModuleName(reportFunctionsPlugin.MODULE_NAME);
        this.setMenuGroup(MenuGroupNameConst.REPORTING);//this is the module group it shows up in
        this.setMenuListLocation(1000); //bottom of the list
        this.setIconClassPath("/images/unigrative48.png"); // make sure there is a 24 version in the folder so it can use that for the tabs
        this.setDefaultHelpPath("https://www.unigrative.com/kbtopic/fishbowl-plugins/");

        this.repository = new Repository(this);

    }


    public static reportFunctionsPlugin getInstance() {
        return instance;
    }

    public String getModuleTitle() {
        return "<html><center>Report<br>Functions</center></html>"; //CHANGE -> THIS SHOWS IN THE MODULE LIST
    }

    public String getProperty(final String key) {
        return this.repository.getProperty(key);
    }

    public List<QueryRow> executeSql(final String query) {
        return (List<QueryRow>)this.runQuery(query);
    }


    private void loadSettings() {
        LOGGER.info("Loading Settings");
//        this.txtSqlConnectionUrl.setText(Property.SQL_CONNECTION_URL.get(this));
//        LOGGER.info(Property.SQL_CONNECTION_URL.get(this));
//        this.txtUsername.setText(Property.USERNAME.get(this));
//        this.txtPassword.setText(Encryptor.getInstance().decrypt(Property.PASSWORD.get(this)));
//
//        this.apiKeyTextField.setText(Property.CC_API_KEY.get(this));
//        this.OAuthTokenTextField.setText(Property.CC_TOKEN.get(this));
//        this.txtLastSync.setText(Property.LAST_SYNC_TIME.get(this));
//
//        this.txtCampaignDate.setText(Property.CAMPAIGN_CREATED_DATE.get(this));

        LOGGER.info("Settings Loaded");

    }

    protected void saveSettings(){
        LOGGER.info("Saving settings");

        final Map<String, String> properties = new HashMap<>();

//        properties.put(Property.USERNAME.getKey(), txtUsername.getText());
//        properties.put(Property.PASSWORD.getKey(), Encryptor.getInstance().encrypt(txtPassword.getText()));
//        properties.put(Property.SQL_CONNECTION_URL.getKey(), txtSqlConnectionUrl.getText());
//
//        properties.put(Property.CC_API_KEY.getKey(), apiKeyTextField.getText());
//        properties.put(Property.CC_TOKEN.getKey(), OAuthTokenTextField.getText());
//
//
//        String lastSync = "";
//        DateTimeFormatter format = ISODateTimeFormat.dateHourMinuteSecond();
//        try {
//
//            lastSync = format.print(format.parseDateTime(txtLastSync.getText())); //set in settings
//        }
//        catch (Exception e){
//            LOGGER.error("Unable to parse Last Sync Time", e);
//            UtilGui.showMessageDialog("Unable to parse Last Sync time");
//            return; //dont save
//        }
//
//        properties.put(Property.LAST_SYNC_TIME.getKey(), lastSync);
//
//
//        String campaignDate = "";
//        try {
//            campaignDate = format.print(format.parseDateTime(txtCampaignDate.getText())); //set in settings
//
//        }
//        catch (Exception e){
//            LOGGER.error("Unable to parse campaign date", e);
//            UtilGui.showMessageDialog("Unable to parse campaign date");
//            return; //dont save
//        }
//
//        properties.put(Property.CAMPAIGN_CREATED_DATE.getKey(), campaignDate);
//


        this.savePluginProperties(properties);

        LOGGER.info("Settings Saved");
    }

    private void btnSaveActionPerformed() {
        this.saveSettings();

    }

    protected void initModule() {
        super.initModule();
        this.initComponents(); //HAS TO COME FIRST SINCE THE PANELS NEED TO BE MADE
        this.setMainToolBar(this.mainToolBar);
        this.initLayout();
        this.setButtonPrintVisible(false); //OPTIONAL
        this.setButtonEmailVisible(false); //OPTIONAL


    }

    private void initLayout() {
        //PANELS TO BE ADDED TO THE TABBED LAYOUT IF DESIRED
        JLabel lblMessage = new JLabel();
        lblMessage.setText("This plugin adds methods that can be used in iReport to return complex values"); //CHANGE


        this.pnlCards.add(lblMessage);
        this.pnlCards.add(pnlGeneric, "GenericPanel" );
        this.hideShowPanels();
    }

    void hideShowPanels() {
        final CardLayout layout = (CardLayout)this.pnlCards.getLayout();
        this.enableControls(true);
        layout.show(this.pnlCards, PLUGIN_GENERIC_PANEL);
    }

    private void enableControls(final boolean enable) {
        this.btnSave.setEnabled(enable);
    }

    private void initComponents() {
        try {
            this.titleLabel = new TitlePanel();
            this.pnlCards = new JPanel(); //Tabbed layout Option
            this.mainToolBar = new JToolBar();
            this.btnSave = new FBMainToolbarButton();

            //GENERIC PANEL
            this.pnlGeneric  = new JPanel();

            this.mainToolBar.setFloatable(false);
            this.mainToolBar.setRollover(true);
            this.mainToolBar.setName("mainToolBar");

            this.btnSave.setIcon((Icon) new ImageIcon(this.getClass().getResource("/icon24/filesystem/disks/disk_gold.png")));
            this.btnSave.setText("Save");
            this.btnSave.setToolTipText("Save your Plugin settings."); //CHANGE NAME
            this.btnSave.setHorizontalTextPosition(0);
            this.btnSave.setIconTextGap(0);
            this.btnSave.setMargin(new Insets(0, 2, 0, 2));
            this.btnSave.setName("btnSave");
            this.btnSave.setVerticalTextPosition(3);
            this.btnSave.addActionListener((ActionListener) new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    reportFunctionsPlugin.this.btnSaveActionPerformed();
                }
            });
            this.mainToolBar.add((Component) this.btnSave);

//HEADER LABEL AT THE TOP OF THE MODULE
            this.setName("this");
            this.setLayout((LayoutManager) new GridBagLayout());
            ((GridBagLayout) this.getLayout()).columnWidths = new int[]{0, 0};
            ((GridBagLayout) this.getLayout()).rowHeights = new int[]{0, 0, 0};
            ((GridBagLayout) this.getLayout()).columnWeights = new double[]{1.0, 1.0E-4};
            ((GridBagLayout) this.getLayout()).rowWeights = new double[]{0.0, 1.0, 1.0E-4};
            this.titleLabel.setModuleIcon(new ImageIcon(this.getClass().getResource("/images/unigrative32.png"))); //CHANGE
            this.titleLabel.setModuleTitle(this.MODULE_FRIENDLY_NAME);
            this.titleLabel.setBackground(new Color(44, 94, 140));
            this.titleLabel.setName("titleLabel");
            this.add((Component) this.titleLabel, (Object) new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));





            this.pnlCards.setName("pnlCards");
            this.pnlCards.setLayout(new CardLayout());
            this.add((Component) this.pnlCards, (Object) new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));

        }
        catch (Exception e){
            LOGGER.error("Error in init",e);
        }
        LOGGER.info("init done");
    }

}
