package com.jason.plugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @since 2019年11月27日 9:12 下午
 * @version v 0, TranslateConfig.java
 */
@State(name = "translateJar", storages = {@com.intellij.openapi.components.Storage( value ="$APP_CONFIG$/translate-config.xml")})
public class TranslateState implements PersistentStateComponent<TranslateConfig> {

    /**
     * translate config
     */
    private TranslateConfig translateConfig;

    /**
     * 不允许new
     */
    private TranslateState() {
    }

    /**
     * 从服务中获取
     * @return
     */
    public static TranslateState getInstance(){
        return ServiceManager.getService(TranslateState.class);
    }

    @Nullable
    @Override
    public TranslateConfig getState() {
        return this.translateConfig;
    }

    @Override
    public void loadState(@NotNull TranslateConfig state) {
        this.translateConfig = state;
    }

    /**
     * Getter method for property <tt>translateConfig</tt>.
     *
     * @return property value of translateConfig
     */
    public TranslateConfig getTranslateConfig() {
        return translateConfig;
    }

    /**
     * Setter method for property <tt>translateConfig</tt>.
     *
     * @param translateConfig value to be assigned to property translateConfig
     */
    public void setTranslateConfig(TranslateConfig translateConfig) {
        this.translateConfig = translateConfig;
    }
}