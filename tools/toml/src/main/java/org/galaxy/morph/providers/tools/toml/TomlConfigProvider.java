package org.galaxy.morph.providers.tools.toml;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.tools.ToolsConfigProvider;
import org.jetbrains.annotations.NotNull;
import tools.jackson.dataformat.toml.TomlMapper;
import tools.jackson.dataformat.toml.TomlReadFeature;

@AutoService(ConfigProvider.class)
public class TomlConfigProvider extends ToolsConfigProvider {

    public TomlConfigProvider() {
        super(TomlMapper.builder()
                .enable(TomlReadFeature.PARSE_JAVA_TIME));
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "toml";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "toml".equalsIgnoreCase(extension);
    }
}
