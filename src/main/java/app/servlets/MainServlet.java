package app.servlets;

import app.entities.Error;
import app.entities.Filter;
import app.entities.Owner;
import app.entities.Question;
import app.models.ErrorModel;
import app.models.QuestionModel;
import app.util.GMTDateTimeConverter;
import app.util.JsonHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class MainServlet extends HttpServlet {
    private final String _url = "http://api.stackexchange.com/2.2/search";
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Filter filter = getParametersAndValidate(req);
            URL url = buildUrl(_url, filter);
            String jsonStr = getInfo(url);

            JSONObject json = new JSONObject(jsonStr);
            if (json.has("error_id")) {
                Error err = JsonHelper.parseError(json);
                String message = String.format("Error %d - %s: %s", err.getErrorId(),
                        err.getErrorName(), err.getErrorMessage());
                req.setAttribute("error", new ErrorModel(message, false));
            }
            else {
                List<Question> questionList = JsonHelper.parseQuestions(json);
                req.setAttribute("result", questionList.stream().map(QuestionModel::new).collect(Collectors.toList()));
            }

        }
        catch (ValidationException e) {
            req.setAttribute("error", new ErrorModel(e.getMessage(), false));
        }
        catch (Exception e) {
            req.setAttribute("error", new ErrorModel(e.getMessage(), true));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/index.jsp");
        dispatcher.forward(req, resp);

    }

    private Filter getParametersAndValidate(HttpServletRequest req) throws IllegalAccessException, InvocationTargetException {
        Filter filter = new Filter();

        ConvertUtils.register(new GMTDateTimeConverter(), Date.class);
        BeanUtils.populate(filter, req.getParameterMap());

        Set<ConstraintViolation<Filter>> validates = validator.validate(filter);
        if (validates.size() > 0)
        {
            String message = validates.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            throw new ValidationException(message);
        }

        return filter;
    }

    private URL buildUrl(String url, Filter filter) throws URISyntaxException, MalformedURLException {
        URIBuilder builder = new URIBuilder(url);
        builder.addParameter("site", "stackoverflow");

        if (!filter.getTitle().isEmpty())
            builder.addParameter("intitle", filter.getTitle());

        if (!filter.getInclude().isEmpty())
            builder.addParameter("tagged", filter.getInclude());

        if (!filter.getExclude().isEmpty())
            builder.addParameter("nottaged", filter.getExclude());

        builder.addParameter("page", "" + filter.getPagenum());
        builder.addParameter("pagenum", "" + filter.getPagesize());

        if (filter.getFrom() != null && filter.getTo() != null) {
            builder.addParameter("fromdate", String.format("%s", filter.getFrom().getTime()/1000));
            builder.addParameter("todate", String.format("%s", filter.getTo().getTime()/1000));
        }

        return builder.build().toURL();
    }

    private String getInfo(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept-Encoding", "gzip");

        String jsonStr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                result.append(line);

            jsonStr = result.toString();
        }

        return jsonStr;
    }
}
