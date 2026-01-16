package com.apiomdb.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apiomdb.demo.component.PeliculaComp;
import com.apiomdb.demo.component.UsuarioComp;
import com.apiomdb.demo.config.UsuarioNone;
import com.apiomdb.demo.models.entity.Pelicula;
import com.apiomdb.demo.models.entity.Usuario;
import com.apiomdb.demo.models.entity.UsuarioPelicula;
import com.apiomdb.demo.models.entity.UsuarioPeliculaId;
import com.apiomdb.demo.service.IPeliculaService;
import com.apiomdb.demo.service.IUsuarioPeliculaService;
import com.apiomdb.demo.service.IUsuarioService;
import com.apiomdb.demo.service.impl.PeticionGetExternalmpl;

import com.google.gson.Gson;

@Controller
@RequestMapping("/peli")
public class PeliculaController {
	
	@Autowired
	private Gson gson;
	@Autowired
	private PeticionGetExternalmpl peticion;
	
	@Autowired 
	private IUsuarioService serviceUsuario;
	@Autowired 
	private IPeliculaService servicePelicula;
	@Autowired
	private IUsuarioPeliculaService serviceUsuarioPelicula;
	
	@Autowired 
	private PeliculaComp peli;
	
	@Autowired 
	private UsuarioComp usu;
		
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String crearFormulario(Usuario usu, Model model) {
		Usuario usuario = usu;
		model.addAttribute("pelicula", peli);
		model.addAttribute("usuario", usuario);
		return "verPeliUser"; 
	}
	
	@RequestMapping(value = "/verpeli", method = RequestMethod.GET)
	public ModelAndView buscarOMDB(@RequestParam(value = "Title") String titulo) throws IOException {
		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		if (titulo.contains(" ")) {
            titulo = titulo.replace(' ', '+');
        }
		busqueda+="&t=" + titulo;
		ModelAndView mav= new ModelAndView("verPeliUser");
		Usuario usuario = new Usuario();
		usuario.copia(usu);
		String texto= peticion.sendGET(busqueda);
		System.out.println(texto);
		//Una vez obtengo la respuesta en JSON lo transformo en un objeto de tipo MOVIE
		//GSON es una librería para transformar Json a objetos y al revés.
		//Para utilizarla hay que pasarle el texto(json) y el tipo de objeto al que queremos transformarlo
		
	    Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
	    
	    //Hago la traduccion entre peliculacomp y pelicula
	    peli.copia(pelicula);

	  	mav.addObject("usuario", usu);
	  	mav.addObject("pelicula", peli);
	  	
		return mav;
	}
	
	@RequestMapping(value = "/guardarPeliUsuario", method = RequestMethod.GET)
	public String guardarUsuario(@RequestParam("rating") Integer ranking, Model model) {
		
		String siguientePantalla;
		
		Usuario usuario= new Usuario();
				
		usuario.copia(usu);
		Usuario usu1= serviceUsuario.findOne(usuario.getMail());
		if(usu1==null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			
			Pelicula p = new Pelicula();
			p.copia(peli);
		    servicePelicula.save(p);
		    
		 // Obtener el receptor vacío usando la constante de UsuarioConfig
	        Usuario receptorVacio = serviceUsuario.findOne(UsuarioNone.RECEPTOR_VACIO_MAIL);

	     // Crear el ID compuesto (usuario vacío en vez de usuario null)
		    UsuarioPeliculaId id = new UsuarioPeliculaId(usu1.getMail(), receptorVacio.getMail(), p.getImdbID());
		    		   	    
		    UsuarioPelicula relacion = new UsuarioPelicula(id,usu1,receptorVacio, p, ranking);
		    serviceUsuarioPelicula.save(relacion);
		    
			siguientePantalla="redirect:buscar";
		}
		//return siguientePantalla;
	
		model.addAttribute("pelicula", peli);
		model.addAttribute("usuario", usu);
		return siguientePantalla;
	}
	
	
	/**
	 * Controller al que se accede cuando se pulsa sobre "lista de películas"
	 * Se coge el usuario de la pantalla anterior "verpeliuser.html"
	 * con este usuario se busca su lista de peliculas favoritas guardadas y se pasa a la pantalla listapelis.html
	 */
	
