
package com.jason.plugin;

import java.awt.*;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.jason.plugin.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 监听器
 *
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @since 2019年11月27日 7:38 下午
 * @version v 0, TranslateListener.java
 */
public class TranslateListener implements EditorMouseListener {

    /**
     * 有道翻译
     */
    private String baseUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=%s&key=%s&type=data&doctype=json&version=1.1&q=";

    TranslateListener() {
        TranslateState translateState = TranslateState.getInstance();
        String keyFrom = "Skykai521";
        String key = "977124034";
        if (translateState != null) {
            TranslateConfig config = translateState.getState();
            if (config != null){
                keyFrom = config.getKeyFrom();
                key = config.getKey();
            }
        }
        baseUrl = String.format(baseUrl,keyFrom,key);
    }

    @Override
    public void mouseClicked(@NotNull EditorMouseEvent event) {
        Editor mouseEditor = event.getEditor();
        Document document = mouseEditor.getDocument();
        //if (!document.isWritable()){
        //    return;
        //}
        final String selectText = mouseEditor.getSelectionModel().getSelectedText();
        if (StringUtils.isBlank(selectText)){
            return;
        }
        if (selectText.contains(" ")){
            return;
        }
        HttpUtils.doGetAsyn(baseUrl + selectText, (String result) -> {
            showPopupBalloon(mouseEditor, TranslateModel.transfer(result));
        });
    }

    private void showPopupBalloon(final Editor editor, final String result) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                    .setFadeoutTime(5000)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}