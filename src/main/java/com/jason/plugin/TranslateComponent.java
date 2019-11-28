
package com.jason.plugin;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.editor.EditorFactory;

/**
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @version v 0, DictionaryComponent.java
 * @since 2019年11月27日 7:28 下午
 */
public class TranslateComponent implements BaseComponent {
    @Override
    public void initComponent() {
        EditorFactory.getInstance().getEventMulticaster().addEditorMouseListener(new TranslateListener());
    }
}