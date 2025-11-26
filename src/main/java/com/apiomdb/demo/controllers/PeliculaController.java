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
import com.apiomdb.demo.models.entity.Pelicula;
import com.apiomdb.demo.models.entity.Usuario;
import com.apiomdb.demo.models.entity.UsuarioPelicula;
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
		
		/**
		 * los metodos copia de los controller se usan para copiar los datos de los entitys correspondientes
		 * Es decir, todo lo que tengamos en objetos de tipo entity se va a guardar en base de datos
		 * Para que no se hagan duplicados en bases de datos trabajamos con objetos "clones" de estos entitys que son
		 * iguales pero son components.
		 * Esto nos va a permitir trabajar con objetos que no sean exactamente iguales a lo que guardamos en bbdd, mas flexible y util
		 */
		
		
		usuario.copia(usu);
		Usuario usu1= serviceUsuario.findOne(usuario.getMail());
		if(usu1==null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			
			Pelicula p = new Pelicula();
			p.copia(peli);
		    servicePelicula.save(p);
		    
		    UsuarioPelicula relacion = new UsuarioPelicula(usu1, p, ranking);
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
		if(usu1==null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			List<UsuarioPelicula> relaciones = serviceUsuarioPelicula.findByUsuario(usu1.getMail());

	        // Extraer solo las películas
	        List<Pelicula> peliculas = relaciones.stream()
	                                             .map(UsuarioPelicula::getPelicula)
	                                             .toList();

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

}
