package org.galaxy.morph.providers.fasterxml.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.providers.fasterxml.FasterXmlConfigProvider;
import org.jetbrains.annotations.NotNull;

@AutoService(ConfigProvider.class)
public class XmlConfigProvider extends FasterXmlConfigProvider {

    public XmlConfigProvider() {
        super(XmlMapper.xmlBuilder()
                .enable(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL)
                .enable(FromXmlParser.Feature.AUTO_DETECT_XSI_TYPE)
                .enable(FromXmlParser.Feature.PROCESS_XSI_NIL)
                .enable(ToXmlGenerator.Feature.WRITE_XML_1_1)
                .enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION)
                .enable(ToXmlGenerator.Feature.AUTO_DETECT_XSI_TYPE)
                .enable(ToXmlGenerator.Feature.WRITE_NULLS_AS_XSI_NIL)
                .build());
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
