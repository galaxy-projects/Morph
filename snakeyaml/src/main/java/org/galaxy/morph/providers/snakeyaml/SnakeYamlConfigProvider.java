package org.galaxy.morph.providers.snakeyaml;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.representation.CommentedResult;
import org.galaxy.morph.representation.ObjectComments;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@AutoService(ConfigProvider.class)
public class SnakeYamlConfigProvider implements ConfigProvider {

    private final Yaml yaml;

    public SnakeYamlConfigProvider() {
        yaml = new Yaml(
                new Constructor(new LoaderOptions()),
                new Representer(new DumperOptions()),
                new DumperOptions(),
                new LoaderOptions());
    }

    @Override
    public void setup(int indentation) {
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
    public @NotNull <T> CommentedResult<T> load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable {
        T value = yaml.loadAs(new InputStreamReader(in, StandardCharsets.UTF_8), type);

        return new CommentedResult<>(value, null);
    }

    @Override
    public <T> void save(@NotNull OutputStream out, @NotNull T value, @NotNull ObjectComments comments)
            throws Throwable {
        yaml.dump(value, new OutputStreamWriter(out, StandardCharsets.UTF_8));
    }
}
