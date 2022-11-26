package hello.typeconverter.formatter;

import hello.typeconverter.Type.IpPort;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {
    @Test
    void formattingConversionService(){
        //FormmatingConversionService는 ConversionService관련 모든 기능을 상속받기 때문에 컨버터 / 포매터 모두 등록이 간으하다.
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //컨버터 등록
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //포매터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        //컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        //포매터 사용
        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000);
    }
}
