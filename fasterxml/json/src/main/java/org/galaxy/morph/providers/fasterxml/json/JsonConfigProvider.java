package org.galaxy.morph.providers.fasterxml.json;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.fasterxml.FasterXmlConfigProvider;
import org.jetbrains.annotations.NotNull;

@AutoService(ConfigProvider.class)
public class JsonConfigProvider extends FasterXmlConfigProvider {

    public JsonConfigProvider() {
        super(JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
                .build());
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
