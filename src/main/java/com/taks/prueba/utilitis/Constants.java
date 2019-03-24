package com.taks.prueba.utilitis;

public class Constants {

	
	// Spring Security

		public static final String LOGIN_URL = "/login";
		public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
		public static final String TOKEN_BEARER_PREFIX = "Bearer ";

		// JWT

		public static final String ISSUER_INFO = "https://www.autentia.com/";
		public static final String SUPER_SECRET_KEY = "1234";
		public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
		
		public static final String SECRET = "SecretKeyToGenJWTs";
	    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String HEADER_STRING = "Authorization";
	    public static final String SIGN_UP_URL = "/users/sign-up";
	    
	    //rutas
	    public static final String  COMENTARIO="comentario";
	    public static final String  COMENTARIO_ID="comentario/{id}";
	    
	    public static final String  USUARIO="usuario";
	    public static final String  USUARIO_ID="usuario/{id}";
	    
	    public static final String  PROYECTO ="proyecto" ;
	    public static final String  PROYECTO_ID="proyecto/{id}";
	    
	    public static final String  TAREAS="tarea";
	    public static final String  TAREAS_ID="tarea/{id}";
	     
	    public static final String  SIGNUP= "/v1/api/usuario";
	    public static final String  LOGIN= "/login";
	   
	    
	
	   
	   
	    
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
