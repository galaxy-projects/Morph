package org.galaxy.morph.providers.fasterxml.toml;

import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.dataformat.toml.TomlReadFeature;
import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.fasterxml.FasterXmlConfigProvider;
import org.jetbrains.annotations.NotNull;

@AutoService(ConfigProvider.class)
public class TomlConfigProvider extends FasterXmlConfigProvider {

    public TomlConfigProvider() {
        super(TomlMapper.builder()
                .enable(TomlReadFeature.PARSE_JAVA_TIME)
                .build());
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
