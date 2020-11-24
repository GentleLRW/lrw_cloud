package lrw.demo.lib.utils.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @author by lrw
 * @Classname LocalDateDeserialize
 * @Description TODO
 * @Date 2020/8/5 18:58
 */
@Slf4j
public class LocalDateDeserialize extends JsonDeserializer<LocalDate> {
    private final static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            if(jsonParser != null && !StringUtils.isEmpty(jsonParser.getText())){
                return new Date(format.parse(jsonParser.getText()).getTime()).toLocalDate();
            }else{
                return null;
            }
        }catch (Exception e){
            log.warn("LocalDate反序列化异常",e);
        }
        return null;
    }
}
