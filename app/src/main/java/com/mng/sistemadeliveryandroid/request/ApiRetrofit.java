package com.mng.sistemadeliveryandroid.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.modelo.Pago;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.User;
import com.mng.sistemadeliveryandroid.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiRetrofit {


    private static final String PATH="http://192.168.1.102:5000/API/";

    private static  ServiceSistemaDelivery servicioSistemaDelivery;

    public static ServiceSistemaDelivery getServiceSistemaDelivery(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        servicioSistemaDelivery=retrofit.create(ServiceSistemaDelivery.class);

        return servicioSistemaDelivery;
    }




    public interface ServiceSistemaDelivery{

        //Usuario

        @POST("Usuarios/login")
        Call<String> login(@Body User user);

        @GET("Usuarios")
        Call<Usuario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Usuarios/Actualizar")
        Call<Usuario> actualizarPerfil(@Header("Authorization") String token,@Body Usuario usu);

        @POST("Usuarios/emailPedido")
        Call<Usuario> emailPedido(@Body String email);

        @POST("Usuarios/Nuevo")
        Call<Usuario> nuevo(@Body Usuario user);


        //Producto

        @GET("Productos")
        Call<Producto> obtenerProductos(@Header("Authorization") String token);


        //Pago

        @GET("Pago")
        Call<Pago> obtenerPagos(@Header("Authorization") String token);

        @POST("Pago/CrearPago")
        Call<Pago>crearPago(@Header("Authorization") String token, @Body Pago pago);



        //Pedidos

        @GET("Pedidos")
        Call<Pedido> obtenerPedidos(@Header("Authorization") String token);

        @POST("Pedidos/Crear")
        Call<Pedido>crearPedido(@Header("Authorization") String token, @Body Pedido pedidos);

        @PUT("Pedidos/ModificarUsuario")
        Call<Pedido>modificarPedidoUsuario(@Header("Authorization") String token, @Body Pedido pedido);


        //DetallePedido

        @POST("DetallePedido/AgregarDetallePedido")
        Call<DetallePedido>AgregarDetallePedido(@Header("Authorization") String token, @Body DetallePedido detallePedido);

        @DELETE("DetallePedido/QuitarDetallePedido")
        Call<DetallePedido>QuitarDetallePedido(@Header("Authorization") String token, @Body DetallePedido detallePedido);

        @GET("DetallePedido")
        Call<DetallePedido> obtenerDetallePedido(@Header("Authorization") String token);

        @GET("DetallePedido/DetallePedido")
        Call<DetallePedido> obtenerListaDetalle(@Header("Authorization") String token);


    }

}
