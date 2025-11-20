package org.galaxy.morph.providers.tools.json;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.tools.ToolsConfigProvider;
import org.jetbrains.annotations.NotNull;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.json.JsonMapper;

@AutoService(ConfigProvider.class)
public class JsonConfigProvider extends ToolsConfigProvider {

    public JsonConfigProvider() {
        super(JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .enable(JsonReadFeature.ALLOW_MISSING_VALUES));
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "json";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "json".equalsIgnoreCase(extension);
    }
}
