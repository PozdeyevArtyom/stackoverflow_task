package app.util;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class GMTDateTimeConverter implements Converter {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public GMTDateTimeConverter() {
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object convert(Class aClass, Object o) {
        if (o == null) {
            return null;
        } else {
            try {
                String date = (String)o;
                if (!date.isEmpty())
                    return format.parse((String)o);
                else
                    return null;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
