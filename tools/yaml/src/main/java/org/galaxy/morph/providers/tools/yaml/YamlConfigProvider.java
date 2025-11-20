package org.galaxy.morph.providers.tools.yaml;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.tools.ToolsConfigProvider;
import org.jetbrains.annotations.NotNull;
import tools.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.dataformat.yaml.YAMLReadFeature;

@AutoService(ConfigProvider.class)
public class YamlConfigProvider extends ToolsConfigProvider {

    public YamlConfigProvider() {
        super(YAMLMapper.builder()
                .enable(YAMLReadFeature.EMPTY_STRING_AS_NULL));
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