	@RequestMapping(value = "/listarPelisUsuario", method = RequestMethod.GET)
	public String listarPelisUsuario(Model model) {
		
		String siguientePantalla;
		
		Usuario usuario= new Usuario();
		usuario.copia(usu);
		Usuario usu1= serviceUsuario.findOne(usuario.getMail());
		
		Usuario receptorVacio = serviceUsuario.findOne(UsuarioNone.RECEPTOR_VACIO_MAIL);
		
		if(usu1 == null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			List<UsuarioPelicula> relaciones = serviceUsuarioPelicula.findByUsuarioAndReceptor(usu1.getMail(),receptorVacio.getMail());

	        // Extraer solo las películas
	        List<Pelicula> peliculas = relaciones.stream().map(UsuarioPelicula::getPelicula).toList();

			model.addAttribute("peliculas", peliculas);
			model.addAttribute("usuario", usu1);
			siguientePantalla="listaPelis";
			
		}
		return siguientePantalla;

	}
	
	/**
	 * Método que nos lleva a la pantalla donde se muestran las películas guardadas por el usuario 
	 * Como lo que se guarda en BBDD es la película sin en plot (resumen), se vuelve a hacer una petición a la web
	 */
	
	@RequestMapping(value = "/verPelisUsuario", method = RequestMethod.GET)
	public String verPelisUsuario(Pelicula peli1, Model model) throws IOException {
		
		/**
		 * En esta pantalla nos interesa coger más datos de la película en cuestión, como son el guión largo o el rating en 
		 * imdb.
		 */
		
		System.out.println("Titulo: "+peli1.getTitle());
	    
	    String id= peli1.getImdbID();
		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		
		System.out.println(id);
		
		busqueda +="&i=" + id+"&plot=full";

		String texto= peticion.sendGET(busqueda);
		
		PeliculaComp pelicula = gson.fromJson(texto, PeliculaComp.class);
		
	  	model.addAttribute("peli", pelicula);
		return "datosPeli";

	}

//estas son las peliculas RECIBIDAS, las que han sido compartidas al usuario
	@RequestMapping(value = "/peliculasCompartidas", method = RequestMethod.GET)
	public String peliculasCompartidas(Model model) {

	    Usuario usuario = new Usuario();
	    usuario.copia(usu);                          // usuarioActual en sesión
	    Usuario usu1 = serviceUsuario.findOne(usuario.getMail());

	    if (usu1 == null) {
	        return "redirect:../user/registro";
	    }

	    // Pelis que han compartido con el usuarioActual (él es receptor)
	    List<UsuarioPelicula> relaciones = serviceUsuarioPelicula.findByReceptor(usu1.getMail());

	    // Películas (para listaPelis.html)
	    List<Pelicula> peliculas = relaciones.stream()
	            .map(UsuarioPelicula::getPelicula)
	            .toList();

	    model.addAttribute("peliculas", peliculas);
	    model.addAttribute("usuario", usu1);
	    model.addAttribute("relaciones", relaciones);     // para saber quién la envió y ranking
	    model.addAttribute("modoCompartidas", true);      // para mostrar columnas Amigo/Valorar

	    return "listaPelis";
	}

	@RequestMapping(value = "/valorarCompartida", method = RequestMethod.POST)
	public String valorarCompartida(@RequestParam("imdbID") String imdbID,
	                                @RequestParam("rating") float rating) {

	    Usuario usuario = new Usuario();
	    usuario.copia(usu);                      // receptor actual
	    Usuario usu1 = serviceUsuario.findOne(usuario.getMail());

	    if (usu1 == null) {
	        return "redirect:../user/registro";
	    }

	    // ID compuesto usuario (emisor DESCONOCIDO aquí) + receptor + pelicula
	    // Si solo hay una fila por (receptor, pelicula), lo más práctico es 
	    // buscar por receptor y película y tomar la única relación existente.
	    List<UsuarioPelicula> relaciones = serviceUsuarioPelicula
	            .findByReceptor(usu1.getMail())
	            .stream()
	            .filter(r -> r.getPelicula().getImdbID().equals(imdbID))
	            .toList();

	    if (!relaciones.isEmpty()) {
	        UsuarioPelicula relacion = relaciones.get(0);
	        relacion.setRankingUsuario(rating);
	        serviceUsuarioPelicula.save(relacion);
	    }

	    return "redirect:/peli/peliculasCompartidas";
	}
	
