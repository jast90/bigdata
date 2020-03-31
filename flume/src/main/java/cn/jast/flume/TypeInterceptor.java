package cn.jast.flume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

public class TypeInterceptor implements Interceptor {

    List<Event> list;

    @Override
    public void initialize() {
        list = new ArrayList<>();
    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        String body = new String(event.getBody());
        if (body.contains("hello")) {
            headers.put("type", "jast");
        } else {
            headers.put("type", "other");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        list = new ArrayList<>();
        for (Event event : events) {
            list.add(intercept(event));
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public void configure(Context context) {

        }

        @Override
        public Interceptor build() {
            return new TypeInterceptor();
        }

    }

}
