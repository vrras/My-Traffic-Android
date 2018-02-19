package id.tech.firas.mytraffic.network;

import id.tech.firas.mytraffic.models.ResponseLogin;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by Firas Luthfi on 2/17/2018.
 */

public interface NetworkStores {

    @FormUrlEncoded
    @POST("user/login/")
    Observable<ResponseLogin> getLogin(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @PUT("user/logout/")
    Observable<ResponseLogin> getLogout(@Field("id_user") int id_user);

    @FormUrlEncoded
    @POST("loc/log/")
    Observable<ResponseLogin> getLastLocation(@Field("lat") String lat,
                                              @Field("lng") String lng,
                                              @Field("id_user") int id_user);

}