	@RequestMapping(value = "/peliculasEnviadas", method = RequestMethod.GET)
	public String peliculasEnviadas(Model model) {

	    Usuario usuario = new Usuario();
	    usuario.copia(usu);
	    Usuario usu1 = serviceUsuario.findOne(usuario.getMail());

	    if (usu1 == null) {
	        return "redirect:../user/registro";
	    }

	    // usuarioActual como emisor(usuario), receptor distinto de usuarioNone
	    Usuario receptorVacio = serviceUsuario.findOne(UsuarioNone.RECEPTOR_VACIO_MAIL);

	    List<UsuarioPelicula> relaciones = serviceUsuarioPelicula
	            .findByUsuarioAndReceptor(usu1.getMail(), receptorVacio.getMail());

	    model.addAttribute("usuario", usu1);
	    model.addAttribute("relaciones", relaciones);

	    return "pelisEnviadas";     // nueva vista
	}

	//GET: mostrar formulario con lista de emails
	@RequestMapping(value = "/enviarAmigo", method = RequestMethod.GET)
	public String mostrarEnviarAmigo(Model model) {

	    Usuario usuario = new Usuario();
	    usuario.copia(usu);
	    Usuario usu1 = serviceUsuario.findOne(usuario.getMail());

	    if (usu1 == null) {
	        return "redirect:../user/registro";
	    }

	    // Lista de usuarios registrados (amigos posibles)
	    List<Usuario> usuarios = serviceUsuario.findAll();

	    // Eliminar el propio usuarioActual y el usuarioNone de la lista
	    usuarios.removeIf(u -> u.getMail().equals(usu1.getMail()) ||
	                           u.getMail().equals(UsuarioNone.RECEPTOR_VACIO_MAIL));

	    model.addAttribute("usuario", usu1);
	    model.addAttribute("usuarios", usuarios);
	    model.addAttribute("pelicula", peli);     // peli actual en sesión

	    return "enviarAmigo";                     // nueva vista o modal
	}
	
	//POST: guardar relación y volver a verPeliUser
	@RequestMapping(value = "/enviarAmigo", method = RequestMethod.POST)
	public String enviarAmigo(@RequestParam("imdbID") String imdbID,
	                          @RequestParam("receptorMail") String receptorMail,
	                          Model model) {

	    Usuario emisor = new Usuario();
	    emisor.copia(usu);
	    Usuario usuEmisor = serviceUsuario.findOne(emisor.getMail());

	    if (usuEmisor == null) {
	        return "redirect:../user/registro";
	    }

	    Usuario receptor = serviceUsuario.findOne(receptorMail);
	    if (receptor == null) {
	        // opcional: mensaje de error
	        return "redirect:/peli/buscar";
	    }

	    // Película actual
	    Pelicula p = new Pelicula();
	    p.copia(peli);            // peli actual del componente en sesión
	    servicePelicula.save(p);  // por si no existe en BBDD

	    // Crear id compuesto
	    UsuarioPeliculaId id = new UsuarioPeliculaId(
	            usuEmisor.getMail(),
	            receptor.getMail(),
	            p.getImdbID()
	    );

	    UsuarioPelicula relacion = new UsuarioPelicula(
	            id,
	            usuEmisor,
	            receptor,
	            p,
	            0f   // ranking = 0 hasta que receptor valore
	    );

	    serviceUsuarioPelicula.save(relacion);

	    // Mensaje de confirmación (puedes usar atributo de modelo + alert en verPeliUser.html)
	    model.addAttribute("mensaje", "Película enviada correctamente");

	    return "redirect:/peli/verpeli?Title=" + p.getTitle();
	}




}
