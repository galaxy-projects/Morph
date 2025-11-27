package org.galaxy.morph;

import org.galaxy.morph.exceptions.ProviderNotFoundException;
import org.galaxy.morph.exceptions.ResolverNotFoundException;
import org.galaxy.morph.representation.ConfigRepresentation;
import org.galaxy.morph.source.ExtensionConfigProvider;
import org.galaxy.morph.source.Resource;
import org.galaxy.morph.source.Source;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

class Util {

    private Util() {}

    static @NotNull Optional<Resource> getAvailableResource(@NotNull List<ConfigSourceResolver> resolvers,
                                                            @NotNull List<ConfigProvider> providers,
                                                            @NotNull ConfigRepresentation representation) {
        Set<String> supportedExtensions = representation.getSupportedExtensions();

        Stream<Resource> availableSources = resolvers.stream()
                .flatMap(resolver -> resolver.resolve(providers, representation.getName()));

        if (supportedExtensions != null)
            availableSources = availableSources.filter(
                    source -> supportedExtensions.contains(source.source().extension()));
        return availableSources.findFirst();
    }

    static @NotNull Resource createResource(@NotNull List<ConfigSourceResolver> resolvers,
                                            @NotNull List<ConfigProvider> providers,
                                            @NotNull ConfigRepresentation representation) {
        ConfigSourceResolver resolver = resolvers.stream()
                .filter(ConfigSourceResolver::canCreate)
                .findFirst()
                .orElseThrow(() -> new ResolverNotFoundException("No resolver can create source"));
        ExtensionConfigProvider extensionConfigProvider = getExtensionConfigProvider(providers, representation);
        Source target = new Source(representation.getName(), extensionConfigProvider.extension());

        return resolver.createSource(target, extensionConfigProvider.provider());
    }

    static @NotNull ExtensionConfigProvider getExtensionConfigProvider(@NotNull List<ConfigProvider> providers,
                                                                       @NotNull ConfigRepresentation representation) {
        Set<String> supportedExtensions = representation.getSupportedExtensions();

        if (supportedExtensions == null) {
            if (providers.isEmpty())
                throw new ProviderNotFoundException("No provider");
            ConfigProvider provider = providers.get(0);

            return new ExtensionConfigProvider(provider.getDefaultExtension(), provider);
        }
        for (ConfigProvider provider : providers) {
            for (String extension : supportedExtensions) {
                if (provider.supportsExtension(extension))
                    return new ExtensionConfigProvider(extension, provider);
            }
        }
        throw new ProviderNotFoundException("No provider found for " + representation.getName());
    }
}
