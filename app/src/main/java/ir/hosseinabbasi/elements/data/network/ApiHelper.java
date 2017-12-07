package ir.hosseinabbasi.elements.data.network;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import ir.hosseinabbasi.elements.data.db.model.Data;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public interface ApiHelper {

    @Streaming
    @GET("ccc")
    Observable<Response<ResponseBody>> downloadFile(@QueryMap Map<String, String> options);

}
