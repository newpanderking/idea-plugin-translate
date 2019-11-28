
package com.jason.plugin;

import java.awt.*;

import javax.swing.*;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @since 2019年11月28日 10:37 上午
 * @version v 0, TranslateDialog.java
 */
public class TranslateDialog extends DialogWrapper {

    /**
     * key from
     */
    private JTextField keyFromText;
    /**
     * key
     */
    private JTextField keyText;

    /**
     * keyFrom
     */
    private String keyFrom;

    /**
     * key
     */
    private String key;


    /**
     * constructor
     */
    public TranslateDialog() {
        super(true);
        init();
        setTitle("翻译配置");
        setSize(300,300);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new GridLayout(2,2));
        JLabel keyFrom = new JLabel("keyFrom:");
        JLabel key = new JLabel("key:");
        JPanel keyFromTextPanel = new JPanel();
        JPanel keyTextPanel = new JPanel();
        dialogPanel.add(keyFrom);
        dialogPanel.add(keyFromTextPanel);
        dialogPanel.add(key);
        dialogPanel.add(keyTextPanel);
        keyFromText = new JTextField();
        keyFromText.setSize(300,30);
        keyFromTextPanel.add(keyFromText,new FlowLayout());
        keyText = new JTextField();
        keyText.setSize(300,30);
        keyTextPanel.add(keyText,new FlowLayout());
        return dialogPanel;
    }

    @Override
    public void doOKAction(){
        this.keyFrom = keyFromText.getText();
        this.key = keyText.getText();
        if(StringUtils.isBlank(keyFrom) || StringUtils.isBlank(key)){
            Messages.showWarningDialog("keyFrom and key both can not be empty!","Warning");
            return;
        }
        super.doOKAction();
    }

    /**
     * Getter method for property <tt>keyFrom</tt>.
     *
     * @return property value of keyFrom
     */
    public String getKeyFrom() {
        return keyFrom;
    }

    /**
     * Getter method for property <tt>key</tt>.
     *
     * @return property value of key
     */
    public String getKey() {
        return key;
    }
}