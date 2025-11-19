package org.galaxy.morph.providers.fasterxml.yaml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.fasterxml.FasterXmlConfigProvider;
import org.jetbrains.annotations.NotNull;

@AutoService(ConfigProvider.class)
public class YamlConfigProvider extends FasterXmlConfigProvider {

    public YamlConfigProvider() {
        super(YAMLMapper.builder()
                .enable(JsonParser.Feature.ALLOW_YAML_COMMENTS)
                .enable(YAMLParser.Feature.EMPTY_STRING_AS_NULL)
                .build());
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "yml";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "yaml".equalsIgnoreCase(extension) || "yml".equalsIgnoreCase(extension);
    }
}
