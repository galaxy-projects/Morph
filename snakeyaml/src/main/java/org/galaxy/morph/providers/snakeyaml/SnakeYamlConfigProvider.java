package org.galaxy.morph.providers.snakeyaml;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@AutoService(ConfigProvider.class)
public class SnakeYamlConfigProvider implements ConfigProvider {

    private Yaml yaml;

    public SnakeYamlConfigProvider() {}

    @Override
    public void setup(int indentation) {
        DumperOptions dumpOptions = new DumperOptions();
        dumpOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumpOptions.setPrettyFlow(true);
        dumpOptions.setIndent(indentation);

        yaml = new Yaml(dumpOptions);
        yaml.setBeanAccess(BeanAccess.FIELD);
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "yml";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "yaml".equalsIgnoreCase(extension) || "yml".equalsIgnoreCase(extension);
    }

    @Override
    public @NotNull <T> T load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable {
        return yaml.loadAs(new InputStreamReader(in, StandardCharsets.UTF_8), type);
    }

    @Override
    public <T> void save(@NotNull OutputStream out, @NotNull T value) throws Throwable {
        yaml.dump(value, new OutputStreamWriter(out, StandardCharsets.UTF_8));
    }
}
