package org.galaxy.morph.providers.gson;

import com.google.auto.service.AutoService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;
import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@AutoService(ConfigProvider.class)
public class GsonConfigProvider implements ConfigProvider {

    private final Gson gson;

    public GsonConfigProvider() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setStrictness(Strictness.LENIENT)
                .create();
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "json";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "json".equalsIgnoreCase(extension);
    }

    @Override
    public void setup(int indentation) {}

    @Override
    public @NotNull <T> T load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable {
        return gson.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), type);
    }

    @Override
    public <T> void save(@NotNull OutputStream out, @NotNull T value) throws Throwable {
        gson.toJson(value, value.getClass(), new OutputStreamWriter(out, StandardCharsets.UTF_8));
    }
}
