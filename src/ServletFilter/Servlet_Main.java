package ServletFilter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Servlet_Main {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:),<script>,欢迎访问fdfkj.dffdf，大家都是傻逼";
        Response response = new Response();
        response.str = "response";
        FilterChain filterChain = new FilterChain();
        filterChain.add(new HTMLFilter()).add(new SensitiveFilter());
        filterChain.doFilter(request, response);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}

interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}

class Request {
    String str;
}

class Response {
    String str;
}

class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]") + "HTMLFilter";
        filterChain.doFilter(request, response);
        response.str += "--HTMLFilter";
    }
}

class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str = request.str.replaceAll("傻逼", "sb") + "SensitiveFilter";
        filterChain.doFilter(request, response);
        response.str += "--SensitiveFilter";
    }
}

// FilterChain 可实现接口也可不实现接口。
class FilterChain {
    private List<Filter> filterList = new ArrayList<>();
    private int index = 0;

    public FilterChain add(Filter filter) {
        filterList.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (index == filterList.size()) return;
        Filter f = filterList.get(index);
        index++;
        f.doFilter(request, response, this);
    }
}
