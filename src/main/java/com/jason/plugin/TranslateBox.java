package com.jason.plugin;

import java.util.Objects;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @version v 0, TranslateBox.java
 * @since 2019年11月27日 5:19 下午
 */
public class TranslateBox extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        /**
         * translate state
         */
        TranslateState translateState = TranslateState.getInstance();
        if (translateState != null) {
            TranslateConfig translateConfig = translateState.getState();
            if (Objects.isNull(translateConfig)) {
                TranslateDialog dialog = new TranslateDialog();
                dialog.show();
                translateConfig = new TranslateConfig();
                translateConfig.setKeyFrom(dialog.getKeyFrom());
                translateConfig.setKey(dialog.getKey());
                translateState.loadState(translateConfig);
            }
        } else {
            Messages.showWarningDialog("Can not get translate config use plugin default config.", "Tips");
        }

    }
}