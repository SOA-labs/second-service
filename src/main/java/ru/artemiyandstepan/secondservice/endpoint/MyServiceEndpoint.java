package ru.artemiyandstepan.secondservice.endpoint;

import com.example.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.artemiyandstepan.secondservice.OscarService;

import java.util.List;

@Endpoint
public class MyServiceEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/soap";

    @Autowired
    private OscarService oscarService;

    @Value("${server.port}")
    private String port;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetLoosersRequest")
    @ResponsePayload
    public GetLoosersResponse getLoosers(@RequestPayload GetLoosersRequest request) {
        // Получаем фильмы без Оскара из OscarService
        List<ru.artemiyandstepan.secondservice.model.Movie> loosers = oscarService.getLoosers();

        GetLoosersResponse response = new GetLoosersResponse();
        MovieList movieList = new MovieList();

        // Преобразуем доменные объекты в сгенерированные JAXB объекты
        for (ru.artemiyandstepan.secondservice.model.Movie m : loosers) {
            Movie soapMovie = new Movie();
            soapMovie.setId(m.getId());
            soapMovie.setName(m.getName());
            soapMovie.setOscarsCount(m.getOscarsCount());
            soapMovie.setUsaBoxOffice(m.getUsaBoxOffice());
            soapMovie.setGenre(MovieGenre.valueOf(m.getGenre().name()));
            // Если mpaaRating не null, можно установить:
            if (m.getMpaaRating() != null) {
                soapMovie.setMpaaRating(mapMpaaRating(m.getMpaaRating()));
            }
            // Аналогично и для Person, Coordinates, Location (если они нужны)
            // Здесь для упрощения опущено заполнение вложенных объектов.

            movieList.getMovie().add(soapMovie);
        }

        response.setMovies(movieList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HumiliateByGenreRequest")
    @ResponsePayload
    public HumiliateByGenreResponse humiliateByGenre(@RequestPayload HumiliateByGenreRequest request) {
        // Преобразуем жанр, пришедший из SOAP-запроса, к доменному enum
        ru.artemiyandstepan.secondservice.model.MovieGenre domainGenre =
                ru.artemiyandstepan.secondservice.model.MovieGenre.valueOf(request.getGenre().name());

        // Выполняем "унижение" по жанру
        oscarService.humiliateByGenre(domainGenre);

        HumiliateByGenreResponse response = new HumiliateByGenreResponse();
        response.setResult("Oscars were successfully taken away");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SleepRequest")
    @ResponsePayload
    public SleepResponse sleep(@RequestPayload SleepRequest request) throws InterruptedException {
        long time = request.getTime();
        // "Засыпаем" на time секунд
        Thread.sleep(time * 1000);

        SleepResponse response = new SleepResponse();
        response.setMessage("Sleep for " + time + " seconds completed. Application port is " + port);
        return response;
    }

    private com.example.soap.MpaaRating mapMpaaRating(ru.artemiyandstepan.secondservice.model.MpaaRating domainRating) {
        if (domainRating == null) {
            return null;
        }
        switch (domainRating) {
            case G:
                return com.example.soap.MpaaRating.G;
            case PG:
                return com.example.soap.MpaaRating.PG;
            case PG_13:
                return com.example.soap.MpaaRating.PG_13;
            case NC_17:
                return com.example.soap.MpaaRating.NC_17;
            default:
                throw new IllegalArgumentException("Unknown MpaaRating: " + domainRating);
        }
    }

}