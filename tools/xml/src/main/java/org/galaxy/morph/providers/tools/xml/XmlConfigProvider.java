package org.galaxy.morph.providers.tools.xml;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.tools.ToolsConfigProvider;
import org.jetbrains.annotations.NotNull;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.dataformat.xml.XmlReadFeature;
import tools.jackson.dataformat.xml.XmlWriteFeature;

@AutoService(ConfigProvider.class)
public class XmlConfigProvider extends ToolsConfigProvider {

    public XmlConfigProvider() {
        super(XmlMapper.xmlBuilder()
                .enable(XmlReadFeature.EMPTY_ELEMENT_AS_NULL)
                .enable(XmlReadFeature.AUTO_DETECT_XSI_TYPE)
                .enable(XmlReadFeature.PROCESS_XSI_NIL)
                .enable(XmlWriteFeature.WRITE_XML_1_1)
                .enable(XmlWriteFeature.WRITE_XML_DECLARATION)
                .enable(XmlWriteFeature.AUTO_DETECT_XSI_TYPE)
                .enable(XmlWriteFeature.WRITE_NULLS_AS_XSI_NIL));
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "xml";
    }

    @Override
    public boolean supportsExtension(@NotNull String extension) {
        return "xml".equalsIgnoreCase(extension);
    }
}
